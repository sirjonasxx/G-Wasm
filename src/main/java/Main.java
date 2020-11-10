import disassembly.InvalidOpCodeException;
import disassembly.modules.Magic;
import disassembly.modules.Module;
import disassembly.modules.Version;
import disassembly.modules.sections.SectionFactory;
import disassembly.modules.sections.type.TypeSection;

import java.io.*;

public class Main {




    public static void main(String[] args) throws IOException, InvalidOpCodeException {
        File unityWasmCode = new File("C:\\Users\\jonas\\Desktop\\Projects\\Jznnp\\S\\habbo2020\\rawfiles\\0.5.2_(1)\\habbo2020-global-prod.wasm.code.unityweb");
        InputStream targetStream = new FileInputStream(unityWasmCode);
        BufferedInputStream in = new BufferedInputStream(targetStream);

        long time_before = System.currentTimeMillis();
        Module module = new Module(in);
        long time_after = System.currentTimeMillis();

        System.out.println(String.format("disassemble time: %d", time_after - time_before));


        FileOutputStream habAssembled = new FileOutputStream("C:\\Users\\jonas\\Desktop\\Projects\\Jznnp\\S\\habbo2020\\rawfiles\\0.5.2_(1)\\out\\GWASM-global-prod.wasm.code.unityweb");

        time_before = System.currentTimeMillis();
        module.assemble(habAssembled);
        time_after = System.currentTimeMillis();

        System.out.println(String.format("assemble time: %d", time_after - time_before));
    }
}
