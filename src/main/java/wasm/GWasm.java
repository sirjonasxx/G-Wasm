package wasm;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.modules.Module;
import wasm.disassembly.modules.indices.FuncIdx;
import wasm.disassembly.modules.indices.TypeIdx;
import wasm.disassembly.modules.sections.export.Export;
import wasm.disassembly.modules.sections.export.ExportDesc;
import wasm.disassembly.modules.sections.imprt.Import;
import wasm.disassembly.modules.sections.imprt.ImportDesc;
import wasm.disassembly.types.FuncType;
import wasm.disassembly.types.ResultType;
import wasm.disassembly.types.ValType;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GWasm {

    public static void extractMethods(Module module) {
        List<TypeIdx> typesFromImports = new ArrayList<>();
        for (Import imp : module.getImportSection().getImports()) {
            if (imp.getImportDescription().getImportType() == 0) {
                typesFromImports.add((TypeIdx)imp.getImportDescription().getImportValue());
            }
        }

        List<Export> exportList = module.getExportSection().getExports();
        for(Export export : exportList) {
            ExportDesc desc = export.getExportDesc();
            if (desc.getExportType() == 0) {
                FuncIdx funcIdx = (FuncIdx) desc.getExportValue();
                TypeIdx typeIdx;
                if (funcIdx.getX() < typesFromImports.size()) {
                    typeIdx = typesFromImports.get((int)(funcIdx.getX()));
                }
                else {
                    typeIdx = module.getFunctionSection().getTypeIdxVector().get((int)(funcIdx.getX()) - typesFromImports.size());
                }
                FuncType funcType = module.getTypeSection().getFunctionTypes().get((int)(typeIdx.getX()));

                System.out.println(String.format("%s ::= %s -> %s",
                        export.getName(),
                        "(" + funcType.getParameterType().typeList().stream().map(Enum::name).collect(Collectors.joining(" ")) + ")",
                        "(" + funcType.getResultType().typeList().stream().map(Enum::name).collect(Collectors.joining(" ")) + ")"
                ));
            }
        }
    }

    public static void main(String[] args) throws IOException, InvalidOpCodeException {
//
        long start = System.currentTimeMillis();
        Module module = new Module("C:\\Users\\jonas\\Desktop\\Projects\\Jznnp\\S\\habbo2020\\rawfiles\\0.23.0_(534)\\habbo2020-global-prod.wasm.gz", true);
        long end = System.currentTimeMillis();

        System.out.println("Assembly time: " + (end - start));

//        extractMethods(module);
//
//        module.getImportSection().importFunctions(Collections.singletonList(
//                new Import(
//                        "env",
//                        "consoleLog",
//                        new ImportDesc(module.getTypeSection().getTypeIdxForFuncType(new FuncType(
//                                new ResultType(Collections.singletonList(ValType.I32)),
//                                new ResultType(Collections.emptyList())
//                        )))
//                )
//        ));

        start = System.currentTimeMillis();
        module.assembleToFile("C:\\Users\\jonas\\Desktop\\Projects\\Jznnp\\S\\habbo2020\\rawfiles\\0.23.0_(534)\\out\\habbo2020-global-prod.wasm.gz", true);
        end = System.currentTimeMillis();

        System.out.println("Disassembly time: " + (end - start));

    }
}
