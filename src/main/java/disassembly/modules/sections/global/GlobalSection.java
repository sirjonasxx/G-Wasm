package disassembly.modules.sections.global;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.conventions.Vector;
import disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class GlobalSection extends Section {

    public static final int GLOBAL_SECTION_ID = 6;

    private Vector<Global> globals;


    public GlobalSection(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        super(in, GLOBAL_SECTION_ID);
        globals = new Vector<>(in, Global::new);
    }

    public GlobalSection(List<Global> globals) {
        super(GLOBAL_SECTION_ID);
        this.globals = new Vector<>(globals);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        globals.assemble(out);
    }

    public List<Global> getGlobals() {
        return globals.getElements();
    }

    public void setGlobals(List<Global> globals) {
        this.globals = new Vector<>(globals);
    }
}
