package org.fjltax.sa.pojo;
/**
 * �������б�
 */
import java.util.ArrayList;
import java.util.List;

public class ResUserWbbdList {
  private int code=0;
  private String msg="";
  private List<UserWbbd> userwWbbdList=new ArrayList();  //�������б�

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

  public List<UserWbbd> getUserwWbbdList() {
    return userwWbbdList;
  }

  public void setUserwWbbdList(List<UserWbbd> userwWbbdList) {
    this.userwWbbdList = userwWbbdList;
  }



}
