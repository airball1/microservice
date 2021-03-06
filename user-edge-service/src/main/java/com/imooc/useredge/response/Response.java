package com.imooc.useredge.response;

import java.io.Serializable;

/**
 * @Author liuzike
 * @Date 1/7/21
 **/
public class Response implements Serializable {

    public static final Response USER_PASSWORD_INVALID = new Response("1001", "username or password invalid");

    public static final Response MOBILE_OR_EMAIL_REQUIRED = new Response("1002", "mobile or email is required");

    public static final Response SEND_VERIFYCODE_FAILED = new Response("1003", "send verify code failed");

    public static final Response VERIFY_CODE_INVALID = new Response("1004", "verifyCode invalid");

    public static final Response SUCCESS = new Response();

    private String code;

    private String message;

    public Response() {
        this.code = "0";
        this.message = "success";
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
