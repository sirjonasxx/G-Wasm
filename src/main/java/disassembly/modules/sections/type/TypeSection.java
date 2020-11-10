package disassembly.modules.sections.type;

import disassembly.InvalidOpCodeException;
import disassembly.conventions.Vector;
import disassembly.modules.sections.Section;
import disassembly.types.FuncType;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class TypeSection extends Section {

    public static final int TYPE_SECTION_ID = 1;

    private Vector<FuncType> functionTypes;


    public TypeSection(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        super(in, TYPE_SECTION_ID);
        functionTypes = new Vector<>(in, FuncType::new);
    }

    public TypeSection(List<FuncType> functionTypes) {
        super(TYPE_SECTION_ID);
        this.functionTypes = new Vector<>(functionTypes);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        functionTypes.assemble(out);
    }

    public List<FuncType> getFunctionTypes() {
        return functionTypes.getElements();
    }

    public void setFunctionTypes(List<FuncType> functionTypes) {
        this.functionTypes = new Vector<>(functionTypes);
    }
}
