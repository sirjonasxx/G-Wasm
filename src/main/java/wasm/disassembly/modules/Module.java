package wasm.disassembly.modules;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.WASMOpCode;
import wasm.disassembly.modules.indices.FuncIdx;
import wasm.disassembly.modules.sections.Section;
import wasm.disassembly.modules.sections.code.CodeSection;
import wasm.disassembly.modules.sections.custom.CustomSection;
import wasm.disassembly.modules.sections.custom.CustomSectionFactory;
import wasm.disassembly.modules.sections.data.DataSection;
import wasm.disassembly.modules.sections.element.ElementSection;
import wasm.disassembly.modules.sections.export.ExportSection;
import wasm.disassembly.modules.sections.function.FunctionSection;
import wasm.disassembly.modules.sections.global.GlobalSection;
import wasm.disassembly.modules.sections.imprt.ImportSection;
import wasm.disassembly.modules.sections.memory.MemorySection;
import wasm.disassembly.modules.sections.start.StartSection;
import wasm.disassembly.modules.sections.table.TableSection;
import wasm.disassembly.modules.sections.type.TypeSection;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Module extends WASMOpCode {

    private Magic magic;
    private Version version;

    private TypeSection typeSection;
    private ImportSection importSection;
    private FunctionSection functionSection;
    private TableSection tableSection;
    private MemorySection memorySection;
    private GlobalSection globalSection;
    private ExportSection exportSection;
    private StartSection startSection;
    private ElementSection elementSection;
    private CodeSection codeSection;
    private DataSection dataSection;

    private List<List<CustomSection>> customSectionsList;
    private List<FuncIdx> allFuncIdxs = new ArrayList<>();

    public Module(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        customSectionsList = new ArrayList<>();

        magic = new Magic(in);
        version = new Version(in);

        disassembleCustomSections(in);
        typeSection = isNextSection(in, 1) ? new TypeSection(in, this) : null;
        disassembleCustomSections(in);
        importSection = isNextSection(in, 2) ? new ImportSection(in, this) : null;
        disassembleCustomSections(in);
        functionSection = isNextSection(in, 3) ? new FunctionSection(in, this) : null;
        disassembleCustomSections(in);
        tableSection = isNextSection(in, 4) ? new TableSection(in, this) : null;
        disassembleCustomSections(in);
        memorySection = isNextSection(in, 5) ? new MemorySection(in, this) : null;
        disassembleCustomSections(in);
        globalSection = isNextSection(in , 6) ? new GlobalSection(in, this) : null;
        disassembleCustomSections(in);
        exportSection = isNextSection(in, 7) ? new ExportSection(in, this) : null;
        disassembleCustomSections(in);
        startSection = isNextSection(in, 8) ? new StartSection(in, this) : null;
        disassembleCustomSections(in);
        elementSection = isNextSection(in, 9) ? new ElementSection(in, this) : null;
        disassembleCustomSections(in);
        codeSection = isNextSection(in, 10) ? new CodeSection(in, this) : null;
        disassembleCustomSections(in);
        dataSection = isNextSection(in, 11) ? new DataSection(in, this) : null;
        disassembleCustomSections(in);

        in.close();
    }

    public Module(String fileName) throws IOException, InvalidOpCodeException {
        this(new BufferedInputStream(new FileInputStream(new File(fileName))));
    }

    public Module(Magic magic, Version version, TypeSection typeSection, ImportSection importSection, FunctionSection functionSection, TableSection tableSection, MemorySection memorySection, GlobalSection globalSection, ExportSection exportSection, StartSection startSection, ElementSection elementSection, CodeSection codeSection, DataSection dataSection) {
        this.magic = magic;
        this.version = version;
        this.typeSection = typeSection;
        this.importSection = importSection;
        this.functionSection = functionSection;
        this.tableSection = tableSection;
        this.memorySection = memorySection;
        this.globalSection = globalSection;
        this.exportSection = exportSection;
        this.startSection = startSection;
        this.elementSection = elementSection;
        this.codeSection = codeSection;
        this.dataSection = dataSection;

        this.customSectionsList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            customSectionsList.add(new ArrayList<>());
        }
    }
    public Module(TypeSection typeSection, ImportSection importSection, FunctionSection functionSection, TableSection tableSection, MemorySection memorySection, GlobalSection globalSection, ExportSection exportSection, StartSection startSection, ElementSection elementSection, CodeSection codeSection, DataSection dataSection, List<List<CustomSection>> customSectionsList) {
        this(new Magic(), new Version(), typeSection, importSection, functionSection, tableSection, memorySection,
                globalSection, exportSection, startSection, elementSection, codeSection, dataSection);
    }

    private void disassembleCustomSections(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        List<CustomSection> customSections = new ArrayList<>();

        while (isNextSection(in, 0)) {
            customSections.add(CustomSectionFactory.get(in, this));
        }

        in.reset();
        customSectionsList.add(customSections);
    }

    private boolean isNextSection(BufferedInputStream in, int id) throws IOException {
        in.mark(1);
        if (in.read() == id) {
            return true;
        }
        in.reset();
        return false;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        magic.assemble(out);
        version.assemble(out);

        Section[] sections = new Section[]{typeSection, importSection, functionSection, tableSection,
        memorySection, globalSection, exportSection, startSection, elementSection, codeSection,
        dataSection};

        for (int i = 0; i < 11; i++) {
            assembleCustomSections(out, i);
            if (sections[i] != null) {
                sections[i].assemble(out);
            }
        }
        assembleCustomSections(out, 11);
    }

    private void assembleCustomSections(OutputStream out, int location) throws IOException, InvalidOpCodeException {
        for(CustomSection section : customSectionsList.get(location)) {
            section.assemble(out);
        }
    }

    public void assembleToFile(String fileName) throws IOException, InvalidOpCodeException {
        FileOutputStream habAssembled = new FileOutputStream(fileName);
        assemble(habAssembled);
        habAssembled.close();
    }

    public Magic getMagic() {
        return magic;
    }

    public void setMagic(Magic magic) {
        this.magic = magic;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public TypeSection getTypeSection() {
        return typeSection;
    }

    public void setTypeSection(TypeSection typeSection) {
        this.typeSection = typeSection;
    }

    public ImportSection getImportSection() {
        return importSection;
    }

    public void setImportSection(ImportSection importSection) {
        this.importSection = importSection;
    }

    public FunctionSection getFunctionSection() {
        return functionSection;
    }

    public void setFunctionSection(FunctionSection functionSection) {
        this.functionSection = functionSection;
    }

    public TableSection getTableSection() {
        return tableSection;
    }

    public void setTableSection(TableSection tableSection) {
        this.tableSection = tableSection;
    }

    public MemorySection getMemorySection() {
        return memorySection;
    }

    public void setMemorySection(MemorySection memorySection) {
        this.memorySection = memorySection;
    }

    public GlobalSection getGlobalSection() {
        return globalSection;
    }

    public void setGlobalSection(GlobalSection globalSection) {
        this.globalSection = globalSection;
    }

    public ExportSection getExportSection() {
        return exportSection;
    }

    public void setExportSection(ExportSection exportSection) {
        this.exportSection = exportSection;
    }

    public StartSection getStartSection() {
        return startSection;
    }

    public void setStartSection(StartSection startSection) {
        this.startSection = startSection;
    }

    public ElementSection getElementSection() {
        return elementSection;
    }

    public void setElementSection(ElementSection elementSection) {
        this.elementSection = elementSection;
    }

    public CodeSection getCodeSection() {
        return codeSection;
    }

    public void setCodeSection(CodeSection codeSection) {
        this.codeSection = codeSection;
    }

    public DataSection getDataSection() {
        return dataSection;
    }

    public void setDataSection(DataSection dataSection) {
        this.dataSection = dataSection;
    }

    public List<List<CustomSection>> getCustomSectionsList() {
        return customSectionsList;
    }

    public void setCustomSectionsList(List<List<CustomSection>> customSectionsList) {
        this.customSectionsList = customSectionsList;
    }

    public List<FuncIdx> getAllFuncIdxs() {
        return allFuncIdxs;
    }
}
