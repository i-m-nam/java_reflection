package sss.reflection_test.plain.time;

import sss.reflection_test.plain.basic.Car;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeTestMain {
    public static void main(String[] args) {
        int cases = 9_000;

        // case 1)
        LocalDateTime start = LocalDateTime.now();
        List<Car> normalCars = new ArrayList<>();
        for (int i = 0; i < cases; i++) {
            normalCars.add(new Car());
        }
        LocalDateTime end = LocalDateTime.now();
        Duration between1 = Duration.between(start.toLocalTime(), end.toLocalTime());
        System.out.println("between1 ))" + between1);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // case 2)
        LocalDateTime start2 = LocalDateTime.now();

        List<Car> reflectionCars = new ArrayList<>();
        try {
            for (int i = 0; i < cases; i++) {
                Class car = Car.class;
                    reflectionCars.add((Car) car.getConstructor().newInstance());
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        LocalDateTime end2 = LocalDateTime.now();
        Duration between2 = Duration.between(start2.toLocalTime(), end2.toLocalTime());
        System.out.println("between2 ))" + between2);

        // between1 ))PT0.001884S
        // between2 ))PT0.027349S
        // 약 14 ~ 15 배 차이 확인
    }
}
