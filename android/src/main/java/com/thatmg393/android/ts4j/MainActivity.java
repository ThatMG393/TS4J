package com.thatmg393.android.ts4j;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.thatmg393.android.ts4j.databinding.ActivityMainBinding;
import com.thatmg393.android.ts4j.log.CustomLogFormatter;
import com.thatmg393.android.ts4j.log.SoraEditorLogHandler;
import com.thatmg393.ts4j.server.TerrariaServer;
import com.thatmg393.ts4j.ts4j.BuildConfig;
import com.thatmg393.ts4j.world.TerrariaWorld;

import com.thatmg393.ts4j.world.contents.WorldMetadata;
import com.thatmg393.ts4j.world.contents.WorldProperties;
import io.github.rosemoe.sora.text.Content;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
	private static final Logger LOGGER = Logger.getLogger("MainActivity");
	
	private ActivityMainBinding binding;
	private TerrariaServer ts;
	private TerrariaWorld world;
	
	@Override
	protected void onCreate(Bundle previousData) {
		super.onCreate(previousData);
		
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		binding.logScreen.setEditable(true);
		
		rerouteTS4JLogger(); // reroute asap
		
		LOGGER.severe("first python now java aueghrh");
		LOGGER.info("TS4J Version " + BuildConfig.TS4J_VERSION + " installed!");
		
		ts = new TerrariaServer(6969, 1, "/storage/emulated/0/test.wld");
		
		binding.startTrServer.setOnClickListener(v -> {
			if (ts.isRunning()) {
				ts.stop();
				binding.startTrServer.setText("Start Terraria Server");
			} else {
				ts.start();
				binding.startTrServer.setText("Stop Terraria Server");
				
				if (ts.getWorld() != null) {
				    WorldMetadata m = ts.getWorld().getMetadata();
				    if (m != null) LOGGER.info(m.toString());
				    
				    WorldProperties p = ts.getWorld().getProperties();
				    if (p != null) LOGGER.info(p.toString());
				}
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ts.stop();
	}
	
	private void rerouteTS4JLogger() {
		SoraEditorLogHandler vh = new SoraEditorLogHandler(binding.logScreen);
		vh.setFormatter(new CustomLogFormatter());
		
		Logger root = Logger.getLogger("");
		root.setLevel(Level.ALL);
		root.removeHandler(root.getHandlers()[0]);
		root.addHandler(vh);
		
		try { root.addHandler(new FileHandler("/sdcard/ts4j.log")); }
		catch (IOException e) { }
	}
}
