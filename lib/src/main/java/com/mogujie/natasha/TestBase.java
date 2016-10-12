package com.mogujie.natasha;

/**
 * Created by xiaochuang on 3/4/16.
 */

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Rule;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Extend Mockito so that any method in Mockito is make available in TestBase, OH YEAH, I'm a genius!!!
 * Why? Because very time you mock or spy, you have to static import the method or use Mockito.methodYouWant, which is annoying!
 * Created by xiaochuang on 12/17/15.
 */
public class TestBase extends Mockito {

    /**
     * Inject mocks before every test method runs
     */
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public JSpecRule jSpecRule = new JSpecRule();

    public CountDownLatch latch = new CountDownLatch(1);

    /**
     * Short for assertEquals
     * @param str1
     * @param str2
     */
    public void ae(String str1, String str2) {
        assertEquals(str1, str2);
    }

    /**
     * Short for Assert.assertNotNull
     * @param single
     */
    public void ann(Object single) {
        assertNotNull(single);
    }

    /**
     * Short for Assert.assertEqual
     * @param expected
     * @param actual
     */
    protected void ae(int expected, int actual) {
        assertEquals(expected, actual);
    }

    /**
     * Short for Assert.assertEqual
     *
     * @param msg
     * @param expected
     * @param actual
     */
    protected void ae(String msg, int expected, int actual) {
        assertEquals(msg, expected, actual);
    }

    /**
     * Short for Assert.assertTrue
     * @param condition
     */
    protected void at(boolean condition) {
        Assert.assertTrue(condition);
    }


    /**
     * Short for Assert.assertTrue
     *
     * @param condition
     */
    protected void at(String msg, boolean condition) {
        Assert.assertTrue(msg, condition);
    }


    /**
     * Short for Assert.assertTrue(true);
     */
    protected void at() {
        at(true);
    }


    /**
     * Short for Assert.assertNull
     * @param object
     */
    protected void an(Object object) {
        org.junit.Assert.assertNull(object);
    }

    /**
     * Same as Assert.fail, save you from annoying static import.
     */
    protected void fail() {
        Assert.fail();
    }

    /**
     * Short for Assert.assertFalse
     * @param b
     */
    protected void af(String msg, boolean b) {
        Assert.assertFalse(msg, b);
    }

    /**
     * Short for Assert.assertFalse
     *
     * @param b
     */
    protected void af(boolean b) {
        Assert.assertFalse(b);
    }

    /**
     * Short for Assert.assertEqual
     * @param expected
     * @param actual
     */
    protected void ae(float expected, float actual) {
        assertEquals(expected, actual);
    }

    /**
     * Short for Assert.assertEqual
     * @param expected
     * @param actual
     */
    protected void ae(Object expected, Object actual) {
        assertEquals(expected, actual);
    }

    /**
     * Assert these two are of the same class
     * @param expected
     * @param actual
     */
    protected void ac(Object expected, Object actual) {
        ae(expected.getClass(), actual.getClass());
    }

    /**
     * Put your test data file under src/test/resources/file_name.json, and pass in 'file_name.json', this method will
     * read the content of the file as String and use Gson to convert to an Object of the class in the argument.
     * @param resourceName the file name of your test resource
     * @param clazz the class of Object you want to return
     * @param <T>
     * @return an Object of T
     * @throws IOException
     */
    public <T> T dataFromResource(String resourceName, Class<T> clazz) {
        return json2Data(readResource(resourceName), clazz);
    }

    public String readResource(String resourceName) {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(resourceName);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            return new String(bytes, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetLatch(int count) {
        latch = new CountDownLatch(count);
    }

    public void await() throws InterruptedException {
        latch.await();
    }

    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
        return latch.await(timeout, unit);
    }

    public void countDown() {
        latch.countDown();
    }

    /**
     * 
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> T json2Data(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

}

