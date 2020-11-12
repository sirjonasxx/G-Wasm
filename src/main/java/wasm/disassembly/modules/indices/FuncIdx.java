package wasm.disassembly.modules.indices;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.WASMOpCode;
import wasm.disassembly.values.WUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FuncIdx extends WASMOpCode {


    public static int OFFSET = 0;

    private long x;

    public FuncIdx(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        x = WUnsignedInt.read(in, 32);
    }

    public FuncIdx(long x) {
        this.x = x - OFFSET;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        WUnsignedInt.write(x + OFFSET, out, 32);
    }

    public long getX() {
        return x + OFFSET;
    }

    public void setX(long x) {
        this.x = x - OFFSET;
    }
}
