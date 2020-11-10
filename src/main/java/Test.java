import disassembly.InvalidOpCodeException;
import disassembly.values.old.OldWUnsignedInt;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main2(String[] args) throws InvalidOpCodeException, IOException {

        List<OldWUnsignedInt> values = new ArrayList<>();

        ByteArrayOutputStream tempFill = new ByteArrayOutputStream();
        for (int i = 0; i < 12000000; i++) {
            OldWUnsignedInt oldWUnsignedInt = new OldWUnsignedInt(i);
            oldWUnsignedInt.assemble(tempFill);
        }

        BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(tempFill.toByteArray()));


        long time_before = System.currentTimeMillis();
        for (int i = 0; i < 12000000; i++) {
            values.add(new OldWUnsignedInt(in, 32));
        }

        long time_after = System.currentTimeMillis();

        System.out.println(String.format("%d", time_after - time_before));


        System.out.println("hi");


    }

    public static void main(String[] args) throws InvalidOpCodeException {
        List<Long> values = new ArrayList<>();

        long time_before = System.currentTimeMillis();

        for (int i = 0; i < 12000000; i++) {
            OldWUnsignedInt oldWUnsignedInt = new OldWUnsignedInt(i);
            values.add(oldWUnsignedInt.getUnsignedInt());
        }

        long time_after = System.currentTimeMillis();

        System.out.println(String.format("%d", time_after - time_before));

    }

}
