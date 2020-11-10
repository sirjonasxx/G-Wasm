package disassembly.modules.sections.export;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.values.WName;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Export extends WASMOpCode {

    private WName name;
    private ExportDesc exportDesc;

    public Export(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        name = new WName(in);
        exportDesc = new ExportDesc(in);
    }

    public Export(WName name, ExportDesc exportDesc) {
        this.name = name;
        this.exportDesc = exportDesc;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        name.assemble(out);
        exportDesc.assemble(out);
    }


    public WName getName() {
        return name;
    }

    public void setName(WName name) {
        this.name = name;
    }

    public ExportDesc getExportDesc() {
        return exportDesc;
    }

    public void setExportDesc(ExportDesc exportDesc) {
        this.exportDesc = exportDesc;
    }
}
