package org.fjltax.sa.pojo;

public class Gdtp {
  private long tpId;   //图片ID
  private String tpbt;   //图片标题
  private String tpdz;   //图片地址
  private String zsfs;   //1 通知页面 2 http网页，固定传入消息ID和用户ID 3 activity，固定传入消息ID
  private String zsdz;   //展示地址
  private String zt;   //状态
  public long getTpId() {
    return tpId;
  }
  public void setTpId(long tpId) {
    this.tpId = tpId;
  }
  public String getTpbt() {
    return tpbt;
  }
  public void setTpbt(String tpbt) {
    this.tpbt = tpbt;
  }
  public String getTpdz() {
    return tpdz;
  }
  public void setTpdz(String tpdz) {
    this.tpdz = tpdz;
  }
  public String getZsfs() {
    return zsfs;
  }
  public void setZsfs(String zsfs) {
    this.zsfs = zsfs;
  }
  public String getZsdz() {
    return zsdz;
  }
  public void setZsdz(String zsdz) {
    this.zsdz = zsdz;
  }
  public String getZt() {
    return zt;
  }
  public void setZt(String zt) {
    this.zt = zt;
  }

}
