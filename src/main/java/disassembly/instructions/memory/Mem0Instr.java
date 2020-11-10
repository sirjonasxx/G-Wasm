package disassembly.instructions.memory;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Mem0Instr extends Instr {
    public Mem0Instr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        if (in.read() != 0x00) {
            throw new InvalidOpCodeException("Unexpected non-zero byte");
        }
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException {
        out.write(0x00);
    }
}
