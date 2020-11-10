package disassembly.modules;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.modules.indices.TypeIdx;
import disassembly.modules.sections.Section;
import disassembly.modules.sections.SectionFactory;
import disassembly.modules.sections.code.CodeSection;
import disassembly.modules.sections.custom.CustomSection;
import disassembly.modules.sections.custom.CustomSectionFactory;
import disassembly.modules.sections.data.DataSection;
import disassembly.modules.sections.element.ElementSection;
import disassembly.modules.sections.export.ExportSection;
import disassembly.modules.sections.function.FunctionSection;
import disassembly.modules.sections.global.GlobalSection;
import disassembly.modules.sections.imprt.ImportSection;
import disassembly.modules.sections.memory.MemorySection;
import disassembly.modules.sections.start.StartSection;
import disassembly.modules.sections.table.TableSection;
import disassembly.modules.sections.type.TypeSection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
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

    public Module(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        customSectionsList = new ArrayList<>();

        magic = new Magic(in);
        version = new Version(in);

        disassembleCustomSections(in);
        typeSection = new TypeSection(in);
        disassembleCustomSections(in);
        importSection = new ImportSection(in);
        disassembleCustomSections(in);
        functionSection = new FunctionSection(in);
        disassembleCustomSections(in);
//        tableSection = new TableSection(in);
//        disassembleCustomSections(in);
//        memorySection = new MemorySection(in);
//        disassembleCustomSections(in);
        globalSection = new GlobalSection(in);
        disassembleCustomSections(in);
        exportSection = new ExportSection(in);
        disassembleCustomSections(in);
//        startSection = new StartSection(in);
//        disassembleCustomSections(in);
        elementSection = new ElementSection(in);
        disassembleCustomSections(in);
        codeSection = new CodeSection(in);
        disassembleCustomSections(in);
        dataSection = new DataSection(in);
        disassembleCustomSections(in);

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
    }

    public Module(TypeSection typeSection, ImportSection importSection, FunctionSection functionSection, TableSection tableSection, MemorySection memorySection, GlobalSection globalSection, ExportSection exportSection, StartSection startSection, ElementSection elementSection, CodeSection codeSection, DataSection dataSection, List<List<CustomSection>> customSectionsList) {
        this.magic = new Magic();
        this.version = new Version(new byte[]{1, 0, 0, 0});

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
        this.customSectionsList = customSectionsList;
    }

    private void disassembleCustomSections(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        List<CustomSection> customSections = new ArrayList<>();

        int nextSectionId = SectionFactory.readSectionId(in);
        if (nextSectionId == 0) {
            customSections.add(CustomSectionFactory.get(in));
        }

        customSectionsList.add(customSections);
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
//        Section[] sections = new Section[]{typeSection, importSection, functionSection, tableSection,
//        memorySection, globalSection, exportSection, startSection, elementSection, codeSection,
//        dataSection};
//
//        for (int i = 0; i < 11; i++) {
//            assembleCustomSections(out, i);
//            sections[i].assemble(out);
//        }
//        assembleCustomSections(out, 11);

        magic.assemble(out);
        version.assemble(out);

        Section[] sections = new Section[]{typeSection, importSection, functionSection, globalSection, exportSection,
                elementSection, codeSection,
                dataSection};

        for (int i = 0; i < 8; i++) {
            assembleCustomSections(out, i);
            sections[i].assemble(out);
        }
        assembleCustomSections(out, 8);
    }

    private void assembleCustomSections(OutputStream out, int location) throws IOException, InvalidOpCodeException {
        for(CustomSection section : customSectionsList.get(location)) {
            section.assemble(out);
        }
    }
}
