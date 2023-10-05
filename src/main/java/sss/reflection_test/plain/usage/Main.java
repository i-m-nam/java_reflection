package sss.reflection_test.plain.usage;

import java.util.List;

public class Main {
    private static final List<String> featureList = List.of("someModule2", "someModule6", "someModule9", "someModule12");
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Sample1 sample1 = new Sample1();
        for (int i = 0; i < featureList.size(); i++) {
            sample1.createLib(featureList.get(i));
        }
        System.out.println("------ end sample1 ------");

        Sample1Reflection sample1Reflection = new Sample1Reflection();
        for (int i = 0; i < featureList.size(); i++) {
            sample1Reflection.getInstance("sss.reflection_test.plain.usage.custom_lib." + featureList.get(i));
        }

        System.out.println("------ end reflection ------");
    }
}
