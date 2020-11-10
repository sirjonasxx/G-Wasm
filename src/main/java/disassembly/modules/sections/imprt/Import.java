package disassembly.modules.sections.imprt;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.modules.Module;
import disassembly.values.WName;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Import extends WASMOpCode {

    private WName module;
    private WName name;
    private ImportDesc importDescription;

    public Import(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        module = new WName(in);
        name = new WName(in);
        importDescription = new ImportDesc(in);
    }

    public Import(WName module, WName name, ImportDesc importDescription) {
        this.module = module;
        this.name = name;
        this.importDescription = importDescription;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        module.assemble(out);
        name.assemble(out);
        importDescription.assemble(out);
    }

    public WName getModule() {
        return module;
    }

    public void setModule(WName module) {
        this.module = module;
    }

    public WName getName() {
        return name;
    }

    public void setName(WName name) {
        this.name = name;
    }

    public ImportDesc getImportDescription() {
        return importDescription;
    }

    public void setImportDescription(ImportDesc importDescription) {
        this.importDescription = importDescription;
    }
}
