package wasm.misc;

import wasm.disassembly.modules.sections.code.Func;
import wasm.disassembly.types.FuncType;

public interface CodeCompare {

    boolean isEqual(Func code);

}
