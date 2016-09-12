package com.mogujie.natasha;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by xiaochuang on 4/22/16.
 */
public class JSpecRule implements TestRule {
    @Override
    public Statement apply(final Statement base, final Description description) {
        final JSpec annotation = description.getAnnotation(JSpec.class);
        if (annotation == null) return base;

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String msg = description.getTestClass().getSimpleName() + "#" + description.getMethodName() + ": " + annotation.desc() + "...";
                try {
                    base.evaluate();
                } catch (Throwable e) {
                    String detailMessage = msg + "Failed!";
                    if (e.getMessage() != null) detailMessage += e.getMessage();
                    Throwable throwable = new Throwable(detailMessage, e);//, e);
                    throwable.setStackTrace(e.getStackTrace());
                    throw throwable;
                }
                System.out.print(msg);
                System.out.println("passed");
            }
        };
    }
}
