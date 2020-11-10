package disassembly.modules.sections.custom;

import disassembly.InvalidOpCodeException;
import disassembly.values.WName;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UnImplementedCustomSection extends CustomSection {

    private byte[] bytes;

    public UnImplementedCustomSection(BufferedInputStream in, long size, WName name) throws IOException, InvalidOpCodeException {
        super(size, name);
        bytes = new byte[(int)size - name.getValue().getBytes(StandardCharsets.UTF_8).length];
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
