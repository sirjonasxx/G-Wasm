package disassembly.modules.sections;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class Section extends WASMOpCode {

    private int sectionId;
    private OldWUnsignedInt size;

    public Section(BufferedInputStream in, int sectionId) throws IOException, InvalidOpCodeException {
        this.sectionId = sectionId;
        size = new OldWUnsignedInt(in, 32);
    }

    public Section(int sectionId, OldWUnsignedInt size) {
        this.sectionId = sectionId;
        this.size = size;
    }

    public Section(int sectionId) {
        this(sectionId, null);
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        out.write(sectionId);

        ByteArrayOutputStream fakeOutputStream = new ByteArrayOutputStream();
        assemble2(fakeOutputStream);
        byte[] asbytes = fakeOutputStream.toByteArray();
        new OldWUnsignedInt(asbytes.length).assemble(out);
        out.write(asbytes);
    }

    protected abstract void assemble2(OutputStream out) throws IOException, InvalidOpCodeException;


    public int getSectionId() {
        return sectionId;
    }

    public OldWUnsignedInt getSize() {
        return size;
    }
}
