package cn.demo.service;

import cn.demo.entity.SysRole;
import cn.demo.entity.SysUserRole;

import java.util.List;

/**
 * Created by dj on 2017/10/25.
 */
public interface IRoleService {
    public List<SysRole> allRole();

    public  List<SysUserRole>  idandrole(int id);
    public  int  deleterole(int userid);

    public  int  addrole(SysUserRole sysUserRole);






}
