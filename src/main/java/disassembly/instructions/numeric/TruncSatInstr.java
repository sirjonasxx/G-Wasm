package disassembly.instructions.numeric;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TruncSatInstr extends Instr {

    private OldWUnsignedInt type;

    public TruncSatInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        type = new OldWUnsignedInt(in, 32);
        long value = type.getUnsignedInt();
        if (value < 0 || value > 7) {
            throw new InvalidOpCodeException("Invalid opcode");
        }
    }

    public TruncSatInstr(InstrType instrType, OldWUnsignedInt type) throws IOException {
        super(instrType);
        this.type = type;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException {
        type.assemble(out);
    }

    public OldWUnsignedInt getType() {
        return type;
    }

    public void setType(OldWUnsignedInt type) {
        this.type = type;
    }
}
