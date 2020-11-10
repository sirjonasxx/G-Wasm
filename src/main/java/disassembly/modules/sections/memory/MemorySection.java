package disassembly.modules.sections.memory;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.conventions.Vector;
import disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MemorySection extends Section {

    public static final int MEMORY_SECTION_ID = 5;

    private Vector<Mem> memories;


    public MemorySection(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        super(in, MEMORY_SECTION_ID);
        memories = new Vector<>(in, Mem::new);
    }

    public MemorySection(List<Mem> memories) {
        super(MEMORY_SECTION_ID);
        this.memories = new Vector<>(memories);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        memories.assemble(out);
    }


    public List<Mem> getMemories() {
        return memories.getElements();
    }

    public void setMemories(List<Mem> memories) {
        this.memories = new Vector<>(memories);
    }
}
