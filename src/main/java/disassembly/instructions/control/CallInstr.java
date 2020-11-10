package disassembly.instructions.control;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.modules.indices.FuncIdx;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CallInstr extends Instr {

    private FuncIdx funcIdx;

    public CallInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        funcIdx = new FuncIdx(in);
    }

    public CallInstr(InstrType instrType, FuncIdx funcIdx) throws IOException {
        super(instrType);
        this.funcIdx = funcIdx;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        funcIdx.assemble(out);
    }

    public FuncIdx getFuncIdx() {
        return funcIdx;
    }

    public void setFuncIdx(FuncIdx funcIdx) {
        this.funcIdx = funcIdx;
    }
}
