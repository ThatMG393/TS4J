package com.thatmg393.ts4j.socket;

import com.thatmg393.ts4j.socket.base.BaseServerClient;
import com.thatmg393.ts4j.socket.packet.base.BasePacket;
import com.thatmg393.ts4j.socket.packet.parser.base.BasePacketParser;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unchecked")
public class MultiClientSocketServer<C extends BaseServerClient, P extends BasePacketParser<? extends BasePacket>> {
	private static final Logger LOGGER = Logger.getLogger("MultiClientSocketServer");
	
	private ThreadPoolExecutor serverThreadPool;
	
	private String serverIP;
	private int serverPort;
	private int serverMaxClients;
	
	private InetSocketAddress serverSocketAddress;
	private ServerSocketChannel serverSocket;
	
	private Class<C> clientClass;
	private P packetParser;
	
	public MultiClientSocketServer(String ip, int port, int maxClients) throws IOException {
		this.serverIP = ip;
		this.serverPort = port;
		this.serverMaxClients = maxClients;
		this.serverSocketAddress = new InetSocketAddress(serverIP, serverPort);
		
		this.serverSocket = ServerSocketChannel.open();
		this.serverSocket.socket().bind(serverSocketAddress);
		this.serverSocket.socket().setReuseAddress(true);
		
		try {
			Type[] typeParams = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
			
			clientClass = (Class<C>) typeParams[0];
			packetParser = ((Class<P>) typeParams[1]).getDeclaredConstructor().newInstance();
		} catch (Exception e) { LOGGER.log(Level.WARNING, e.getMessage()); }
	}
	
	// TODO: Properly tell clients that server has been closed (either callbacks or BasePacket class)
	public void startServing() {
		if (serverThreadPool == null || serverThreadPool.isTerminated()) this.serverThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(serverMaxClients + 1);
		serverThreadPool.execute(() -> {
			LOGGER.info("Client acceptor thread started!");
			LOGGER.info("Socket is now waiting for clients.");
			while (!Thread.interrupted()) {
				try {
					SocketChannel client = serverSocket.accept();
					LOGGER.info("Client from " + client.socket().getInetAddress() + ":" + client.socket().getPort() + " connected.");
					
					if ((serverThreadPool.getActiveCount() - 1) == serverMaxClients) {
						LOGGER.info("Server pool is full, disconnecting client...");
						
						client.close();
				    } else {
					    serverThreadPool.execute(
						    clientClass.getDeclaredConstructor(
							    Socket.class, BasePacketParser.class
						    ).newInstance(
							    client.socket(), packetParser
						    )
					    );
				    }
				} catch (ClosedByInterruptException e) {
					LOGGER.info("java.nio.channels.ClosedByInterruptException! Socket server is probably being closed...");
				} catch (Exception e) {
					LOGGER.severe("Client acceptor thread got an exception! Shutting down ASAP!");
					LOGGER.log(Level.SEVERE, e.getMessage());
					serverThreadPool.shutdownNow();
					break;
				}
			}
			LOGGER.info("Client acceptor thread shutdown!");
		});
	}
	
	public void stopServer() throws IOException {
		if (serverThreadPool != null) serverThreadPool.shutdownNow();
		serverSocket.close();
	}
}
