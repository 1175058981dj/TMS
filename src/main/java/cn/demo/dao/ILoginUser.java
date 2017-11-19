package cn.demo.dao;

import cn.demo.entity.TmsUser;

/**
 * Created by dj on 2017/10/19.
 */
public interface ILoginUser {
    //登录方法
    public TmsUser   login(TmsUser tmsUser);
}
