package sss.reflection_test.plain.basic.sub;

public class SubCar {
    private String subCarName;
    private int subCarCapa;

    @Override
    public String toString() {
        return "SubCar{" +
                "subCarName='" + subCarName + '\'' +
                ", subCarCapa=" + subCarCapa +
                '}';
    }

    public SubCar(String subCarName, int subCarCapa) {
        this.subCarName = subCarName;
        this.subCarCapa = subCarCapa;
    }

    // package 가 다른거야
//    SubCar() {
//    }

    public SubCar() {
    }
}
