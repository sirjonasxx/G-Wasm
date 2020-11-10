package disassembly.values.old;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class OldWFloatingPoint extends WASMOpCode {

    private final int N;
    private byte[] bytes;

    public OldWFloatingPoint(BufferedInputStream in, int N) throws IOException, InvalidOpCodeException {
        this.N = N;
        if (N != 64 && N != 32) {
            throw new InvalidOpCodeException("Invalid floating point size");
        }
        bytes = new byte[N/8];
        in.read(bytes);
    }

    public OldWFloatingPoint(float v) {
        this.N = 32;
        bytes = new byte[4];
        ByteBuffer.wrap(bytes).putFloat(v);
    }

    public OldWFloatingPoint(double v) {
        this.N = 64;
        bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(v);
    }

    public void assemble(OutputStream out) throws IOException {
        out.write(bytes);
    }

    public double get() throws InvalidOpCodeException {
        if (N == 32) {
            return getFloat();
        }
        else if (N == 64) {
            return getDouble();
        }
        else {
            throw new InvalidOpCodeException("Invalid floating point size");
        }
    }

    public float getFloat() throws InvalidOpCodeException {
        if (N != 32) throw new InvalidOpCodeException("Invalid floating point size");
        return ByteBuffer.wrap(bytes).getFloat();
    }

    public double getDouble() throws InvalidOpCodeException {
        if (N != 64) throw new InvalidOpCodeException("Invalid floating point size");
        return ByteBuffer.wrap(bytes).getDouble();
    }

    public void put(float v) throws InvalidOpCodeException {
        if (N != 32) throw new InvalidOpCodeException("Invalid floating point size");
        ByteBuffer.wrap(bytes).putFloat(v);
    }

    public void put(double v) throws InvalidOpCodeException {
        if (N != 64) throw new InvalidOpCodeException("Invalid floating point size");
        ByteBuffer.wrap(bytes).putDouble(v);
    }


    public static void main(String[] args) {

    }
}
