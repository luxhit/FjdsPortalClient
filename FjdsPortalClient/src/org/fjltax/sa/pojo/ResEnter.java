package org.fjltax.sa.pojo;
/**
 * 入口接口，取得模块列表、滚动图片列表、消息列表
 */
import java.util.ArrayList;
import java.util.List;

public class ResEnter {
  private int code=0;
  private String msg="";
  private List<PcModuleCode> pcModuleCodeList=new ArrayList();  //模块列表
  private List<Tzgg> tzggList=new ArrayList();  //通知公告列表
  private List<Gdtp> gdtpList=new ArrayList();  //滚动图片列表
  private List<Khdcs> khdcsList=new ArrayList();  //客户端参数列表

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

  public List<PcModuleCode> getPcModuleCodeList() {
    return pcModuleCodeList;
  }

  public void setPcModuleCodeList(List<PcModuleCode> pcModuleCodeList) {
    this.pcModuleCodeList = pcModuleCodeList;
  }

  public List<Tzgg> getTzggList() {
    return tzggList;
  }

  public void setTzggList(List<Tzgg> tzggList) {
    this.tzggList = tzggList;
  }

  public List<Gdtp> getGdtpList() {
    return gdtpList;
  }

  public void setGdtpList(List<Gdtp> gdtpList) {
    this.gdtpList = gdtpList;
  }

  public List<Khdcs> getKhdcsList() {
    return khdcsList;
  }

  public void setKhdcsList(List<Khdcs> khdcsList) {
    this.khdcsList = khdcsList;
  }


}
