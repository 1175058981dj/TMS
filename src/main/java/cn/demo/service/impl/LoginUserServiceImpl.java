package cn.demo.service.impl;

import cn.demo.dao.ILoginUser;
import cn.demo.entity.TmsUser;
import cn.demo.service.ILoginUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dj on 2017/10/19.
 */
@Service("loginUserService")
public class LoginUserServiceImpl implements ILoginUserService {
   @Resource(name = "ILoginUser")
    ILoginUser  dao;
    public TmsUser login(TmsUser tmsUser) {
        return dao.login(tmsUser);
    }
}
