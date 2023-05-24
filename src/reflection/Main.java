package reflection;

import reflection.testclass.DiffPack;

import java.lang.reflect.*;
import java.util.Arrays;

class SamePack {
    public int publicI = 1;
    protected int protectedI = 2;
    private int privateI = 3;
    int defaultI = 4;
}

public class Main {
    public static void main(String[] args) throws Exception {

        SamePack samePack = new SamePack();
        System.out.println(samePack.publicI);
        System.out.println(samePack.protectedI);
//        System.out.println(samePack.privateI);
        System.out.println(samePack.defaultI);

        DiffPack diffPack = new DiffPack();
        System.out.println(diffPack.publicI);
//        System.out.println(diffPack.protectedI);
//        System.out.println(diffPack.privateI);
//        System.out.println(diffPack.defaultI);

        // Reflection
        Class<SamePack> samePackClass = SamePack.class;
        Class<DiffPack> diffPackClass = DiffPack.class;

        System.out.println(Arrays.toString(samePackClass.getDeclaredFields()));
        System.out.println(Arrays.toString(diffPackClass.getDeclaredFields()));

        System.out.println("\n///////////Constructor///////////");
        Constructor<SamePack> samePackConstructor = samePackClass.getDeclaredConstructor();
        SamePack samePack2 = samePackConstructor.newInstance();
        System.out.println(samePack2.publicI);

        System.out.println("\n///////////Field///////////");
        for (Field field : diffPackClass.getDeclaredFields()) {
            field.setAccessible(true);
            if(field.getName().equals("privateI")){
                field.set(diffPack, 5);
            }
            String fieldInfo = field.getType() + ", " + field.getName() + " = " + field.get(diffPack);
            System.out.println(fieldInfo);
        }

        System.out.println("\n///////////Method///////////");
        System.out.println(Arrays.toString(diffPackClass.getDeclaredMethods()));

        Method sum = diffPackClass.getDeclaredMethod("sum");
        try {
            System.out.println(sum.invoke(diffPack));
        } catch (Exception ex){
            System.err.println(ex);
        } finally {
            sum.setAccessible(true);
            System.out.println("sum : " + sum.invoke(diffPack));
        }
    }
}
