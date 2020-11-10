package disassembly.instructions.control;

import disassembly.InvalidOpCodeException;
import disassembly.conventions.Creator;
import disassembly.conventions.Vector;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.modules.indices.LabelIdx;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BranchTableInstr extends Instr {

    private Vector<LabelIdx> table;
    private LabelIdx labelIdx;

    public BranchTableInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        table = new Vector<>(in, LabelIdx::new);
        labelIdx = new LabelIdx(in);
    }

    public BranchTableInstr(InstrType instrType, Vector<LabelIdx> table, LabelIdx labelIdx) throws IOException {
        super(instrType);
        this.table = table;
        this.labelIdx = labelIdx;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        table.assemble(out);
        labelIdx.assemble(out);
    }

    public Vector<LabelIdx> getTable() {
        return table;
    }

    public void setTable(Vector<LabelIdx> table) {
        this.table = table;
    }

    public LabelIdx getLabelIdx() {
        return labelIdx;
    }

    public void setLabelIdx(LabelIdx labelIdx) {
        this.labelIdx = labelIdx;
    }
}
