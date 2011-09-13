package com.yourdomain.website.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * User类用于表示网站的用户信息
 */
public class User {
	/**
	 * 登录用户名（包含字母和数字），是网站用户的唯一标识
	 */
	private String username;
	private String password;
	private String email;
	/**
	 * 用户姓名，用于网站显示
	 */
	private String name;
	/**
	 * 用户头像地址
	 */

    private String uid;
    private int sex;
    private int star;
    private int zidou;
    private int vip;
    private String birthday;
    private String tinyurl;
    private String mainurl;
    private String headurl;
    private ArrayList<HashMap> hometown_location;
    private ArrayList<HashMap> university_info;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getZidou() {
        return zidou;
    }

    public void setZidou(int zidou) {
        this.zidou = zidou;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTinyurl() {
        return tinyurl;
    }

    public void setTinyurl(String tinyurl) {
        this.tinyurl = tinyurl;
    }

    public String getMainurl() {
        return mainurl;
    }

    public void setMainurl(String mainurl) {
        this.mainurl = mainurl;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public ArrayList<HashMap> getHometown_location() {
        return hometown_location;
    }

    public void setHometown_location(ArrayList<HashMap> hometown_location) {
        this.hometown_location = hometown_location;
    }

    public ArrayList<HashMap> getUniversity_info() {
        return university_info;
    }

    public void setUniversity_info(ArrayList<HashMap> university_info) {
        this.university_info = university_info;
    }
}