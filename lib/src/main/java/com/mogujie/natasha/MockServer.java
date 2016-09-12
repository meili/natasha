package com.mogujie.natasha;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaochuang on 1/8/16.
 */
public class MockServer {

    private static final Map<String, MockResponse> sRoutes = new HashMap<>();

    private static void addRoute(String url, String data) {
        sRoutes.put(url, new MockResponse(data));
    }

    private static void addRoute(String url, Object data) {
        sRoutes.put(url, new MockResponse(data));
    }

    /**
     * Put your test code in testRunnable.
     * When running test, the request for {@code url} will get a {@link MockResponse} with the json field to be the json format of {@code data}
     *
     * @param url          the target url
     * @param json         json to return as in the returned {@link MockResponse} if a request for {@code url} is requested
     * @param testRunnable the test code to run.
     */
    public static void withRoute(String url, String json, Runnable testRunnable) {
        addRoute(url, json);
        testRunnable.run();
        removeRoute(url);
    }

    public static void withRoute(String url, Object data, Runnable runnable) {
        addRoute(url, data);
        runnable.run();
        removeRoute(url);
    }

    @NonNull
    public static MockResponse get(String url) {
        log("Got request: "+url);
        MockResponse mockResponse = sRoutes.get(url);
        if (mockResponse==null) mockResponse = new MockResponse("Not found", 404);
        log("Returning: "+mockResponse);
        return mockResponse;
    }

    public static void removeRoute(String s) {
        sRoutes.remove(s);
    }

    private static void log(String str) {
        System.out.println(str);
    }
}
