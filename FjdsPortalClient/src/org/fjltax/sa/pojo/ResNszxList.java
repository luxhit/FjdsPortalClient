package org.fjltax.sa.pojo;
/**
 * ��˰��ѯ�б�
 */
import java.util.ArrayList;
import java.util.List;

public class ResNszxList {
  private int code=0;
  private String msg="";
  private List<NszxItem> nszxList=new ArrayList();  //��˰��ѯ�б�

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
