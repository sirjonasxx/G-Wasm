package disassembly.instructions.variable;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.modules.indices.GlobalIdx;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GlobalVariableInstr extends Instr {

    private GlobalIdx globalIdx;

    public GlobalVariableInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        globalIdx = new GlobalIdx(in);
    }

    public GlobalVariableInstr(InstrType instrType, GlobalIdx globalIdx) throws IOException {
        super(instrType);
        this.globalIdx = globalIdx;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        globalIdx.assemble(out);
    }

    public GlobalIdx getGlobalIdx() {
        return globalIdx;
    }

    public void setGlobalIdx(GlobalIdx globalIdx) {
        this.globalIdx = globalIdx;
    }
}
