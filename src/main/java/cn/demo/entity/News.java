package cn.demo.entity;


import java.util.Date;
import java.util.List;

public class News {

    private String id;
    private String title;
    private String columname;
    private int clicks;
    private String creator;
    private Date updateTime;
    private String type;
    private String tag;
    private String imageurl;
    private String defaulttitle;
    private String updatedate;
    private String zhengwen;
    private Chinamath chinamath;
    private TmsUser tmsUser;
    private int countcaogao;
    private List<Chinamath> chinamathList;

    public List<Chinamath> getChinamathList() {
        return chinamathList;
    }

    public void setChinamathList(List<Chinamath> chinamathList) {
        this.chinamathList = chinamathList;
    }

    public int getCountcaogao() {
        return countcaogao;
    }

    public void setCountcaogao(int countcaogao) {
        this.countcaogao = countcaogao;
    }

    public String getZhengwen() {
        return zhengwen;
    }

    public void setZhengwen(String zhengwen) {
        this.zhengwen = zhengwen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColumname() {
        return columname;
    }

    public void setColumname(String columname) {
        this.columname = columname;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDefaulttitle() {
        return defaulttitle;
    }

    public void setDefaulttitle(String defaulttitle) {
        this.defaulttitle = defaulttitle;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public Chinamath getChinamath() {
        return chinamath;
    }

    public void setChinamath(Chinamath chinamath) {
        this.chinamath = chinamath;
    }

    public TmsUser getTmsUser() {
        return tmsUser;
    }

    public void setTmsUser(TmsUser tmsUser) {
        this.tmsUser = tmsUser;
    }
}
