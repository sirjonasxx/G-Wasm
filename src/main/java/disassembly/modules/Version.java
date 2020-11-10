package disassembly.modules;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Version extends WASMOpCode {

    byte[] version;


    public Version(BufferedInputStream in) throws IOException {
        version = new byte[4];
        in.read(version);
    }

    public Version(byte[] version) {
        this.version = version;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        out.write(version);
    }

    public byte[] getVersion() {
        return version;
    }

    public void setVersion(byte[] version) {
        this.version = version;
    }
}
