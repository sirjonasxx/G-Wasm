package disassembly.values;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class WName extends WASMOpCode {

    private String value;

    public WName(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        long length = WUnsignedInt.read(in, 32);
        byte[] arr = new byte[(int)length];
        in.read(arr);
        value = new String(arr, StandardCharsets.UTF_8);
    }

    public WName(String value) {
        this.value = value;
    }

    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        WUnsignedInt.write(bytes.length, out, 32);
        out.write(bytes);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
