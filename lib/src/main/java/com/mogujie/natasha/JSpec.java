package com.mogujie.natasha;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xiaochuang on 4/6/16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSpec {
    /**
     * A description of the test case. Let you get rid of wring "_" to test case names
     * @return
     */
    String desc() default "";
}
