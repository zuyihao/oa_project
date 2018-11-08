package com.lin.model.base.hyey;

import java.util.List;

public class HyeyData{
	private boolean ok;
	private List<HyeyGood> tables;
	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public List<HyeyGood> getTables() {
		return tables;
	}
	public void setTables(List<HyeyGood> tables) {
		this.tables = tables;
	}
	@Override
	public String toString() {
		return "ok:"+ok+",tables:"+tables;
	}
}