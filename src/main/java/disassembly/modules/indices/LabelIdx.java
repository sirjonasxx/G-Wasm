package disassembly.modules.indices;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LabelIdx extends WASMOpCode {

    private OldWUnsignedInt l;

    public LabelIdx(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        l = new OldWUnsignedInt(in, 32);
    }

    public LabelIdx(OldWUnsignedInt l) {
        this.l = l;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        l.assemble(out);
    }

    public OldWUnsignedInt getL() {
        return l;
    }

    public void setL(OldWUnsignedInt l) {
        this.l = l;
    }
}
