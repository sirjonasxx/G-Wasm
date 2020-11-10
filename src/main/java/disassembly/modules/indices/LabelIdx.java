package disassembly.modules.indices;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.values.WUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LabelIdx extends WASMOpCode {

    private long l;

    public LabelIdx(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        l = WUnsignedInt.read(in, 32);
    }

    public LabelIdx(long l) {
        this.l = l;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        WUnsignedInt.write(l, out, 32);
    }

    public long getL() {
        return l;
    }

    public void setL(long l) {
        this.l = l;
    }
}
