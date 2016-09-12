package com.mogujie.natasha;

import com.google.gson.Gson;

/**
 * Created by xiaochuang on 1/8/16.
 */
public class MockResponse {
    public final int code;
    public final String msg;
    public final String json;

    public MockResponse(String json) {
        this(200, null, json);
    }

    public MockResponse(Object data) {
        this(200, null, toJson(data));
    }

    private static String toJson(Object data) {
        return new Gson().toJson(data);
    }


    public MockResponse(String msg, int code) {
        this(code, msg, null);
    }

    public MockResponse(int code, String msg, String json) {
        this.code = code;
        this.msg = msg;
        this.json = json;
    }

    public boolean successful() {
        return json != null;
    }

    @Override
    public String toString() {
        return "Status: "+ code+", msg: "+msg+", body: "+json;
    }

}
