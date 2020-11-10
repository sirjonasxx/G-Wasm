package disassembly.values.old;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class OldWUnsignedInt extends WASMOpCode {

    private final int N;
    private byte[] bytes;


    public OldWUnsignedInt(BufferedInputStream in, int N) throws InvalidOpCodeException, IOException {
        List<Byte> buffer = new ArrayList<>();

        int cur;
        int count = 0;
        int limit = N/7 + (N%7 != 0 ? 1 : 0);

        do {
            int read = in.read();
            buffer.add((byte)read);
            cur = read & 0xff;
            count++;
        } while (((cur & 0x80) == 0x80) && count < limit);

        if ((cur & 0x80) == 0x80) {
            throw new InvalidOpCodeException("invalid LEB128 sequence");
        }

        this.N = N;
        bytes = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            bytes[i] = buffer.get(i);
        }
    }

    public OldWUnsignedInt(long v) throws InvalidOpCodeException {
        N = 32;
        put(v);
    }

    public OldWUnsignedInt(BigInteger v) throws InvalidOpCodeException {
        N = 64;
        put(v);
    }

    public void assemble(OutputStream out) throws IOException {
        out.write(bytes);
    }

    public long getUnsignedInt() throws InvalidOpCodeException {
        if (N != 32) throw new InvalidOpCodeException("Invalid integer size");

        long result = 0;
        long cur;
        int count = 0;
        int limit = N/7 + (N%7 != 0 ? 1 : 0);

        do {
            cur = bytes[count] & 0xff;
            result |= (cur & 0x7f) << (count * 7);
            count++;
        } while (((cur & 0x80) == 0x80) && count < limit);

        if ((cur & 0x80) == 0x80) {
            throw new InvalidOpCodeException("invalid LEB128 sequence");
        }

        return result;
    }

    public BigInteger getUnsignedLong() throws InvalidOpCodeException {
        if (N != 64) throw new InvalidOpCodeException("Invalid integer size");

        BigInteger result = BigInteger.ZERO;
        long cur;
        int count = 0;
        int limit = N/7 + (N%7 != 0 ? 1 : 0);

        do {
            cur = bytes[count] & 0xff;
            result = result.or(BigInteger.valueOf(cur & 0x7f).shiftLeft(count * 7));
            count++;
        } while (((cur & 0x80) == 0x80) && count < limit);

        if ((cur & 0x80) == 0x80) {
            throw new InvalidOpCodeException("invalid LEB128 sequence");
        }

        return result;
    }

    public void put(long value) throws InvalidOpCodeException {
        if (N != 32) throw new InvalidOpCodeException("Invalid integer size");

        long remaining = value >>> 7;

        List<Byte> buffer = new ArrayList<>();
        while (remaining != 0) {
            buffer.add((byte) ((value & 0x7f) | 0x80));
            value = remaining;
            remaining >>>= 7;
        }
        buffer.add((byte) (value & 0x7f));

        bytes = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            bytes[i] = buffer.get(i);
        }
    }

    public void put(BigInteger value) throws InvalidOpCodeException {
        if (N != 64) throw new InvalidOpCodeException("Invalid integer size");

        long remaining = value.shiftRight(7).longValueExact();
        long l_value = -1;

        List<Byte> buffer = new ArrayList<>();
        while (remaining != 0) {
            if (buffer.size() == 0) {
                buffer.add((byte) ((value.longValue() & 0x7f) | 0x80));
            }
            else {
                buffer.add((byte) ((l_value & 0x7f) | 0x80));
            }
            l_value = remaining;
            remaining >>>= 7;
        }
        if (buffer.size() == 0) {
            buffer.add((byte) (value.longValue() & 0x7f));
        }
        else {
            buffer.add((byte) (l_value & 0x7f));
        }

        bytes = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            bytes[i] = buffer.get(i);
        }
    }


    public static void main(String[] args) throws InvalidOpCodeException {
        OldWUnsignedInt u = new OldWUnsignedInt(45454);
        System.out.println(u.getUnsignedInt());

        BigInteger test = BigInteger.valueOf(8424526546848754651L).multiply(BigInteger.valueOf(2L));
        System.out.println(test);

        OldWUnsignedInt u2 = new OldWUnsignedInt(test);
        System.out.println(u2.getUnsignedLong().toString());
    }
}
