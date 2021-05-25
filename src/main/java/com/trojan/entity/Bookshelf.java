package com.trojan.entity;

import java.util.Date;

public class Bookshelf {
	private int id;
	private User user;
	private Directory directory;
	private Chapter chapter;
	private Date readtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public Date getReadtime() {
		return readtime;
	}

	public void setReadtime(Date readtime) {
		this.readtime = readtime;
	}

	@Override
	public String toString() {
		return "Bookshelf [id=" + id + ", user=" + user + ", directory=" + directory + ", chapter=" + chapter
				+ ", readtime=" + readtime + "]";
	}

}
