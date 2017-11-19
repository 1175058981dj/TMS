package cn.demo.Controller;

import cn.demo.entity.*;
import cn.demo.service.IChinaService;
import cn.demo.service.ILoginUserService;
import cn.demo.service.IPrivilageService;
import cn.demo.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dj on 2017/10/19.
 */
@Controller
public class LoginUserController {
    @Resource(name = "loginUserService")
    ILoginUserService service;
    @Resource(name = "ChinamathService")
    IChinaService chinaservice;
    @Resource(name = "privilageService")
    IPrivilageService privilageService;

    @Resource(name = "RoleService")
    IRoleService roleService;

    @RequestMapping("/userLogin")
    @ResponseBody
    public Object userlogin(String username, String userpassword, HttpSession session) {
        TmsUser tmsUser = new TmsUser();
        tmsUser.setUsername(username);
        tmsUser.setUserpassword(userpassword);
        TmsUser login = service.login(tmsUser);
        session.setAttribute("login", login);
        return login;
    }

    @RequestMapping("/daoMain")
    public String daoMain(HttpSession session, Model model) {
        //新的容器，保存具有层级关系的集合
        List<SysPrivilage> tootmunu = new ArrayList<SysPrivilage>();
        TmsUser tmsUser = (TmsUser) session.getAttribute("login");
        System.out.println(tmsUser.getUserid());
        List<SysPrivilage> privilagelist = privilageService.getPrivilagelist(tmsUser.getUserid());
        for (SysPrivilage item : privilagelist) {
            SysPrivilage sysPrivilage = item;
            int pid = sysPrivilage.getParent();
            if (pid == 0) {
                tootmunu.add(item);
            } else {
                for (SysPrivilage items : privilagelist) {
                    int id = items.getId();
                    if (id == pid) {
                        SysPrivilage privilage = items;
                        privilage.getChirdern().add(sysPrivilage);
                        break;
                    }
                }
            }
        }
        model.addAttribute("tootmunu", tootmunu);
        return "main";
    }

    @ResponseBody
    @RequestMapping("/selectallprivilage")
    public Object selectallprivilage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //新的容器，保存具有层级关系的集合
        List<SysPrivilage> tootmunu = new ArrayList<SysPrivilage>();
        List<SysPrivilage> allprivilage = privilageService.allprivilage();
        for (SysPrivilage item : allprivilage) {
            SysPrivilage sysPrivilage = item;
            int pid = sysPrivilage.getParent();
            if (pid == 0) {
                tootmunu.add(item);
            } else {
                for (SysPrivilage items : allprivilage) {
                    int id = items.getId();
                    if (id == pid) {
                        SysPrivilage privilage = items;
                        privilage.getChirdern().add(sysPrivilage);
                        break;
                    }
                }
            }
        }
        return tootmunu;
    }

    @RequestMapping("selectallrole")
    @ResponseBody
    public Object allRole() {
        List<SysRole> roleList = roleService.allRole();
        return roleList;
    }

    @ResponseBody
    @RequestMapping("/selectidrole")
    public Object idrole(int id) {
        List<SysUserRole> idandrole = roleService.idandrole(id);
        return idandrole;
    }

    @RequestMapping("/deleterole")
    @ResponseBody
    public int deleterole(int userid) {
        int i = roleService.deleterole(userid);
        return i;
    }
    @RequestMapping("/addrole")
    @ResponseBody
    public  int addrole(int userid,int roleid){
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setUserid(userid);
        sysUserRole.setRoleid(roleid);
        int addrole = roleService.addrole(sysUserRole);
        return addrole;
    }
    @RequestMapping("/getAllPrivilageByRoid")
    @ResponseBody
    public  Object   getPByRId(String rid){
        List<SysPrivilage> allPrivilageByRoleid = privilageService.getAllPrivilageByRoleid(rid);
      return   allPrivilageByRoleid;
    }
    //参数rid是角色id  ls是选中的权限id的数组
    @RequestMapping("/deleteANDaddPrivilage")
    @ResponseBody
    public   void   addAndDelete(String rid,String ls){
              privilageService.deleteByRoleid(rid);
              int  roleid=Integer.parseInt(rid);
              String[]    idlist=ls.split(",");
        for (String item : idlist) {
                int  pid=Integer.parseInt(item);
            Map<String,Object>  map=new HashMap<String,Object>();
            map.put("rid",roleid);
            map.put("pid",pid);
            privilageService.addPrivilage(map);
        }
    }
}
