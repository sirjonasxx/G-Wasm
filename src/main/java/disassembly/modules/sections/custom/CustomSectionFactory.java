package disassembly.modules.sections.custom;

import disassembly.InvalidOpCodeException;
import disassembly.values.WName;
import disassembly.values.WUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;

public class CustomSectionFactory {

    public static CustomSection get(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        long size = WUnsignedInt.read(in, 32);
        WName name = new WName(in);

        // select implementation

        return new UnImplementedCustomSection(in, size, name);
    }
}
