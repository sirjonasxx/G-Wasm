package wasm.disassembly.conventions;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.WASMOpCode;
import wasm.disassembly.values.WUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Vector<B extends WASMOpCode> extends WASMOpCode {

    private List<B> elements;

    public Vector(BufferedInputStream in, Creator<B> creator) throws IOException, InvalidOpCodeException {
        long length = WUnsignedInt.read(in, 32);
        elements = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            elements.add(creator.create(in));
        }
    }

    public Vector(List<B> elements) {
        this.elements = elements;
    }

    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        WUnsignedInt.write(elements.size(), out, 32);
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
