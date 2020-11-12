package wasm.disassembly.modules.sections.custom;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.values.WName;
import wasm.disassembly.values.WUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;

public class CustomSectionFactory {

    public static CustomSection get(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        long size = WUnsignedInt.read(in, 32);
        String name = WName.read(in);

        // select implementation

        return new UnImplementedCustomSection(in, size, name);
    }
}
