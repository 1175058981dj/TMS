package cn.demo.dao;

import cn.demo.entity.SysPrivilage;

import java.util.List;
import java.util.Map;

/**
 * Created by dj on 2017/10/20.
 */
public interface IPrivilageDao {
    public List<SysPrivilage>   getPrivilagelist(int id);
    public List<SysPrivilage>   allprivilage();
    //根据角色id查询对应角色下的所有权限
    public  List<SysPrivilage>  getAllPrivilageByRoleid(String roleid);
    //根据角色id删除对应的权限
    public  int    deleteByRoleid(String roleid);
    //添加权限  将角色id和权限id封装成一个map集合
    public   int   addPrivilage(Map<String,Object> map);
}
