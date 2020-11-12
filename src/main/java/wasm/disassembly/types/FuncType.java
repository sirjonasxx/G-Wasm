package wasm.disassembly.types;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.WASMOpCode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FuncType extends WASMOpCode {

    private ResultType parameterType;
    private ResultType resultType;

    public FuncType(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        if (in.read() != 0x60) throw new InvalidOpCodeException("Function types must be encoded with 0x60");

        parameterType = new ResultType(in);
        resultType = new ResultType(in);
    }

    public FuncType(ResultType parameterType, ResultType resultType) {
        this.parameterType = parameterType;
        this.resultType = resultType;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        out.write(0x60);
        parameterType.assemble(out);
        resultType.assemble(out);
    }

    public ResultType getParameterType() {
        return parameterType;
    }

    public void setParameterType(ResultType parameterType) {
        this.parameterType = parameterType;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }
}
