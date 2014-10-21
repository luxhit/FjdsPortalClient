package org.fjltax.sa.pojo;

public class PcModuleCode {
  private String moduleCode;   //模块代码，五位
  private String moduleName;   //模块名称
  private String moduleType;   //1：主模块，2：功能模块，0，其他模块类型（如用于菜单生成层次，公共）
  private String funcType;   //该模块的使用功能类型：是否需要登录 1 公共，使用不需要登录 2 个人模块，使用需要登录并绑定身份证号 3 企业模块，使用需要登录并绑定网报用户
  private String callMode;   //1 内部模块 2 http链接 3 外部模块（主界面） 4 外部模块（特定界面）
  private String dlls;   //调用的模块名，根据调用方法不同配置不同的值，如模块方法名，http链接等。
  private String ver;   //前端程序的最低版本
  private String moduleDesc;   //模块描述
  private String grade;   //0 通用模块 1 分配权限模块，暂无用，
  private String dutyGrade;   //见notes，暂无用
  private String shortCut;   //前端自动生成菜单使用，暂不用
  private String menuLevel;   //前端自动生成菜单使用（定义菜单层次），1表示主菜单，2表示2级菜单...
  private String parentMenu;   //定义父菜单
  private String iconFile;   //图标文件的下载地址
  private long noteCount;   //提醒数，用于在图标右上角生成红圈提醒，0表示没有不提醒
  private String ifCust;   //是否缺省定制模块，用于初始化定制模块（第一次使用软件）
  private String sts;   //‘A’表示再用，‘P'表示历史，所有的对此表的查询都应加此条件
  private String a1;   //A1
  private String a2;   //A2
  private String a3;   //A3
  
  
  public long getNoteCount() {
    return noteCount;
  }
  public void setNoteCount(long noteCount) {
    this.noteCount = noteCount;
  }
  public String getIfCust() {
    return ifCust;
  }
  public void setIfCust(String ifCust) {
    this.ifCust = ifCust;
  }
  public String getModuleCode() {
    return moduleCode;
  }
  public void setModuleCode(String moduleCode) {
    this.moduleCode = moduleCode;
  }
  public String getModuleName() {
    return moduleName;
  }
  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }
  public String getModuleType() {
    return moduleType;
  }
  public void setModuleType(String moduleType) {
    this.moduleType = moduleType;
  }
  public String getFuncType() {
    return funcType;
  }
  public void setFuncType(String funcType) {
    this.funcType = funcType;
  }
  public String getCallMode() {
    return callMode;
  }
  public void setCallMode(String callMode) {
    this.callMode = callMode;
  }
  public String getDlls() {
    return dlls;
  }
  public void setDlls(String dlls) {
    this.dlls = dlls;
  }
  public String getVer() {
    return ver;
  }
  public void setVer(String ver) {
    this.ver = ver;
  }
  public String getModuleDesc() {
    return moduleDesc;
  }
  public void setModuleDesc(String moduleDesc) {
    this.moduleDesc = moduleDesc;
  }
  public String getGrade() {
    return grade;
  }
  public void setGrade(String grade) {
    this.grade = grade;
  }
  public String getDutyGrade() {
    return dutyGrade;
  }
  public void setDutyGrade(String dutyGrade) {
    this.dutyGrade = dutyGrade;
  }
  public String getShortCut() {
    return shortCut;
  }
  public void setShortCut(String shortCut) {
    this.shortCut = shortCut;
  }
  public String getMenuLevel() {
    return menuLevel;
  }
  public void setMenuLevel(String menuLevel) {
    this.menuLevel = menuLevel;
  }
  public String getParentMenu() {
    return parentMenu;
  }
  public void setParentMenu(String parentMenu) {
    this.parentMenu = parentMenu;
  }
  public String getIconFile() {
    return iconFile;
  }
  public void setIconFile(String iconFile) {
    this.iconFile = iconFile;
  }
  public String getSts() {
    return sts;
  }
  public void setSts(String sts) {
    this.sts = sts;
  }
  public String getA1() {
    return a1;
  }
  public void setA1(String a1) {
    this.a1 = a1;
  }
  public String getA2() {
    return a2;
  }
  public void setA2(String a2) {
    this.a2 = a2;
  }
  public String getA3() {
    return a3;
  }
  public void setA3(String a3) {
    this.a3 = a3;
  }

  
  
}
