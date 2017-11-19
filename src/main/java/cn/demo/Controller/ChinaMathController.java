package cn.demo.Controller;

import cn.demo.entity.Chinamath;
import cn.demo.entity.News;
import cn.demo.entity.TmsUser;
import cn.demo.service.IChinaService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dj on 2017/11/6.
 */
@Controller
public class ChinaMathController {
    @Resource(name = "ChinamathService")
    IChinaService service;

    //根据栏目名称模糊查询对应的栏目并且选中
    @RequestMapping("/selectlanmu")
    @ResponseBody
    public Object selectlanmu(String columname) {

        System.out.println(columname);
        Chinamath selectlanmu = service.selectlanmu(columname);
        String columname1 = selectlanmu.getColumnamee();

        return columname1;
    }

    //根据用户id查询草稿箱的信息
    @RequestMapping("/selectallcapgao")
    @ResponseBody
    public Object showcaogao(HttpServletRequest request) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        TmsUser login = (TmsUser) request.getSession().getAttribute("login");
        int userid = login.getUserid();
        List<News> selectcountcaogao = service.selectcountcaogao(userid);
        for (News item : selectcountcaogao) {
            for (Chinamath g : item.getChinamathList()) {
                map.put(g.getColumnamee(), service.selectcountcao(g.getColumnamee()));
            }
        }
        return JSONObject.toJSONString(map);
    }

    //添加的方法
    @RequestMapping("/addnews")
    public void addnews(HttpServletResponse response, News news, HttpServletRequest request, @RequestParam MultipartFile file) throws IOException {
        String path = new Date().getTime() + file.getOriginalFilename();
        String rootPath = request.getSession().getServletContext().getRealPath("/upload");
        File newFile = new File(rootPath, path);
        file.transferTo(newFile);
        news.setImageurl(rootPath + "\\" + path);
        Object columname = request.getSession().getAttribute("columname");
        news.setColumname(columname.toString());
        TmsUser login = (TmsUser) request.getSession().getAttribute("login");
        int userid = login.getUserid();
        news.setCreator(String.valueOf(userid));
        service.addnews(news);
        PrintWriter writer = response.getWriter();
        writer.write("true");
    }

    @RequestMapping("/updatenews")
    @ResponseBody
    public void updatenews(HttpServletResponse response, News news, HttpServletRequest request, @RequestParam MultipartFile file) throws IOException {
        String path = new Date().getTime() + file.getOriginalFilename();
        String rootPath = request.getSession().getServletContext().getRealPath("/upload");
        File newFile = new File(rootPath, path);
        file.transferTo(newFile);
        news.setImageurl(rootPath + "\\" + path);
        Object columname = request.getSession().getAttribute("columname");
        news.setColumname(columname.toString());
        TmsUser login = (TmsUser) request.getSession().getAttribute("login");
        int userid = login.getUserid();
        news.setCreator(String.valueOf(userid));
        service.updatenews(news);
        PrintWriter writer = response.getWriter();
        writer.write("true");
    }

    //删除的方法
    @RequestMapping("/deletenews")
    @ResponseBody
    public int deletenews(String id) {
        int deletenews = service.deletenews(id);
        return deletenews;
    }

    @RequestMapping("/huifunews")
    @ResponseBody
    public int huifunews(String id) {
        int huifunews = service.huifunews(id);
        return huifunews;
    }

    //多条件查询的方法
    @RequestMapping("/selectmoretitle")
    @ResponseBody
    public Object selectallmore(Integer page, Integer rows, String title, HttpServletRequest request, String status) {
        Map<String, Object> map = new HashMap<String, Object>();
        Object columname = request.getSession().getAttribute("columname");
        String coumname = columname.toString();
        PageInfo<News> selectalltitle = service.selectalltitle(page, rows, title, coumname, status);
        for (News item : selectalltitle.getList()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(item.getUpdateTime());
            item.setUpdatedate(format);
        }
        map.put("rows", selectalltitle.getList());
        map.put("total", selectalltitle.getTotal());
        return map;
    }

    //树形菜单的方法
    @RequestMapping("/selectallChinaMath")
    @ResponseBody
    public Object allChinaMath() {
        List<Chinamath> toomenu = new ArrayList<Chinamath>();
        List<Chinamath> chinamaths = service.selectallChinaDta();
        for (Chinamath item : chinamaths) {
            Chinamath chinamath = item;
            String parentcode = chinamath.getParentcode();
            if (parentcode.equals("0")) {
                toomenu.add(item);
            } else {
                for (Chinamath items : chinamaths) {
                    String syscode = items.getSyscode();
                    if (syscode.equals(parentcode)) {
                        Chinamath chinamath1 = items;
                        chinamath1.getChirdern().add(chinamath);
                        break;
                    }
                }
            }
        }
        return toomenu;
    }

    //默认列表的方法
    @RequestMapping("/selectChinaMathById")
    @ResponseBody
    public Object addByname(String mycolumname, String columname, Integer page, Integer rows, HttpServletRequest request, String status) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(mycolumname);
        System.out.println(columname);
        System.out.println(page);
        System.out.println(rows);
        request.getSession().setAttribute("columname", columname);
        request.getSession().setAttribute("mycolumname", mycolumname);
        PageInfo<News> pageInfo = service.selectallNews(page, rows, columname);
        for (News item : pageInfo.getList()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(item.getUpdateTime());
            item.setUpdatedate(format);
        }
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @RequestMapping("/tiao")
    public String tiao(String id, String types, Model model, HttpServletRequest request) {
        if (types.equals("add")) {
            System.out.println(id);
            System.out.println(types);
            Object mycolumname = request.getSession().getAttribute("mycolumname");
            System.out.println(mycolumname);
            model.addAttribute("mycolumname", mycolumname);
            model.addAttribute("types", types);
            return "/page/addColumnDetail";
        } else {
            System.out.println(id);
            System.out.println(types);
            Object mycolumname = request.getSession().getAttribute("mycolumname");
            model.addAttribute("mycolumname", mycolumname);
            model.addAttribute("id", id);
            model.addAttribute("types", types);
            return "/page/addColumnDetail";
        }

    }

    @RequestMapping("/updatechuanzhi")
    @ResponseBody
    public Object updatechuanzhi(String id) {
        News selectupdatechuan = service.selectupdatechuan(id);
        return selectupdatechuan;
    }

    @RequestMapping("/selectallchinamath")
    public ModelAndView selectallchinamath(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView("/font/index");
        List<Chinamath> toomenu = new ArrayList<Chinamath>();
        List<Chinamath> chinamaths = service.selectallChinaDta();
        Map<String, List<News>> map = new HashMap<String, List<News>>();
        for (Chinamath item : chinamaths) {
            Chinamath chinamath = item;

            String parentcode = chinamath.getParentcode();
            if (parentcode.equals("0")) {
                map.put(item.getColumnamee(), service.selectall(item.getSyscode()));
                toomenu.add(item);
            } else {
                for (Chinamath items : chinamaths) {
                    String syscode = items.getSyscode();
                    if (syscode.equals(parentcode)) {
                        Chinamath chinamath1 = items;
                        chinamath1.getChirdern().add(chinamath);
                        break;
                    }
                }
            }
        }

        mv.addObject("selectall1", map);
        mv.addObject("toomenu", toomenu);

        return mv;
    }
}
