package com.lingxian.lingxian.bean;

/**
 * Created by Administrator on 2016/7/12 0012.
 *
 *
 * 一个装点子界面数据的Bean
 */
public class DianziBean {
    private String tempdata;
    //TODO:没有后台，数据现在乱写
    //TODO:图片用picaso

    public DianziBean(String tempdata) {
        this.tempdata = tempdata;
    }

    public String getTempdata() {
        return tempdata;
    }

    public void setTempdata(String tempdata) {
        this.tempdata = tempdata;
    }
}
