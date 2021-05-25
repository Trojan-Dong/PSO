package com.trojan.entity;

import java.util.Date;

public class Directory {
	private int nId;
	private String nName;
	private String nURL;
	private String nLURL;
	private int cId;
	private String author;
	private Date lastUpdateTime;
	private String introduction;
	private Category category;
	private String coverPic;

	public int getnId() {
		return nId;
	}

	public void setnId(int nId) {
		this.nId = nId;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	public String getnURL() {
		return nURL;
	}

	public void setnURL(String nURL) {
		this.nURL = nURL;
	}

	public String getnLURL() {
		return nLURL;
	}

	public void setnLURL(String nLURL) {
		this.nLURL = nLURL;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	@Override
	public String toString() {
		return "Directory [nId=" + nId + ", nName=" + nName + ", nURL=" + nURL + ", nLURL=" + nLURL + ", cId=" + cId
				+ ", author=" + author + ", lastUpdateTime=" + lastUpdateTime + ", introduction=" + introduction
				+ ", category=" + category + ", coverPic=" + coverPic + "]";
	}

}
