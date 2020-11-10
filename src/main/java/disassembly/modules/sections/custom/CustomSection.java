package disassembly.modules.sections.custom;

import disassembly.InvalidOpCodeException;
import disassembly.modules.sections.Section;
import disassembly.values.WName;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public abstract class CustomSection extends Section {

    public static final int CUSTOM_SECTION_ID = 0;

    private WName name;

    public CustomSection(long size, WName name) throws IOException, InvalidOpCodeException {
        super(CUSTOM_SECTION_ID, size);
        this.name = name;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        out.write(name.getValue().getBytes(StandardCharsets.UTF_8));
        assemble3(out);
    }

    protected abstract void assemble3(OutputStream out) throws IOException, InvalidOpCodeException;

    public WName getName() {
        return name;
    }

    public void setName(WName name) {
        this.name = name;
    }
}
