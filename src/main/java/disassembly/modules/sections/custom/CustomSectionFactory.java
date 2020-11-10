package disassembly.modules.sections.custom;

import disassembly.InvalidOpCodeException;
import disassembly.values.WName;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;

public class CustomSectionFactory {

    public static CustomSection get(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        OldWUnsignedInt size = new OldWUnsignedInt(in, 32);
        WName name = new WName(in);

        // select implementation

        return new UnImplementedCustomSection(in, size, name);
    }
}
