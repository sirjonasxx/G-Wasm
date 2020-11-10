package disassembly.modules.sections.data;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.conventions.Vector;
import disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class DataSection extends Section {

    public static final int DATA_SECTION_ID = 11;


    private Vector<Data> dataSegments;

    public DataSection(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        super(in, DATA_SECTION_ID);
        dataSegments = new Vector<>(in, Data::new);
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
