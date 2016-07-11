package com.lingxian.lingxian.bean;

/**
 * Created by Chan on 2016/7/11.
 *
 * 想法的pojo类
 */
public class XiangfaBean {

	private String title;
	private String time;
	// TODO : 使用云存储代替本地测试
	// private String thumbnailUrl; // 暂时以本地资源为例
	// private String avatarUrl;

	private Integer thumbnailResId;
	private Integer avatarResId;

	/* 主要内容采用字符串拼接形式，代表图文混杂的内容，以 ->R.id.xxx<- 代表图片资源Id，从字符串截取出来 */
	private String details;

	// TODO : 未来添加上传视频，音频功能

	public XiangfaBean(String title, String time, Integer thumbnailResId,
			Integer avatarResId, String details) {
		this.title = title;
		this.time = time;
		this.thumbnailResId = thumbnailResId;
		this.avatarResId = avatarResId;
		this.details = details;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getThumbnailResId() {
		return thumbnailResId;
	}

	public void setThumbnailResId(Integer thumbnailResId) {
		this.thumbnailResId = thumbnailResId;
	}

	public Integer getAvatarResId() {
		return avatarResId;
	}

	public void setAvatarResId(Integer avatarResId) {
		this.avatarResId = avatarResId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
