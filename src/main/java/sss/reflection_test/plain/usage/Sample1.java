package sss.reflection_test.plain.usage;

import sss.reflection_test.plain.usage.custom_lib.*;

public class Sample1 {
    public BaseLibrary createLib(String featureName) {
        switch(featureName) {
            case "someModule1":
                return someModule1();
            case "someModule2":
                return someModule2();
            case "someModule3":
                return someModule3();
            case "someModule4":
                return someModule4();
            case "someModule5":
                return someModule5();
            case "someModule6":
                return someModule6();
            case "someModule7":
                return someModule7();
            case "someModule8":
                return someModule8();
            case "someModule9":
                return someModule9();
            case "someModule10":
                return someModule10();
            case "someModule11":
                return someModule11();
            case "someModule12":
                return someModule12();
            case "someModule13":
                return someModule13();
            default:
                throw new IllegalArgumentException("Not supported feature library");
        }
    }

    private BaseLibrary someModule8() {
        return null;
    }

    private BaseLibrary someModule9() {
        return new someModule9();
    }

    private BaseLibrary someModule10() {
        return null;
    }

    private BaseLibrary someModule11() {
        return null;
    }

    private BaseLibrary someModule12() {
        return new someModule12();
    }

    private BaseLibrary someModule13() {
        return null;
    }

    private BaseLibrary someModule7() {
        return null;
    }

    private BaseLibrary someModule6() {
        return new someModule6();
    }

    private BaseLibrary someModule5() {
        return null;
    }

    private BaseLibrary someModule4() {
        return null;
    }

    private BaseLibrary someModule3() {
        return null;
    }

    private BaseLibrary someModule2() {
        return new someModule2();
    }

    private BaseLibrary someModule1() {
        return null;
    }
}
