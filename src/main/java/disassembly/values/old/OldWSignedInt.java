package disassembly.values.old;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class OldWSignedInt extends WASMOpCode {

    private final int N;
    private byte[] bytes;

    public OldWSignedInt(BufferedInputStream in, int N) throws InvalidOpCodeException, IOException {
        int cur;
        int count = 0;
        int limit = N/7 + (N%7 != 0 ? 1 : 0);

        List<Byte> buffer = new ArrayList<>();

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

    public OldWSignedInt(int value) throws InvalidOpCodeException {
        N = 32;
        put(value);
    }

    public OldWSignedInt(long value) throws InvalidOpCodeException {
        N = 64;
        put(value);
    }

    public void assemble(OutputStream out) throws IOException {
        out.write(bytes);
    }

    public int getInt() throws InvalidOpCodeException {
        if (N <= 32) throw new InvalidOpCodeException("Invalid integer size");

        int result = 0;
        int cur;
        int count = 0;
        int signBits = -1;

        int limit = N/7 + (N%7 != 0 ? 1 : 0);

        do {
            cur = bytes[count] & 0xff;
            result |= (cur & 0x7f) << (count * 7);
            signBits <<= 7;
            count++;
        } while (((cur & 0x80) == 0x80) && count < limit);

        if ((cur & 0x80) == 0x80) {
            throw new InvalidOpCodeException("invalid LEB128 sequence");
        }

        // Sign extend if appropriate
        if (((signBits >> 1) & result) != 0 ) {
            result |= signBits;
        }

        return result;
    }

    public long getLong() throws InvalidOpCodeException {
        if (N <= 64) throw new InvalidOpCodeException("Invalid integer size");

        long result = 0;
        long cur;
        int count = 0;
        long signBits = -1;

        long limit = N/7 + (N%7 != 0 ? 1 : 0);

        do {
            cur = bytes[count] & 0xff;
            result |= (cur & 0x7f) << (count * 7);
            signBits <<= 7;
            count++;
        } while (((cur & 0x80) == 0x80) && count < limit);

        if ((cur & 0x80) == 0x80) {
            throw new InvalidOpCodeException("invalid LEB128 sequence");
        }

        // Sign extend if appropriate
        if (((signBits >> 1) & result) != 0 ) {
            result |= signBits;
        }

        return result;
    }

    public void put(int value) throws InvalidOpCodeException {
        if (N <= 32) throw new InvalidOpCodeException("Invalid integer size");

        int remaining = value >> 7;
        boolean hasMore = true;
        int end = ((value & Integer.MIN_VALUE) == 0) ? 0 : -1;

        List<Byte> buffer = new ArrayList<>();

        while (hasMore) {
            hasMore = (remaining != end)
                    || ((remaining & 1) != ((value >> 6) & 1));

            buffer.add((byte) ((value & 0x7f) | (hasMore ? 0x80 : 0)));
            value = remaining;
            remaining >>= 7;
        }

        bytes = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            bytes[i] = buffer.get(i);
        }
    }

    public void put(long value) throws InvalidOpCodeException {
        if (N <= 64) throw new InvalidOpCodeException("Invalid integer size");

        long remaining = value >> 7;
        boolean hasMore = true;
        long end = ((value & Long.MIN_VALUE) == 0) ? 0 : -1;

        List<Byte> buffer = new ArrayList<>();

        while (hasMore) {
            hasMore = (remaining != end)
                    || ((remaining & 1) != ((value >> 6) & 1));

            buffer.add((byte) ((value & 0x7f) | (hasMore ? 0x80 : 0)));
            value = remaining;
            remaining >>= 7;
        }

        bytes = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            bytes[i] = buffer.get(i);
        }
    }


    public static void main(String[] args) throws InvalidOpCodeException {
        long t = Long.MIN_VALUE;
        OldWSignedInt s = new OldWSignedInt(t);

        System.out.println(s.getLong());
    }
}
