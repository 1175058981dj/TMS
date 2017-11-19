package cn.demo.entity;


import java.util.ArrayList;
import java.util.List;

public class SysPrivilage {

  private int id;
  private String url;
  private String name;
  private String icon;
  private int parent;

  private List<SysPrivilage>  chirdern=new ArrayList<SysPrivilage>();

  public List<SysPrivilage> getChirdern() {
    return chirdern;
  }

  public void setChirdern(List<SysPrivilage> chirdern) {
    this.chirdern = chirdern;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public int getParent() {
    return parent;
  }

  public void setParent(int parent) {
    this.parent = parent;
  }
}
