package disassembly.instructions.memory;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MemInstr extends Instr {

    private MemArg memArg;

    public MemInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        memArg = new MemArg(in);
    }

    public MemInstr(InstrType instrType, MemArg memArg) throws IOException {
        super(instrType);
        this.memArg = memArg;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        memArg.assemble(out);
    }

    public MemArg getMemArg() {
        return memArg;
    }

    public void setMemArg(MemArg memArg) {
        this.memArg = memArg;
    }
}
