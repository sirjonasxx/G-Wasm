package wasm.misc;

import wasm.disassembly.types.FuncType;

public interface StreamReplacement {


    enum ReplacementType {
        HOOK,
        HOOKCOPYEXPORT
    }

    FuncType getFuncType();
    ReplacementType getReplacementType();
    String getImportName();
    String getExportName();
    CodeCompare getCodeCompare();
}
