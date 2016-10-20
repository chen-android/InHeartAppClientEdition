package com.medvision.vrdoctor.beans;

import java.util.List;

/**
 * Created by cs on 2016/10/18.
 */

public class FilterDisease {
	private String letter;

	private List<DiseaseItem> array;

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public List<DiseaseItem> getArray() {
		return array;
	}

	public void setArray(List<DiseaseItem> array) {
		this.array = array;
	}

	public class DiseaseItem {
		private String id;
		private String creator;
		private String createdAt;
		private String updator;
		private String updatedAt;
		private String helpCode;
		private String name;
		private String pid;
		private String sort;
		private String remark;
		private int status;
		private String hidden;
		private String letter;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getCreator() {
			return creator;
		}

		public void setCreator(String creator) {
			this.creator = creator;
		}

		public String getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}

		public String getUpdator() {
			return updator;
		}

		public void setUpdator(String updator) {
			this.updator = updator;
		}

		public String getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}

		public String getHelpCode() {
			return helpCode;
		}

		public void setHelpCode(String helpCode) {
			this.helpCode = helpCode;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPid() {
			return pid;
		}

		public void setPid(String pid) {
			this.pid = pid;
		}

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			this.sort = sort;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getHidden() {
			return hidden;
		}

		public void setHidden(String hidden) {
			this.hidden = hidden;
		}

		public String getLetter() {
			return letter;
		}

		public void setLetter(String letter) {
			this.letter = letter;
		}
	}
}
