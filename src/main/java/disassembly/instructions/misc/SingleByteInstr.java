package disassembly.instructions.misc;

import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SingleByteInstr extends Instr {
    public SingleByteInstr(BufferedInputStream in, InstrType instrType) throws IOException {
        super(instrType);
    }

    @Override
    protected void assemble2(OutputStream out) {
        // do nothing
    }
}
