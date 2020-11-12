package wasm.disassembly.modules.sections.imprt;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.conventions.Vector;
import wasm.disassembly.modules.indices.FuncIdx;
import wasm.disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ImportSection extends Section {

    public static final int IMPORT_SECTION_ID = 2;

    private Vector<Import> imports;


    public ImportSection(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        super(in, IMPORT_SECTION_ID);
        imports = new Vector<>(in, Import::new);
    }

    public ImportSection(List<Import> imports) {
        super(IMPORT_SECTION_ID);
        this.imports = new Vector<>(imports);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        imports.assemble(out);
    }

    public List<Import> getImports() {
        return imports.getElements();
    }

    public void setImports(List<Import> imports) {
        this.imports = new Vector<>(imports);
    }

    public void importFunctions(List<Import> functions) {
        List<Import> newImports = new ArrayList<>(functions);
        newImports.addAll(imports.getElements());
        
        setImports(newImports);
        FuncIdx.OFFSET += functions.size();
    }
}
