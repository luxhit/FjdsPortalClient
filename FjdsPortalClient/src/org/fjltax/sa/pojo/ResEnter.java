package org.fjltax.sa.pojo;
/**
 * ��ڽӿڣ�ȡ��ģ���б�����ͼƬ�б���Ϣ�б�
 */
import java.util.ArrayList;
import java.util.List;

public class ResEnter {
  private int code=0;
  private String msg="";
  private List<PcModuleCode> pcModuleCodeList=new ArrayList();  //ģ���б�
  private List<Tzgg> tzggList=new ArrayList();  //֪ͨ�����б�
  private List<Gdtp> gdtpList=new ArrayList();  //����ͼƬ�б�
  private List<Khdcs> khdcsList=new ArrayList();  //�ͻ��˲����б�

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
