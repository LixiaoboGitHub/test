package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Emp implements Serializable {
 private Integer eid;
 private String ename;
 private String sex;
 private String address;
 private Date birthday;
 private String photo="default.jpg";
 private Integer depid;
 //与页面相关属性
 //1.日期字符串转换
 private String sdate;
 //2.文件上传
 private MultipartFile pic;
 //3.页面展示部门名称
 private String depname;
 //4.页面展示薪资数值
 private Float emoney;
 //5.页面展示福利
 private String[] wids;
 private List<Welfare> lswf;
public Emp() {
	super();
	// TODO Auto-generated constructor stub
}
public Emp(Integer eid, String ename, String sex, String address, Date birthday, String photo, Integer depid,
		String sdate, MultipartFile pic, String depname, Float emoney, String[] wids, List<Welfare> lswf) {
	super();
	this.eid = eid;
	this.ename = ename;
	this.sex = sex;
	this.address = address;
	this.birthday = birthday;
	this.photo = photo;
	this.depid = depid;
	this.sdate = sdate;
	this.pic = pic;
	this.depname = depname;
	this.emoney = emoney;
	this.wids = wids;
	this.lswf = lswf;
}

public Emp(String ename, String sex, String address, String photo, Integer depid, String sdate, MultipartFile pic,
		Float emoney, String[] wids) {
	super();
	this.ename = ename;
	this.sex = sex;
	this.address = address;
	this.photo = photo;
	this.depid = depid;
	this.sdate = sdate;
	this.pic = pic;
	this.emoney = emoney;
	this.wids = wids;
}
public Integer getEid() {
	return eid;
}
public void setEid(Integer eid) {
	this.eid = eid;
}
public String getEname() {
	return ename;
}
public void setEname(String ename) {
	this.ename = ename;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
public Integer getDepid() {
	return depid;
}
public void setDepid(Integer depid) {
	this.depid = depid;
}
public String getSdate() {
	if(birthday!=null){
	sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
	}
	return sdate;
}
public void setSdate(String sdate) {
	if(sdate!=null){
		try {
			birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	this.sdate = sdate;
}
public MultipartFile getPic() {
	return pic;
}
public void setPic(MultipartFile pic) {
	this.pic = pic;
}
public String getDepname() {
	return depname;
}
public void setDepname(String depname) {
	this.depname = depname;
}
public Float getEmoney() {
	return emoney;
}
public void setEmoney(Float emoney) {
	this.emoney = emoney;
}
public String[] getWids() {
	return wids;
}
public void setWids(String[] wids) {
	this.wids = wids;
}
public List<Welfare> getLswf() {
	return lswf;
}
public void setLswf(List<Welfare> lswf) {
	this.lswf = lswf;
}
@Override
public String toString() {
	return "Emp [eid=" + eid + ", ename=" + ename + ", sex=" + sex + ", address=" + address + ", birthday=" + birthday
			+ ", photo=" + photo + ", depid=" + depid + ", sdate=" + sdate + ", pic=" + pic + ", depname=" + depname
			+ ", emoney=" + emoney + ", wids=" + Arrays.toString(wids) + ", lswf=" + lswf + "]";
}
 
}
