package cn.demo.dao;

import cn.demo.entity.Chinamath;
import cn.demo.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dj on 2017/11/6.
 */
public interface IChinaMath {
    public List<Chinamath> selectallChinaDta();

    public List<News> selectallNews(String columname);

    public List<News> selectalltitle(@Param("title") String title, @Param("coumname") String coumname, @Param("status")String status);

    public   int  deletenews(String id);
    public  int  addnews(News news);
    //public  int  addcaogao(News news);
    public  List<News>  selectcountcaogao(int userid);
    public  int  selectcountcao(String columnamee);
    public   Chinamath  selectlanmu(String columname);
    public    List<News>   selectall(String parentCode);
    public  News  selectupdatechuan(String id);
    //恢复删除的新闻
    public   int  huifunews(String id);
   //修改新闻
    public   int  updatenews(News news);
}
