package cn.demo.entity;


import java.util.ArrayList;
import java.util.List;

public class Chinamath {

  private String syscode;
  private String columcode;
  private String columnamee;
  private String sort;
  private String parentcode;
  private int status;
  private String remark;
  private List<Chinamath> chirdern=new ArrayList<Chinamath>();

  public List<Chinamath> getChirdern() {
    return chirdern;
  }

  public void setChirdern(List<Chinamath> chirdern) {
    this.chirdern = chirdern;
  }

  public String getSyscode() {
    return syscode;
  }

  public void setSyscode(String syscode) {
    this.syscode = syscode;
  }

  public String getColumcode() {
    return columcode;
  }

  public void setColumcode(String columcode) {
    this.columcode = columcode;
  }

  public String getColumnamee() {
    return columnamee;
  }

  public void setColumnamee(String columnamee) {
    this.columnamee = columnamee;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public String getParentcode() {
    return parentcode;
  }

  public void setParentcode(String parentcode) {
    this.parentcode = parentcode;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
