package com.thatmg393.ts4j.utils;

import com.google.common.primitives.UnsignedLong;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/** BinaryReader
 * Purely based on C#'s BinaryReader
 * </b>Uses C# type names</b>
 *
 * @reason For C# interop
 * @author ThatMG393
 */
public class BinaryReader implements AutoCloseable {
    private static final Logger LOGGER = Logger.getLogger("BinaryReader");
    
    private final DataInputStream in;
    private final byte[] buffer = new byte[8]; // Long = 8
    private final ByteBuffer byteBuffer = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN);
	private final Charset encoding;
	
    private long position = 0;

    public BinaryReader(InputStream inputStream, String charsetName) {
        this.in = new DataInputStream(new BufferedInputStream(inputStream));
		this.encoding = Charset.forName(charsetName);
    }

    public byte readByte() {
        try {
            position++;
            return in.readByte();
        } catch (EOFException e) {
            throw new BinaryReaderException("Reached end of stream while reading byte", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to read byte", e);
            return 0;
        }
    }

    public short readInt16() {
        try {
            return readLittleEndian(Short.BYTES, byteBuffer::getShort);
        } catch (EOFException e) {
            throw new BinaryReaderException("Reached end of stream while reading short", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to read short", e);
            return 0;
        }
    }

    public int readInt32() {
        try {
            return readLittleEndian(Integer.BYTES, byteBuffer::getInt);
        } catch (EOFException e) {
            throw new BinaryReaderException("Reached end of stream while reading int", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to read int", e);
            return 0;
        }
    }

    public long readInt64() {
        try {
            return readLittleEndian(Long.BYTES, byteBuffer::getLong);
        } catch (EOFException e) {
            throw new BinaryReaderException("Reached end of stream while reading long", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to read long", e);
            return 0;
        }
    }

    public float readSingle() {
        try {
            return readLittleEndian(Float.BYTES, byteBuffer::getFloat);
        } catch (EOFException e) {
            throw new BinaryReaderException("Reached end of stream while reading float", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to read float", e);
            return 0;
        }
    }

    public double readDouble() {
        try {
            return readLittleEndian(Double.BYTES, byteBuffer::getDouble);
        } catch (EOFException e) {
            throw new BinaryReaderException("Reached end of stream while reading double", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to read double", e);
            return 0;
        }
    }

    public boolean readBoolean() {
        try {
            return in.readBoolean();
        } catch (EOFException e) {
            throw new BinaryReaderException("Reached end of stream while reading boolean", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to read boolean", e);
            return false;
        }
    }

    public char readChar() {
        try {
            return readLittleEndian(Character.BYTES, byteBuffer::getChar);
        } catch (EOFException e) {
            throw new BinaryReaderException("Reached end of stream while reading char", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to read char", e);
            return '\0';
        }
    }

    public short readUInt8() {
        return (short) (readByte() & 0xFF);
    }

    public int readUInt16() {
        return Short.toUnsignedInt(readInt16());
    }

    public long readUInt32() {
        return Integer.toUnsignedLong(readInt32());
    }

    public BigInteger readUInt64() {
        return UnsignedLong.fromLongBits(readInt64()).bigIntegerValue();
    }

    public String readString() {
        try {
            return new String(readBytes(read7BitEncodedInt()), encoding);
        } catch (EOFException e) {
            throw new BinaryReaderException("Reached end of stream while reading string", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to read string", e);
            return "";
        }
    }

    public byte[] readBytes(int count) {
        try {
            position += count;
            return in.readNBytes(count);
        } catch (EOFException e) {
            throw new BinaryReaderException("Reached end of stream while reading bytes", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to read bytes", e);
            return new byte[0];
        }
    }

    public void skip(int by) {
        if (by < 0) {
            LOGGER.log(Level.WARNING, "Attempted to skip backward");
            return;
        }
        try {
            long skipped = in.skip(by);
            position += skipped;
            if (skipped < by) {
                throw new BinaryReaderException("Reached end of stream while skipping", new EOFException("e"));
            }
        } catch (EOFException e) {
            throw new BinaryReaderException("Reached end of stream while skipping", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to skip", e);
        }
    }

    public void skipTo(long offset) {
        if (offset < position) {
            LOGGER.log(Level.WARNING, "Attempted to skip backward");
            return;
        }
        skip((int) (offset - position));
    }

    public long getPosition() {
        return position;
    }

    private int read7BitEncodedInt() throws IOException {
        int result = 0;
        int shift = 0;
        byte b;
        do {
            b = readByte();
            result |= (b & 0x7F) << shift;
            shift += 7;
        } while ((b & 0x80) != 0);
        return result;
    }

    private <T> T readLittleEndian(int bytes, ByteBufferReader<T> reader) throws IOException {
        in.readFully(buffer, 0, bytes);
		position += bytes;
        return reader.read(0);
    }

    @FunctionalInterface
    private interface ByteBufferReader<T> {
        T read(int index);
    }

    @Override
    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to close reader", e);
        }
    }

    public static class BinaryReaderException extends RuntimeException {
        public BinaryReaderException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
