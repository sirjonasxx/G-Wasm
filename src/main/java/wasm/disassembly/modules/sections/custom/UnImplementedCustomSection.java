package wasm.disassembly.modules.sections.custom;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.values.WName;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UnImplementedCustomSection extends CustomSection {

    private byte[] bytes;

    public UnImplementedCustomSection(BufferedInputStream in, long size, String name) throws IOException, InvalidOpCodeException {
        super(size, name);

        ByteArrayOutputStream nameOut = new ByteArrayOutputStream();
        WName.write(name, nameOut);

        bytes = new byte[(int)size - nameOut.toByteArray().length];
        in.read(bytes);
    }

    @Override
    protected void assemble3(OutputStream out) throws IOException, InvalidOpCodeException {
        out.write(bytes);
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
