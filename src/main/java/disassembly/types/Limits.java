package disassembly.types;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Limits extends WASMOpCode {

    private long min;
    private long max;

    public Limits(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        int flag = in.read();
        if (flag == 0x00) {
            min = new OldWUnsignedInt(in, 32).getUnsignedInt();
            max = -1;
        }
        else if (flag == 0x01) {
            min = new OldWUnsignedInt(in, 32).getUnsignedInt();
            max = new OldWUnsignedInt(in, 32).getUnsignedInt();
        }
        else throw new InvalidOpCodeException("Function types must be encoded with 0x00 or 0x01");
    }

    public Limits(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        out.write(max == -1 ? 0x00 : 0x01);
        new OldWUnsignedInt(min).assemble(out);
        if (max != -1) {
            new OldWUnsignedInt(max).assemble(out);
        }
    }

    public long getMax() {
        return max;
    }

    public long getMin() {
        return min;
    }

    public boolean hasMax() {
        return max != -1;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public void setMax(long max) {
        this.max = max;
    }
}
