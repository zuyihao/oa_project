package com.lin.model.base.hyey;

public class HyeyResponseEntity {

	private String data;
	private HyeyPage total;
	public HyeyPage getTotal() {
		return total;
	}
	public void setTotal(HyeyPage total) {
		this.total = total;
	}
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}

