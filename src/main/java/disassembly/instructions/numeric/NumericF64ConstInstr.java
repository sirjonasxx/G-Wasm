package disassembly.instructions.numeric;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.values.old.OldWFloatingPoint;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NumericF64ConstInstr extends Instr {

    private OldWFloatingPoint float64;

    public NumericF64ConstInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        float64 = new OldWFloatingPoint(in, 64);
    }

    public NumericF64ConstInstr(InstrType instrType, OldWFloatingPoint floatingPoint) throws IOException {
        super(instrType);
        this.float64 = floatingPoint;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException {
        float64.assemble(out);
    }

    public OldWFloatingPoint getFloat64() {
        return float64;
    }

    public void setFloat64(OldWFloatingPoint float64) {
        this.float64 = float64;
    }
}
