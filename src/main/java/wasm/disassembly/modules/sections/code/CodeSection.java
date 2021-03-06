package wasm.disassembly.modules.sections.code;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.conventions.Vector;
import wasm.disassembly.modules.Module;
import wasm.disassembly.modules.indices.FuncIdx;
import wasm.disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CodeSection extends Section {

    public static final int CODE_SECTION_ID = 10;


    private Vector<Code> codesEntries;

    public CodeSection(BufferedInputStream in, Module module) throws IOException, InvalidOpCodeException {
        super(in, module, CODE_SECTION_ID);
        codesEntries = new Vector<>(in, Code::new, module);
    }

    public CodeSection(Module module, List<Code> codesEntries) {
        super(module, CODE_SECTION_ID);
        this.codesEntries = new Vector<>(codesEntries);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        codesEntries.assemble(out);
    }

    public Code getCodeByIdx(FuncIdx funcIdx) {
        return codesEntries.getElements().get((int)(funcIdx.getX()) - module.getImportSection().getTotalFuncImports());
    }

    public Func getByIdx(FuncIdx funcIdx) {
        return getCodeByIdx(funcIdx).getCode();
    }

    public List<Code> getCodesEntries() {
        return codesEntries.getElements();
    }

    public void setCodesEntries(List<Code> codesEntries) {
        this.codesEntries = new Vector<>(codesEntries);
    }
}
