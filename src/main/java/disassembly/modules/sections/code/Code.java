package disassembly.modules.sections.code;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Code extends WASMOpCode {

    private Func code;

    public Code(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        OldWUnsignedInt sizeInBytes = new OldWUnsignedInt(in, 32); // don't use
        code = new Func(in);
    }

    public Code(Func code) {
        this.code = code;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        ByteArrayOutputStream codeBuffer = new ByteArrayOutputStream();
        code.assemble(codeBuffer);
        byte[] codeInBytes = codeBuffer.toByteArray();
        new OldWUnsignedInt(codeInBytes.length).assemble(out);
        out.write(codeInBytes);
    }

    public Func getCode() {
        return code;
    }

    public void setCode(Func code) {
        this.code = code;
    }
}
