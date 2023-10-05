package sss.reflection_test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    // static
    public static <T> T getObjectCustom(Class<T> classType) {
        T classIns = createInstanceCustom(classType); // BookService 와 같은 당사자 클래스 인스턴스 생성

        Arrays.stream(classType.getDeclaredFields()).forEach(field -> {
            // CustomInject annotation 여부 확인
            if (field.getAnnotation(CustomInject.class) != null) {
                Object insByFieldTypeClass = createInstanceCustom(field.getType());
                field.setAccessible(true);
                
                // 클래스의 필드에 필드 타입의 클래스 인스턴스를 할당, 주입
                try {
                    field.set(classIns, insByFieldTypeClass);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return classIns;
    }

    private static <T> T createInstanceCustom(Class<T> classType) {
        try {
            return classType.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
