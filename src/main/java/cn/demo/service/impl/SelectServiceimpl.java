package cn.demo.service.impl;

import cn.demo.dao.ISelectUser;
import cn.demo.entity.TmsUser;
import cn.demo.service.ISelectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dj on 2017/10/16.
 */
@Service("selectService")
public class SelectServiceimpl implements ISelectService{
    @Resource(name = "ISelectUser")
     ISelectUser   user;

    public List<TmsUser> selectusername() {
        return user.selectusername();
    }
}
