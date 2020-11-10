package disassembly.modules.sections.imprt;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.modules.indices.TypeIdx;
import disassembly.types.GlobalType;
import disassembly.types.MemType;
import disassembly.types.TableType;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImportDesc extends WASMOpCode {

    private WASMOpCode importValue;
    private int importType;

    public ImportDesc(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        importType = in.read();
        if (importType < 0x00 || importType > 0x03) {
            throw new InvalidOpCodeException("invalid importdesc type");
        }

        importValue = importType == 0x00 ? new TypeIdx(in) :
                (importType == 0x01 ? new TableType(in) :
                        (importType == 0x02 ? new MemType(in) :
                                new GlobalType(in)));
    }

    public ImportDesc(WASMOpCode importValue, int importType) {
        this.importValue = importValue;
        this.importType = importType;
    }

    public ImportDesc(TypeIdx typeIdx) {
        this(typeIdx, 0x00);
    }

    public ImportDesc(TableType tableType) {
        this(tableType, 0x01);
    }

    public ImportDesc(MemType memType) {
        this(memType, 0x02);
    }

    public ImportDesc(GlobalType globalType) {
        this(globalType, 0x03);
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        out.write(importType);
        importValue.assemble(out);
    }

    public WASMOpCode getImportValue() {
        return importValue;
    }

    public void setImportValue(WASMOpCode importValue) {
        this.importValue = importValue;
    }

    public int getImportType() {
        return importType;
    }

    public void setImportType(int importType) {
        this.importType = importType;
    }
}
