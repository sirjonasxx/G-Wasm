package disassembly.modules.sections.code;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.types.ValType;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Locals extends WASMOpCode {

    private OldWUnsignedInt amount;
    private ValType valType;

    public Locals(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        amount = new OldWUnsignedInt(in, 32);
        valType = ValType.from_val(in.read());
    }

    public Locals(long amount, ValType valType) throws InvalidOpCodeException {
        this.amount = new OldWUnsignedInt(amount);
        this.valType = valType;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        amount.assemble(out);
        out.write(valType.val);
    }


    public OldWUnsignedInt getAmount() {
        return amount;
    }

    public void setAmount(OldWUnsignedInt amount) {
        this.amount = amount;
    }

    public ValType getValType() {
        return valType;
    }

    public void setValType(ValType valType) {
        this.valType = valType;
    }
}
