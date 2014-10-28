package org.fjltax.sa.pojo;

public class UserWbbd {
  private long hhId;   //用户ID
  private long wbbdxh;   //
  private String wbbdzt;   //0 未绑定 1 绑定中 2 绑定并启动 9 绑定失效
  private String wbbdsm;   //网报绑定说明
  private String wbhhm;   //网报用户名
  private String wbhhmc;   //
  private String wbhhmm;   //网报用户密码
  private String nsrdzdah;   //纳税人电子档案号
  private String dnbm;   //电脑编码
  private String nsrmc;   //纳税人名称
  public long getHhId() {
    return hhId;
  }
  public void setHhId(long hhId) {
    this.hhId = hhId;
  }
  public long getWbbdxh() {
    return wbbdxh;
  }
  public void setWbbdxh(long wbbdxh) {
    this.wbbdxh = wbbdxh;
  }
  public String getWbbdzt() {
    return wbbdzt;
  }
  public void setWbbdzt(String wbbdzt) {
    this.wbbdzt = wbbdzt;
  }
  public String getWbhhmc() {
    return wbhhmc;
  }
  public void setWbhhmc(String wbhhmc) {
    this.wbhhmc = wbhhmc;
  }
  public String getWbhhm() {
    return wbhhm;
  }
  public void setWbhhm(String wbhhm) {
    this.wbhhm = wbhhm;
  }
  public String getWbhhmm() {
    return wbhhmm;
  }
  public void setWbhhmm(String wbhhmm) {
    this.wbhhmm = wbhhmm;
  }
  public String getNsrdzdah() {
    return nsrdzdah;
  }
  public void setNsrdzdah(String nsrdzdah) {
    this.nsrdzdah = nsrdzdah;
  }
  public String getDnbm() {
    return dnbm;
  }
  public void setDnbm(String dnbm) {
    this.dnbm = dnbm;
  }
  public String getNsrmc() {
    return nsrmc;
  }
  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }
  public String getWbbdsm() {
    return wbbdsm;
  }
  public void setWbbdsm(String wbbdsm) {
    this.wbbdsm = wbbdsm;
  }

}
