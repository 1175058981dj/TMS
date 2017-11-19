package cn.demo.service.impl;

import cn.demo.dao.IRoleDao;
import cn.demo.entity.SysRole;
import cn.demo.entity.SysUserRole;
import cn.demo.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dj on 2017/10/25.
 */
@Service("RoleService")
public class RoleServiceImpl implements IRoleService {
    @Resource(name = "IRoleDao")
    IRoleDao  dao;
    public List<SysRole> allRole() {
        return dao.allRole();
    }

    public List<SysUserRole> idandrole(int id) {
        return dao.idandrole(id);
    }

    public int deleterole(int userid) {
        return dao.deleterole(userid);
    }

    public int addrole(SysUserRole sysUserRole) {
        return dao.addrole(sysUserRole);
    }


}
