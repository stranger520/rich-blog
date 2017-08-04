package com.zuicoding.platform.blog.service.impl;

import com.zuicoding.platform.blog.dao.WpUserMapper;
import com.zuicoding.platform.blog.modal.WpUser;
import com.zuicoding.platform.blog.service.IUserService;
import com.zuicoding.platform.blog.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Stephen.lin on 2017/8/4.
 * <p>
 * Description :<p></p>
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private WpUserMapper wpUserMapper;

    public WpUser login(WpUser user){
        if (user == null ||
                StringUtils.isBlank(user.getUserLogin())
                ||StringUtils.isBlank(user.getUserPass())){
            throw new IllegalArgumentException("用户名或密码不能为空!");

        }
        try {
            user.setUserPass(MD5Util.EncoderByMd5(user.getUserPass()));
        }catch (Exception e){
            throw new IllegalArgumentException("密码加密失败");
        }

        user = wpUserMapper.selectUserByUserNameAndPassword(
                user.getUserLogin(),user.getUserPass());

        return user;
    }

}
