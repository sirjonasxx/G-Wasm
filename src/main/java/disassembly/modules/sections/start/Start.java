package disassembly.modules.sections.start;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.modules.indices.FuncIdx;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Start extends WASMOpCode {

    private FuncIdx funcIdx;

    public Start(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        funcIdx = new FuncIdx(in);
    }

    public Start(FuncIdx funcIdx) {
        this.funcIdx = funcIdx;
    }


    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        funcIdx.assemble(out);
    }

    public FuncIdx getFuncIdx() {
        return funcIdx;
    }

    public void setFuncIdx(FuncIdx funcIdx) {
        this.funcIdx = funcIdx;
    }
}
