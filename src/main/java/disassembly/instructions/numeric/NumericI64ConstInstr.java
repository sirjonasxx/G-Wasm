package disassembly.instructions.numeric;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.values.WSignedLong;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NumericI64ConstInstr extends Instr {

    private long constValue;

    public NumericI64ConstInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        constValue = WSignedLong.read(in, 64);
    }

    public NumericI64ConstInstr(InstrType instrType, long constValue) throws IOException {
        super(instrType);
        this.constValue = constValue;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        WSignedLong.write(constValue, out, 64);
    }

    public long getConstValue() {
        return constValue;
    }

    public void setConstValue(long constValue) {
        this.constValue = constValue;
    }
}
