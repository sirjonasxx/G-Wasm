package wasm.disassembly.modules.sections.code;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.WASMOpCode;
import wasm.disassembly.conventions.Vector;
import wasm.disassembly.instructions.Expression;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class Func extends WASMOpCode {

    private Vector<Locals> localss; // intended double s
    private Expression expression;


    public Func(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        localss = new Vector<>(in, Locals::new);
        expression = new Expression(in);
    }

    public Func(List<Locals> localss, Expression expression) {
        this.localss = new Vector<>(localss);
        this.expression = expression;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        localss.assemble(out);
        expression.assemble(out);
    }

    public List<Locals> getLocalss() {
        return localss.getElements();
    }

    public void setLocalss(List<Locals> localss) {
        this.localss = new Vector<>(localss);
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
