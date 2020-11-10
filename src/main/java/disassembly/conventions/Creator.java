package disassembly.conventions;

import disassembly.InvalidOpCodeException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface Creator<B> {

    B create(BufferedInputStream in) throws IOException, InvalidOpCodeException;

}
