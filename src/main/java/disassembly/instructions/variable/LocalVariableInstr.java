package disassembly.instructions.variable;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.modules.indices.LocalIdx;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LocalVariableInstr extends Instr {

    private LocalIdx localIdx;

    public LocalVariableInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        localIdx = new LocalIdx(in);
    }

    public LocalVariableInstr(InstrType instrType, LocalIdx localIdx) throws IOException {
        super(instrType);
        this.localIdx = localIdx;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        localIdx.assemble(out);
    }

    public LocalIdx getLocalIdx() {
        return localIdx;
    }

    public void setLocalIdx(LocalIdx localIdx) {
        this.localIdx = localIdx;
    }
}
