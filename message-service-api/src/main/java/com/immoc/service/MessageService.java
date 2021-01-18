package com.immoc.service;

/**
 * @Author liuzike
 * @Date 1/6/21
 **/
public interface MessageService {

    boolean sendMobileMessage(String mobile, String message);

    boolean sendEmailMessage(String email, String message);
}
