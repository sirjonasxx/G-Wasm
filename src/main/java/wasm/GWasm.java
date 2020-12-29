package wasm;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.instructions.Expression;
import wasm.disassembly.instructions.Instr;
import wasm.disassembly.instructions.InstrType;
import wasm.disassembly.instructions.misc.SingleByteInstr;
import wasm.disassembly.modules.Module;
import wasm.disassembly.modules.indices.FuncIdx;
import wasm.disassembly.modules.indices.TypeIdx;
import wasm.disassembly.modules.sections.code.Func;
import wasm.disassembly.modules.sections.code.Locals;
import wasm.disassembly.modules.sections.export.Export;
import wasm.disassembly.modules.sections.export.ExportDesc;
import wasm.disassembly.modules.sections.imprt.Import;
import wasm.disassembly.modules.sections.imprt.ImportDesc;
import wasm.disassembly.types.FuncType;
import wasm.disassembly.types.ResultType;
import wasm.disassembly.types.ValType;
import wasm.misc.CodeCompare;
import wasm.misc.Function;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GWasm {

    public static void main(String[] args) throws IOException, InvalidOpCodeException {

    }
}
