package org.fjltax.sa.pojo;
/**
 * ˰������б�
 */
import java.util.ArrayList;
import java.util.List;

public class ResSwjgList {
  private int code=0;
  private String msg="";
  private List<SwjgInfo> swjgList=new ArrayList();  //˰������б�

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public List<SwjgInfo> getSwjgList() {
    return swjgList;
  }

  public void setSwjgList(List<SwjgInfo> swjgList) {
    this.swjgList = swjgList;
  }

  



}
