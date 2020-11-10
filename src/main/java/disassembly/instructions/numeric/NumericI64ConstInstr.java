package disassembly.instructions.numeric;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.values.old.OldWSignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NumericI64ConstInstr extends Instr {

    private OldWSignedInt signedInt64;

    public NumericI64ConstInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        signedInt64 = new OldWSignedInt(in, 64);
    }

    public NumericI64ConstInstr(InstrType instrType, OldWSignedInt signedInt64) throws IOException {
        super(instrType);
        this.signedInt64 = signedInt64;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException {
        signedInt64.assemble(out);
    }

    public OldWSignedInt getSignedInt64() {
        return signedInt64;
    }

    public void setSignedInt64(OldWSignedInt signedInt64) {
        this.signedInt64 = signedInt64;
    }
}
