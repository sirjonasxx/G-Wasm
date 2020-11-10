package disassembly.modules.sections.data;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.instructions.Expression;
import disassembly.modules.indices.MemIdx;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Data extends WASMOpCode {

    private MemIdx dataMemId;
    private Expression offset;
    private byte[] data;


    public Data(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        dataMemId = new MemIdx(in);
        offset = new Expression(in);
        OldWUnsignedInt length = new OldWUnsignedInt(in, 32);
        data = new byte[(int)length.getUnsignedInt()];
        in.read(data);
    }

    public Data(MemIdx dataMemId, Expression offset, byte[] data) {
        this.dataMemId = dataMemId;
        this.offset = offset;
        this.data = data;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        dataMemId.assemble(out);
        offset.assemble(out);
        new OldWUnsignedInt(data.length).assemble(out);
        out.write(data);
    }


    public MemIdx getDataMemId() {
        return dataMemId;
    }

    public void setDataMemId(MemIdx dataMemId) {
        this.dataMemId = dataMemId;
    }

    public Expression getOffset() {
        return offset;
    }

    public void setOffset(Expression offset) {
        this.offset = offset;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
