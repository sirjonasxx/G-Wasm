import disassembly.InvalidOpCodeException;
import disassembly.modules.Magic;
import disassembly.modules.Module;
import disassembly.modules.Version;
import disassembly.modules.sections.SectionFactory;
import disassembly.modules.sections.type.TypeSection;

import java.io.*;

public class Main {




    public static void main(String[] args) throws IOException, InvalidOpCodeException {
        File unityWasmCode = new File("C:\\Users\\jonas\\Desktop\\Projects\\Jznnp\\S\\habbo2020\\rawfiles\\habbo2020-global-prod.wasm.code.unityweb");
        InputStream targetStream = new FileInputStream(unityWasmCode);
        BufferedInputStream in = new BufferedInputStream(targetStream);

//
//        SectionFactory factory = new SectionFactory(in);
//
//        Magic magic = new Magic(in);
//        Version version = new Version(in);
//
//        TypeSection section = (TypeSection) factory.get();

        long time_before = System.currentTimeMillis();
        Module module = new Module(in);
        long time_after = System.currentTimeMillis();

        System.out.println(String.format("%d", time_after - time_before));

        System.out.println("test");

    }
}
