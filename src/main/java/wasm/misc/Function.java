package wasm.misc;

import wasm.disassembly.instructions.Expression;
import wasm.disassembly.modules.Module;
import wasm.disassembly.modules.indices.FuncIdx;
import wasm.disassembly.modules.indices.TypeIdx;
import wasm.disassembly.modules.sections.code.Code;
import wasm.disassembly.modules.sections.code.Func;
import wasm.disassembly.modules.sections.code.Locals;
import wasm.disassembly.types.FuncType;
import wasm.disassembly.types.ValType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Function {

    private FuncType funcType = null;
    private List<ValType> locals = null;
    private Expression code = null;

    public Function(FuncType funcType, List<ValType> locals, Expression code) {
        this.funcType = funcType;
        this.locals = locals;
        this.code = code;
    }

    public Function(Module module, int funcId) {
        if (funcId < 0) return;

        TypeIdx typeIdx = module.getFunctionSection().getTypeIdxVector().get(funcId);
        funcType = module.getTypeSection().getByTypeIdx(typeIdx);

        Func code = module.getCodeSection().getCodesEntries().get(funcId).getCode();
        this.code = code.getExpression();

        locals = new ArrayList<>();
        List<Locals> localss = code.getLocalss();
        for (Locals loc : localss) {
            for (int i = 0; i < loc.getAmount(); i++) {
                locals.add(loc.getValType());
            }
        }
    }

    public Function(Module module, FuncIdx funcIdx) {
        this(module, (int)(funcIdx.getX()) - module.getImportSection().getTotalFuncImports());
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
