package disassembly.modules.sections.table;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.types.TableType;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Table extends WASMOpCode {

    private TableType tableType;

    public Table(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        tableType = new TableType(in);
    }

    public Table(TableType tableType) {
        this.tableType = tableType;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        tableType.assemble(out);
    }

    public TableType getTableType() {
        return tableType;
    }

    public void setTableType(TableType tableType) {
        this.tableType = tableType;
    }
}
