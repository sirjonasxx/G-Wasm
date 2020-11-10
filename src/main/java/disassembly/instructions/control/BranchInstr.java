package disassembly.instructions.control;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.modules.indices.LabelIdx;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BranchInstr extends Instr {

    private LabelIdx labelIdx;

    public BranchInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        labelIdx = new LabelIdx(in);
    }

    public BranchInstr(InstrType instrType, LabelIdx labelIdx) throws IOException {
        super(instrType);
        this.labelIdx = labelIdx;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        labelIdx.assemble(out);
    }

    public LabelIdx getLabelIdx() {
        return labelIdx;
    }

    public void setLabelIdx(LabelIdx labelIdx) {
        this.labelIdx = labelIdx;
    }
}
