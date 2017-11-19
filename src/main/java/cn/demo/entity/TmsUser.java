package cn.demo.entity;


public class TmsUser {

  private int userid;
  private String username;
  private String userpassword;
  private int usertype;
  public  String  action="分配角色";

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUserpassword() {
    return userpassword;
  }

  public void setUserpassword(String userpassword) {
    this.userpassword = userpassword;
  }

  public int getUsertype() {
    return usertype;
  }

  public void setUsertype(int usertype) {
    this.usertype = usertype;
  }
}
