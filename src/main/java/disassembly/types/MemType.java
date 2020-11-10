package disassembly.types;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MemType extends WASMOpCode {

    private Limits limits;

    public MemType(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        limits = new Limits(in);
    }

    public MemType(Limits limits) {
        this.limits = limits;
    }

    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        limits.assemble(out);
    }

    public Limits getLimits() {
        return limits;
    }

    public void setLimits(Limits limits) {
        this.limits = limits;
    }
}
