package disassembly.modules.sections;

import disassembly.InvalidOpCodeException;
import disassembly.modules.sections.code.CodeSection;
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
import java.util.HashMap;
import java.util.Map;

public class SectionFactory {

    private static Map<Integer, SectionSupplier> allSections;
    static {
        allSections = new HashMap<>();
        allSections.put(0, CustomSectionFactory::get);
        allSections.put(1, TypeSection::new);
        allSections.put(2, ImportSection::new);
        allSections.put(3, FunctionSection::new);
        allSections.put(4, TableSection::new);
        allSections.put(5, MemorySection::new);
        allSections.put(6, GlobalSection::new);
        allSections.put(7, ExportSection::new);
        allSections.put(8, StartSection::new);
        allSections.put(9, ElementSection::new);
        allSections.put(10, CodeSection::new);
        allSections.put(11, DataSection::new);
    }


    private int minimumSectionId = 1;

    private BufferedInputStream in;

    public SectionFactory(BufferedInputStream in) {
        this.in = in;
    }

    // returns null if none left
    public Section get() throws IOException, InvalidOpCodeException {
        int sectionType = in.read();
        if (sectionType == -1) {
            return null;
        }

        if (sectionType != 0) {
           if (sectionType < minimumSectionId) {
               throw new InvalidOpCodeException("Unexpected WASM section");
           }
           minimumSectionId = sectionType + 1;
        }

        return allSections.get(sectionType).get(in);
    }

    public static int readSectionId(BufferedInputStream in) throws IOException {
//        in.mark(1);
        int id = in.read();
//        in.reset();

        return id;
    }

}
