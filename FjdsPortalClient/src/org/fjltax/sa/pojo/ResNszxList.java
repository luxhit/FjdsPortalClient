package org.fjltax.sa.pojo;
/**
 * 纳税咨询列表
 */
import java.util.ArrayList;
import java.util.List;

public class ResNszxList {
  private int code=0;
  private String msg="";
  private List<NszxItem> nszxList=new ArrayList();  //纳税咨询列表

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

  public List<NszxItem> getNszxList() {
    return nszxList;
  }

  public void setNszxList(List<NszxItem> nszxList) {
    this.nszxList = nszxList;
  }




}
