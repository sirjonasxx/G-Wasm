package wasm.disassembly.modules.sections.data;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.conventions.Vector;
import wasm.disassembly.modules.Module;
import wasm.disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class DataSection extends Section {

    public static final int DATA_SECTION_ID = 11;


    private Vector<Data> dataSegments;

    public DataSection(BufferedInputStream in, Module module) throws IOException, InvalidOpCodeException {
        super(in, DATA_SECTION_ID);
        dataSegments = new Vector<>(in, Data::new, module);
    }

    public DataSection(List<Data> dataSegments) {
        super(DATA_SECTION_ID);
        this.dataSegments = new Vector<>(dataSegments);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        dataSegments.assemble(out);
    }


    public List<Data> getDataSegments() {
        return dataSegments.getElements();
    }

    public void setDataSegments(List<Data> dataSegments) {
        this.dataSegments = new Vector<>(dataSegments);
    }
}
