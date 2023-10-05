package sss.reflection_test.plain.basic;

public class Car extends Vehicle implements CarInterface {

    public String name;
    public int capa;

    private static int testStaticNum = 3;

//    private Car() {}

//    Car() {}
    // 있고 없고를 통해 컴파일 에러 뿐만 아니라 런타임 예외 상황도 발견할 수 있었음
//    public Car() {
//    }
    public Car() {}


    private Car(int capa) {
        this.capa = capa;
    }

    public Car(String name) {
        this.name = name;
    }

    public Car(String name, int capa) {
        this.name = name;
        this.capa = capa;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", capa=" + capa +
                '}';
    }

    public String toStringExtended() {
        return "Car{" +
                "name='" + name + '\'' +
                ", privateString='" + privateString + '\'' +
                ", testStaticNum='" + testStaticNum + '\'' +
                ", capa=" + capa +
                '}';
    }

    public int publicInt;
    private String privateString = "private string";
    protected boolean protectedBoolean;
    Object defaultObject;

    @Override
    public void methodSout1() {
        System.out.println("Method1 impl.");
    }

    @Override
    public int methodSout2(String str) {
        System.out.println("Method2 impl.");
        return 0;
    }

    @Override
    public int methodSout4(){
        System.out.println("Method4 overriden.");
        return 0;
    }

    public int methodSout5(int i){
        System.out.println("Method5 not overriden, param=" + i);
        return 0;
    }

    // inner classes
    public class CustomInnerPublicClass{}
    private class CustomInnerPrivateClass{}
    protected class CustomInnerProtectedClass{}
    class CustomInnerDefaultClass{}

    //member enum
    enum CustomInnerDefaultEnum{}
    public enum CustomInnerPublicEnum{}

    //member interface
    public interface CustomInnerPublicInterface{}
}
