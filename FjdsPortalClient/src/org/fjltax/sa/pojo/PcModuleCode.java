package org.fjltax.sa.pojo;

public class PcModuleCode {
  private String moduleCode;   //ģ����룬��λ
  private String moduleName;   //ģ������
  private String moduleType;   //1����ģ�飬2������ģ�飬0������ģ�����ͣ������ڲ˵����ɲ�Σ�������
  private String funcType;   //��ģ���ʹ�ù������ͣ��Ƿ���Ҫ��¼ 1 ������ʹ�ò���Ҫ��¼ 2 ����ģ�飬ʹ����Ҫ��¼�������֤�� 3 ��ҵģ�飬ʹ����Ҫ��¼���������û�
  private String callMode;   //1 �ڲ�ģ�� 2 http���� 3 �ⲿģ�飨�����棩 4 �ⲿģ�飨�ض����棩
  private String dlls;   //���õ�ģ���������ݵ��÷�����ͬ���ò�ͬ��ֵ����ģ�鷽������http���ӵȡ�
  private String ver;   //ǰ�˳������Ͱ汾
  private String moduleDesc;   //ģ������
  private String grade;   //0 ͨ��ģ�� 1 ����Ȩ��ģ�飬�����ã�
  private String dutyGrade;   //��notes��������
  private String shortCut;   //ǰ���Զ����ɲ˵�ʹ�ã��ݲ���
  private String menuLevel;   //ǰ���Զ����ɲ˵�ʹ�ã�����˵���Σ���1��ʾ���˵���2��ʾ2���˵�...
  private String parentMenu;   //���常�˵�
  private String iconFile;   //ͼ���ļ������ص�ַ
  private long noteCount;   //��������������ͼ�����Ͻ����ɺ�Ȧ���ѣ�0��ʾû�в�����
  private String ifCust;   //�Ƿ�ȱʡ����ģ�飬���ڳ�ʼ������ģ�飨��һ��ʹ�������
  private String sts;   //��A����ʾ���ã���P'��ʾ��ʷ�����еĶԴ˱�Ĳ�ѯ��Ӧ�Ӵ�����
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
