package beans;

import java.io.Serializable;
import java.util.Date;

public class Contribute implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int userId;
	private String subject;
	private String text;
	private String category;
	private String category2;
	private Date insertDate;
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int i) {
		this.userId = i;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}


	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
