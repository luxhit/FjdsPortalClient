package org.fjltax.sa.pojo;
/**
 * 客户端参数，控制客户端的运行
 * @author Su
 *
 */
public class Khdcs {
  private String csm;   //参数名
  private String csz;   //值
  private String cssm;   //说明
  private String zt;   //状态
  public String getCsm() {
    return csm;
  }
  public void setCsm(String csm) {
    this.csm = csm;
  }
  public String getCsz() {
    return csz;
  }
  public void setCsz(String csz) {
    this.csz = csz;
  }
  public String getCssm() {
    return cssm;
  }
  public void setCssm(String cssm) {
    this.cssm = cssm;
  }
  public String getZt() {
    return zt;
  }
  public void setZt(String zt) {
    this.zt = zt;
  }

}
