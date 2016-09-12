package com.mogujie.lib;

import com.mogujie.natasha.JSpec;
import com.mogujie.natasha.TestBase;

import org.junit.Test;
import org.mockito.Mock;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class UserModelTest extends TestBase {

    @Test
    @JSpec(desc = "should display this description")
    public void testJSpecRule2() {
        at(true);
    }

    @Mock Object string;

    @Test
    @JSpec(desc = "should string mock not null")
    public void testmockrule() {
        ann(string);
    }
}