package cn.demo.service.impl;

import cn.demo.dao.IPrivilageDao;
import cn.demo.entity.SysPrivilage;
import cn.demo.service.IPrivilageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by dj on 2017/10/20.
 */
@Service("privilageService")
public class PrivilageServiceImpl implements IPrivilageService {
    @Resource(name = "IPrivilageDao")
    IPrivilageDao  dao;
    public List<SysPrivilage> getPrivilagelist(int id) {
        return dao.getPrivilagelist(id);
    }

    public List<SysPrivilage> allprivilage() {
        return dao.allprivilage();
    }

    public List<SysPrivilage> getAllPrivilageByRoleid(String roleid) {
        return dao.getAllPrivilageByRoleid(roleid);
    }

    public int deleteByRoleid(String roleid) {
        return dao.deleteByRoleid(roleid);
    }

    public int addPrivilage(Map<String, Object> map) {
        return dao.addPrivilage(map);
    }
}
