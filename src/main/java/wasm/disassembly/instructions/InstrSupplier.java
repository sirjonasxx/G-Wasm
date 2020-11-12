package wasm.disassembly.instructions;

import wasm.disassembly.InvalidOpCodeException;

import java.io.BufferedInputStream;
import java.io.IOException;

public interface InstrSupplier {

    Instr get(BufferedInputStream in, InstrType type) throws IOException, InvalidOpCodeException;

}
