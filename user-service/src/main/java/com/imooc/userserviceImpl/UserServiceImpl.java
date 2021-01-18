package com.imooc.userserviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.imooc.usermodel.TeacherDTO;
import com.imooc.usermodel.UserInfo;
import com.imooc.userservice.UserService;
import com.imooc.userservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author liuzike
 * @Date 1/6/21
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public UserInfo getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public UserInfo getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public TeacherDTO getTeacherById(Integer id) {
        return userMapper.getTeacherById(id);
    }

    public void register(UserInfo userInfo) {
        userMapper.registerUser(userInfo);
    }
}
