package wasm.disassembly.modules.sections.start;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StartSection extends Section {

    public static final int START_SECTION_ID = 8;

    private Start start;

    public StartSection(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        super(in, START_SECTION_ID);
        start = new Start(in);
    }

    public StartSection(Start start) {
        super(START_SECTION_ID);
        this.start = start;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        start.assemble(out);
    }


    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }
}
