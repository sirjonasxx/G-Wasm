package disassembly.modules.sections;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.values.WUnsignedInt;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class Section extends WASMOpCode {

    private int sectionId;
    private long size;

    public Section(BufferedInputStream in, int sectionId) throws IOException, InvalidOpCodeException {
        this.sectionId = sectionId;
        size = WUnsignedInt.read(in, 32);
    }

    public Section(int sectionId, long size) {
        this.sectionId = sectionId;
        this.size = size;
    }

    public Section(int sectionId) {
        this(sectionId, -1);
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        out.write(sectionId);

        ByteArrayOutputStream fakeOutputStream = new ByteArrayOutputStream();
        assemble2(fakeOutputStream);
        byte[] asbytes = fakeOutputStream.toByteArray();
        WUnsignedInt.write(asbytes.length, out, 32);
        out.write(asbytes);
    }

    protected abstract void assemble2(OutputStream out) throws IOException, InvalidOpCodeException;


    public int getSectionId() {
        return sectionId;
    }

    public long getSize() {
        return size;
    }
}
