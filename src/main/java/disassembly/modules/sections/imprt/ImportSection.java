package disassembly.modules.sections.imprt;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.conventions.Vector;
import disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ImportSection extends Section {

    public static final int IMPORT_SECTION_ID = 2;

    private Vector<Import> functionTypes;


    public ImportSection(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        super(in, IMPORT_SECTION_ID);
        functionTypes = new Vector<>(in, Import::new);
    }

    public ImportSection(List<Import> functionTypes) {
        super(IMPORT_SECTION_ID);
        this.functionTypes = new Vector<>(functionTypes);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        functionTypes.assemble(out);
    }

    public List<Import> getFunctionTypes() {
        return functionTypes.getElements();
    }

    public void setFunctionTypes(List<Import> functionTypes) {
        this.functionTypes = new Vector<>(functionTypes);
    }
}
