package com.imooc.useredge.controller;


import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.immoc.service.MessageService;
import com.imooc.useredge.redis.RedisClient;
import com.imooc.useredge.response.LoginResponse;
import com.imooc.useredge.response.Response;
import com.imooc.usermodel.UserInfo;
import com.imooc.userservice.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @Author liuzike
 * @Date 1/7/21
 **/
@Controller
@RequestMapping("/user")
public class UserController {

//    @Reference(url = "dubbo://localhost:7911")
    @Autowired
    private UserService userService;

//    @Reference(url = "dubbo://localhost:9090")
    @Autowired
    private MessageService messageService;

    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("username")String username,
                      @RequestParam("password") String password) {

        //1.验证用户名密码
        UserInfo userInfo = userService.getUserByName(username);

        if(userInfo == null) {
            return Response.USER_PASSWORD_INVALID;
        }

        if (!userInfo.getPassword().equalsIgnoreCase(md5(password))) {
            return Response.USER_PASSWORD_INVALID;
        }

        //2.生成token
        String token = genToken();

        //3.缓存用户
        redisClient.set(token, userInfo, 3600);

        return new LoginResponse(token);
    }

    @RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public Response sendVerifyCode(@RequestParam(value = "mobile", required = false) String mobile,
                          @RequestParam(value = "email", required = false) String email) {

        String message = "Verify code is:";

        String code = randomCode("0123456789", 6);

        boolean result = false;

        if (StringUtils.isNotBlank(mobile)) {
            result = messageService.sendMobileMessage(mobile, message + code);
            redisClient.set(mobile, code);
        } else if (StringUtils.isNotBlank(email)){
            result = messageService.sendEmailMessage(email, message + code);
            redisClient.set(email, code);
        } else {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        if (!result) {
            return Response.SEND_VERIFYCODE_FAILED;
        }

        return Response.SUCCESS;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam(value = "mobile", required = false) String mobile,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam("verifyCode") String verifyCode) {

        if (StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        if (StringUtils.isNotBlank(mobile)) {
            String redisCode = redisClient.get(mobile);
            if (!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        } else {
            String redisCode = redisClient.get(email);
            if (!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(md5(password));
        userInfo.setMobile(mobile);
        userInfo.setEmail(email);

        userService.register(userInfo);

        return Response.SUCCESS;
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo authentication(@RequestHeader("token") String token) {

        return redisClient.get(token);
    }

    private String genToken() {
        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
    }

    private String randomCode(String s, int size) {
        StringBuilder result = new StringBuilder(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }

        return result.toString();
    }

    private String md5(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(password.getBytes("utf-8"));

            return HexUtils.toHexString(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }
}
