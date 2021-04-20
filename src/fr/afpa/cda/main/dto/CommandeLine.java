package fr.afpa.cda.main.dto;

import java.util.ArrayList;
import java.util.List;

public class CommandeLine {

	private String nom;
	private List<String> params = new ArrayList<String>();
	private List<String> options = new ArrayList<String>();

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public void addOption(String option) {
		this.options.add(option);
	}

	public void addParam(String param) {
		this.params.add(param);
	}

}
