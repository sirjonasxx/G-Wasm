package wasm.disassembly.modules.misc;

import wasm.disassembly.instructions.Expression;
import wasm.disassembly.modules.Module;
import wasm.disassembly.modules.indices.FuncIdx;
import wasm.disassembly.modules.indices.TypeIdx;
import wasm.disassembly.modules.sections.code.Code;
import wasm.disassembly.modules.sections.code.Func;
import wasm.disassembly.types.FuncType;
import wasm.disassembly.types.ValType;

import java.util.List;

public class Function {

    private FuncType funcType;
    private List<ValType> locals;
    private Expression code;

    public Function(FuncType funcType, List<ValType> locals, Expression code) {
        this.funcType = funcType;
        this.locals = locals;
        this.code = code;
    }

    public FuncIdx addToModule(Module module) {
        TypeIdx typeIdx = module.getTypeSection().getTypeIdxForFuncType(funcType);
        ValType[] valTypeArr = locals.toArray(new ValType[0]);
        Func func = new Func(valTypeArr, code);

        module.getFunctionSection().getTypeIdxVector().add(typeIdx);
        module.getCodeSection().getCodesEntries().add(new Code(func));
        return new FuncIdx(
                module.getImportSection().getTotalFuncImports() + module.getCodeSection().getCodesEntries().size() - 1,
                module
        );
    }


    public FuncType getFuncType() {
        return funcType;
    }

    public void setFuncType(FuncType funcType) {
        this.funcType = funcType;
    }

    public List<ValType> getLocals() {
        return locals;
    }

    public void setLocals(List<ValType> locals) {
        this.locals = locals;
    }

    public Expression getCode() {
        return code;
    }

    public void setCode(Expression code) {
        this.code = code;
    }
}
