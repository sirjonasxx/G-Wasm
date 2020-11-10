package disassembly.types;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.conventions.CustomVector;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ResultType extends WASMOpCode {

    private CustomVector<ValType> vector;

    public ResultType(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        vector = new CustomVector<>(
                in,
                in1 -> ValType.from_val(in1.read()),
                (valType, out) -> out.write(valType.val)
        );
    }

    public ResultType(List<ValType> valTypes) {
        vector = new CustomVector<>(
                valTypes,
                (valType, out) -> out.write(valType.val)
        );
    }

    public ResultType(CustomVector<ValType> vector) {
        this.vector = vector;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        vector.assemble(out);
    }

    public List<ValType> typeList() {
        return vector.getElements();
    }

    public CustomVector<ValType> getVector() {
        return vector;
    }

    public void setVector(CustomVector<ValType> vector) {
        this.vector = vector;
    }
}
