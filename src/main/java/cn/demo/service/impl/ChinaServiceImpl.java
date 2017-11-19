package cn.demo.service.impl;

import cn.demo.dao.IChinaMath;
import cn.demo.entity.Chinamath;
import cn.demo.entity.News;
import cn.demo.service.IChinaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dj on 2017/11/6.
 */
@Service("ChinamathService")
public class ChinaServiceImpl implements IChinaService {
    @Resource(name = "IChinaMath")
    IChinaMath  chinaMath;

    public List<Chinamath> selectallChinaDta() {
        return chinaMath.selectallChinaDta();
    }

    public PageInfo<News> selectallNews(Integer page, Integer rows, String columname) {
        PageHelper.startPage(page,rows);
        PageInfo<News>   pageInfo=new PageInfo<News>(chinaMath.selectallNews(columname));
        return pageInfo;
    }


    public PageInfo<News> selectalltitle(Integer page, Integer rows,@Param("title") String title, @Param("coumname") String coumname, @Param("status") String status) {
        PageHelper.startPage(page,rows);
        PageInfo<News>   pageInfo=new PageInfo<News>(chinaMath.selectalltitle(title,coumname,status));
        return pageInfo;
    }

    public int deletenews(String id) {
        return chinaMath.deletenews(id);
    }

    public int addnews(News news) {
        return chinaMath.addnews(news);
    }



    public List<News> selectcountcaogao(int userid) {
        return chinaMath.selectcountcaogao(userid);
    }

    public int selectcountcao(String columnamee) {
        return chinaMath.selectcountcao(columnamee);
    }

    public Chinamath selectlanmu(String columname) {
        return chinaMath.selectlanmu(columname);
    }

    public List<News> selectall(String parentCode) {
        return chinaMath.selectall(parentCode);
    }

    public News selectupdatechuan(String id) {
        return chinaMath.selectupdatechuan(id);
    }

    public int huifunews(String id) {
        return chinaMath.huifunews(id);
    }

    public int updatenews(News news) {
        return chinaMath.updatenews(news);
    }


}
