package wasm.disassembly.conventions;

import wasm.disassembly.InvalidOpCodeException;

import java.io.BufferedInputStream;
import java.io.IOException;

public interface Creator<B> {

    B create(BufferedInputStream in) throws IOException, InvalidOpCodeException;

}
