package wasm.disassembly.values;

import wasm.disassembly.WASMOpCode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WByte extends WASMOpCode {

    private int b;

    public WByte(BufferedInputStream inputStream) throws IOException {
        b = inputStream.read();
    }

    public WByte(int b) {
        this.b = b;
    }

    public void assemble(OutputStream out) throws IOException {
        out.write(b);
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
