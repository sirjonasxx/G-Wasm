package disassembly.instructions.numeric;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.values.WFloat;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NumericF32ConstInstr extends Instr {

    private float constValue;

    public NumericF32ConstInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        constValue = WFloat.read(in);
    }

    public NumericF32ConstInstr(InstrType instrType, float constValue) throws IOException {
        super(instrType);
        this.constValue = constValue;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException {
        WFloat.write(constValue, out);
    }

    public float getConstValue() {
        return constValue;
    }

    public void setConstValue(float constValue) {
        this.constValue = constValue;
    }
}
