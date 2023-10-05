package sss.reflection_test.plain.basic;

public class Vehicle extends Move {
    private int age;

    private String name;

    public String testPublicAccessStr;

    private Object someObject;

    // inner public class
    public class VehicleInnerClass{}

    public interface VehicleInnerInterface{}

    //member public enum
    public enum VehicleMemberEnum{}
}
