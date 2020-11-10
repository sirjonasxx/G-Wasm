package disassembly.instructions.memory;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MemArg extends WASMOpCode {

    private OldWUnsignedInt align;
    private OldWUnsignedInt offset;

    public MemArg(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        align = new OldWUnsignedInt(in, 32);
        offset = new OldWUnsignedInt(in, 32);
    }

    public MemArg(OldWUnsignedInt align, OldWUnsignedInt offset) {
        this.align = align;
        this.offset = offset;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        align.assemble(out);
        offset.assemble(out);
    }

    public OldWUnsignedInt getAlign() {
        return align;
    }

    public void setAlign(OldWUnsignedInt align) {
        this.align = align;
    }

    public OldWUnsignedInt getOffset() {
        return offset;
    }

    public void setOffset(OldWUnsignedInt offset) {
        this.offset = offset;
    }
}
