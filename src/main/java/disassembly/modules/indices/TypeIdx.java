package disassembly.modules.indices;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TypeIdx extends WASMOpCode {

    private OldWUnsignedInt x;

    public TypeIdx(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        x = new OldWUnsignedInt(in, 32);
    }

    public TypeIdx(OldWUnsignedInt x) {
        this.x = x;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        x.assemble(out);
    }

    public OldWUnsignedInt getX() {
        return x;
    }

    public void setX(OldWUnsignedInt x) {
        this.x = x;
    }
}
