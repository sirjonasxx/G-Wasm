package disassembly.instructions.numeric;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.values.old.OldWSignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NumericI32ConstInstr extends Instr {

    private OldWSignedInt signedInt32;

    public NumericI32ConstInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        signedInt32 = new OldWSignedInt(in, 32);
    }

    public NumericI32ConstInstr(InstrType instrType, OldWSignedInt signedInt32) throws IOException {
        super(instrType);
        this.signedInt32 = signedInt32;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException {
        signedInt32.assemble(out);
    }

    public OldWSignedInt getSignedInt32() {
        return signedInt32;
    }

    public void setSignedInt32(OldWSignedInt signedInt32) {
        this.signedInt32 = signedInt32;
    }
}
