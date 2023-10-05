package sss.reflection_test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // meta annotation
public @interface CustomInject {
}
