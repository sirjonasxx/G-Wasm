package disassembly.modules.sections;

import disassembly.InvalidOpCodeException;

import java.io.BufferedInputStream;
import java.io.IOException;

public interface SectionSupplier {

    Section get(BufferedInputStream in) throws IOException, InvalidOpCodeException;

}
