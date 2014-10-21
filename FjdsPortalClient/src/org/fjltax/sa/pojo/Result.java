package org.fjltax.sa.pojo;

import java.util.ArrayList;
import java.util.List;

public class Result {
  private int code=0;  //0 正常返回， 否则其他返回
  private String msg="";
  
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
  
}
