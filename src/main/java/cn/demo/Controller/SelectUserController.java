package cn.demo.Controller;

import cn.demo.entity.TmsUser;
import cn.demo.service.ISelectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dj on 2017/10/16.
 */
@Controller
public class SelectUserController {
    @Resource(name = "selectService")
    ISelectService  selectService;
    @ResponseBody
@RequestMapping("/selectusername")
    public   Object    selectUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TmsUser> selectusername = selectService.selectusername();
     return   selectusername;
    }


}
