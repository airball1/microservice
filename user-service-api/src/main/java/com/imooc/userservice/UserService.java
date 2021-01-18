package com.imooc.userservice;

import com.imooc.usermodel.TeacherDTO;
import com.imooc.usermodel.UserInfo;

/**
 * @Author liuzike
 * @Date 1/6/21
 **/
public interface UserService {

    UserInfo getUserById(Integer id);

    UserInfo getUserByName(String username);

    TeacherDTO getTeacherById(Integer id);

    void register(UserInfo userInfo);
}
