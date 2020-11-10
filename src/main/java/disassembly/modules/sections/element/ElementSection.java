package disassembly.modules.sections.element;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.conventions.Vector;
import disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ElementSection extends Section {

    public static final int ELEMENT_SECTION_ID = 9;

    private Vector<Elem> elementSegments;

    public ElementSection(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        super(in, ELEMENT_SECTION_ID);
        elementSegments = new Vector<>(in, Elem::new);
    }

    public ElementSection(List<Elem> elementSegments) {
        super(ELEMENT_SECTION_ID);
        this.elementSegments = new Vector<>(elementSegments);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        elementSegments.assemble(out);
    }

    public List<Elem> getElementSegments() {
        return elementSegments.getElements();
    }

    public void setElementSegments(List<Elem> elementSegments) {
        this.elementSegments = new Vector<>(elementSegments);
    }
}
