package sss.reflection_test.plain.basic;


import sss.reflection_test.plain.basic.sub.SubCar;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class BasicMain {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        /** class */
        Class car = Car.class;
        Class car2 = Class.forName("sss.reflection_test.plain.basic.Car");
        System.out.println(car.getName() + "//" + car2.getName());
        // sss.reflection_test.plain.basic.Car//sss.reflection_test.plain.basic.Car
        System.out.println("==============================================");

        Class[] classes = car2.getClasses(); // 상속받은 클래스와 인터페이스를 포함하여 모든 public 요소 (접근 제한자 영향받음)
        Class[] declaredClasses = car.getDeclaredClasses(); // 해당 클래스에 직접 정의된 내용만(상속받은 클래스와 인터페이스를 제외 & access modifier 와 무관)
        System.out.println("classes))" + classes.length +" // " + declaredClasses.length);
        // classes))6 // 7

        for (int i = 0; i < classes.length; i++) {
            Class aClass = classes[i];
             System.out.println(aClass.getName()); // aClass.getTypeName()
        }
        // sss.reflection_test.plain.basic.Car$CustomInnerPublicInterface
        // sss.reflection_test.plain.basic.Car$CustomInnerPublicEnum
        // ...
        // basic.Vehicle$VehicleInnerClass
        for (int i = 0; i < declaredClasses.length; i++) {
            System.out.println(declaredClasses[i].getName());
        }
        // sss.reflection_test.plain.basic.Car$CustomInnerPublicInterface
        // ...
        // sss.reflection_test.plain.basic.Car$CustomInnerPrivateClass
        // sss.reflection_test.plain.basic.Car$CustomInnerPublicClass
        System.out.println("==============================================");

        // class 에 구현된 interface 확인
        Class[] interfaces = car.getInterfaces();
        System.out.println(interfaces.length  +" // " +  interfaces[0].getTypeName());;
        System.out.println("==============================================");
        /// ------------------------------------------------------------------------------------ ///


        /** Default 생성자를 이용한 객체 생성 */
        SubCar subCar = new SubCar();
        System.out.println(subCar.toString());

        // newInstance() 의 조건: public access modifier 를 가진 default constructor 가 있어야 한다.
        // Class 의 getConstructor0() 참고
        Car realCar = (Car) car.newInstance(); // exception 제일 많이 난 곳
        realCar.methodSout1();
        // Method1 impl.

        /// ------------------------------------------------------------------------------------ ///


        /** constructor */
        // 인자가 없는 생성자
        Constructor constructor = car.getDeclaredConstructor();
        // 생성자를 이용한 객체 생성
        Car realCarByConstructor = (Car) constructor.newInstance();

        // 여러 인자를 가진 생성자
        // 1.
        Constructor constructorWithString = car.getDeclaredConstructor(String.class);
        // 2.
        Class[] classArgs = new Class[2];
        classArgs[0] = String.class;
        classArgs[1] = int.class;
        Constructor declaredConstructor = car.getDeclaredConstructor(classArgs);
        Car car1 = (Car) declaredConstructor.newInstance("생성자 확인중_name", 4);
        System.out.println(car1.name + "//" + car1.capa);
        // 3.
        Constructor declaredConstructorParamTest = car.getDeclaredConstructor(String.class, int.class);
        Car car4 = (Car) declaredConstructorParamTest.newInstance("Class<?> ... args 라는 이름", 2);
        System.out.println(car4.name + "//" + car4.capa);
        System.out.println("==============================================");

        // 모든 생성자 (단 인자 없는 생성자들은 제외)
        Constructor constructors[] = car.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; i++) {
            Class[] parameterTypes = constructors[i].getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                System.out.println(parameterTypes[j].getName());
            }
        }

        // public 생성자만
        Constructor constructorsPublic[] = car.getConstructors();
        Arrays.stream(constructorsPublic).forEach(System.out::println);
        // public sss.reflection_test.plain.basic.Car(java.lang.String, int)
        // public sss.reflection_test.plain.basic.Car(java.lang.String)
        // public sss.reflection_test.plain.basic.Car()

        System.out.println("모든 생성자 ))" + constructors.length + " - " + constructorsPublic.length + "-");
        System.out.println("==============================================");

        /// ------------------------------------------------------------------------------------ ///


        /** field */
        // getDeclareXX: 해당 XX에 선언(또는 구현)만 되어 있으면 됨. 접근 제한자 상관없음.
        // getXX: 해당 XX나 super XX 에 선언(또는 구현)되어 있고 public 접근 제한자.
        Field fieldName = car.getField("name");
        Field privateFieldName = car.getDeclaredField("privateString");

        System.out.println("car.getDeclaredField ))" + fieldName);
        System.out.println("car.getDeclaredField ))" + privateFieldName);
        // car + car super 객체를 포함
        Field field = car.getField("testPublicAccessStr");
        System.out.println("car.getField ))" + field);

        // car 객체에 선언된 모든 field 가져오기
        Field[] fields = car.getDeclaredFields();
        System.out.println("car.getDeclaredFields ))" + fields.length);
        Arrays.stream(fields).forEach(System.out::println);
        // public java.lang.String sss.reflection_test.plain.basic.Car.name
        // ...
        // private java.lang.String sss.reflection_test.plain.basic.Car.privateString
        // protected boolean sss.reflection_test.plain.basic.Car.protectedBoolean
        // java.lang.Object sss.reflection_test.plain.basic.Car.defaultObject

        // car + super 객체의 모든 public field
        Field[] fieldsWithSuper = car.getFields();
        Arrays.stream(fieldsWithSuper).forEach(System.out::println);
        // public java.lang.String sss.reflection_test.plain.basic.Car.name
        // ...
        // public java.lang.String basic.Vehicle.testPublicAccessStr
        System.out.println("==============================================");

        /// ------------------------------------------------------------------------------------ ///


        /** 예제로 클래스 제어 및 setAccessible 기능 확인 */
        Class<Car> carClass = Car.class;
        Constructor constructorWithSetField = carClass.getConstructor(String.class, int.class);

        Car newInstance = (Car) constructorWithSetField.newInstance("example", 7);
        System.out.println("constructorWithSetField 1 ))" + newInstance.toString());
        Field newInstanceField = carClass.getField("name");
        newInstanceField.set(newInstance, "changeExampleName");

        System.out.println("constructorWithSetField 2 ))" + newInstance.toString());
        // constructorWithSetField 1 ))Car{name='example', capa=7}
        // constructorWithSetField 2 ))Car{name='changeExampleName', capa=7}

        Field privateString = carClass.getDeclaredField("privateString");
        privateString.setAccessible(true);
        privateString.set(newInstance, "modified-private string");
        System.out.println("setAccessible 1 ))" + newInstance.toStringExtended());
        // setAccessible 1 ))Car{name='changeExampleName', privateString='modified-private string', testStaticNum='3', capa=7}

        Method targetMethod1 = carClass.getMethod("methodSout5", int.class);
        int sample = 1;
        targetMethod1.invoke(newInstance, sample);

        Method targetMethod2 = carClass.getMethod("methodSout4");
        targetMethod2.invoke(newInstance);
        // Method5 not overriden, param=1
        // Method4 overriden.

        // Field testStaticNum = carClass.getDeclaredField("testStaticNum");
        // testStaticNum.setAccessible(true);
        // testStaticNum.set(newInstance, 123);
        // System.out.println("setAccessible 2 ))" + newInstance.toStringExtended());
        // setAccessible 2 ))Car{name='changeExampleName', privateString='modified-private string', testStaticNum='123', capa=7}

        System.out.println("==============================================");
        /// ------------------------------------------------------------------------------------ ///
    }
}
