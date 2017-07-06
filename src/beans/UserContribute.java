package beans;

import java.io.Serializable;
import java.util.Date;

public class UserContribute implements Serializable {
	private static final long serialVersionUID = 1L;

	private int contributeId;
	private int userId;
	private String name;
	private String subject;
	private String text;
	private String category;
	private int branchId;
	private Date insertDate;
	private Date updateDate;

	public int getContributeId() {
		return contributeId;
	}

	public void setContributeId(int contributeId) {
		this.contributeId = contributeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
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