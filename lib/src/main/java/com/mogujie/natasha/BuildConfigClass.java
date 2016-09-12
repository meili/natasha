package com.mogujie.natasha;

/**
 * Created by xiaochuang on 4/22/16.
 */
public class BuildConfigClass {
    private static Class<?> sBuildConfigClass;

    public static Class<?> get() {
        return sBuildConfigClass;
    }

    public static void setBuildConfigClass(Class clazz) {
        sBuildConfigClass = clazz;
    }

}
