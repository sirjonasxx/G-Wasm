package disassembly.conventions;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Vector<B extends WASMOpCode> extends WASMOpCode {

    private List<B> elements;

    public Vector(BufferedInputStream in, Creator<B> creator) throws IOException, InvalidOpCodeException {
        OldWUnsignedInt length = new OldWUnsignedInt(in, 32);
        elements = new ArrayList<>();
        for (int i = 0; i < length.getUnsignedInt(); i++) {
            elements.add(creator.create(in));
        }
    }

    public Vector(List<B> elements) {
        this.elements = elements;
    }

    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        OldWUnsignedInt length = new OldWUnsignedInt(elements.size());
        length.assemble(out);
        for (B b : elements) {
            b.assemble(out);
        }
    }

    public List<B> getElements() {
        return elements;
    }

    public void setElements(List<B> elements) {
        this.elements = elements;
    }
}
