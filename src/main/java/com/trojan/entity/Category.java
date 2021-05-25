package com.trojan.entity;

public class Category {

	private int cId;
	private String cName;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	@Override
	public String toString() {
		return "Category [cId=" + cId + ", cName=" + cName + "]";
	}

}
