package com.inheart.inheartapp.beans;

/**
 * Created by cs on 16/9/22.
 */

public class Disease {
	private String diseaseName;
	private String firstSpell;

	public Disease(String diseaseName, String firstSpell) {
		this.diseaseName = diseaseName;
		this.firstSpell = firstSpell;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getFirstSpell() {
		return firstSpell;
	}

	public void setFirstSpell(String firstSpell) {
		this.firstSpell = firstSpell;
	}
}
