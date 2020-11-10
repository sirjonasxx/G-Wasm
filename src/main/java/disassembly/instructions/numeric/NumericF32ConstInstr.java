package disassembly.instructions.numeric;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.values.old.OldWFloatingPoint;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NumericF32ConstInstr extends Instr {

    private OldWFloatingPoint float32;

    public NumericF32ConstInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        float32 = new OldWFloatingPoint(in, 32);
    }

    public NumericF32ConstInstr(InstrType instrType, OldWFloatingPoint float32) throws IOException {
        super(instrType);
        this.float32 = float32;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException {
        float32.assemble(out);
    }

    public OldWFloatingPoint getFloat32() {
        return float32;
    }

    public void setFloat32(OldWFloatingPoint float32) {
        this.float32 = float32;
    }
}
