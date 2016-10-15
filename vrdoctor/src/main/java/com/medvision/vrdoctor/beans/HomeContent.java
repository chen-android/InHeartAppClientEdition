/**
 * Copyright 2016 bejson.com
 */
package com.medvision.vrdoctor.beans;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Auto-generated: 2016-10-14 14:39:49
 */
public class HomeContent {
	private String creator;
	private int hidden;
	private int isFree;
	@SerializedName("config_isFree")
	private String configIsfree;
	private String remark;
	private String helpCode;
	private int type;
	@SerializedName("config_type")
	private String configType;
	private int status;
	@SerializedName("config_status")
	private String configStatus;
	private Date createdAt;
	private int price;
	private String name;
	private String updator;
	private String coverPic;
	private String id;
	private Date updatedAt;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getHidden() {
		return hidden;
	}

	public void setHidden(int hidden) {
		this.hidden = hidden;
	}

	public int getIsFree() {
		return isFree;
	}

	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}

	public String getConfigIsfree() {
		return configIsfree;
	}

	public void setConfigIsfree(String configIsfree) {
		this.configIsfree = configIsfree;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getConfigStatus() {
		return configStatus;
	}

	public void setConfigStatus(String configStatus) {
		this.configStatus = configStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}