package project.java2014.Bernie;

import java.io.File;

public class PicContainer {
	private String comment = "";
	private File picture;
	
	public PicContainer(File picture) {
		this.picture = picture;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the picture
	 */
	public File getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(File picture) {
		this.picture = picture;
	}
}
