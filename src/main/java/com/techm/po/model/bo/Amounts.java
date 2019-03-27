package com.techm.po.model.bo;

public class Amounts {
	
	private int Qty;
	private float Entry;
	private float Usd;	
	
	public Amounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getQty() {
		return Qty;
	}
	public void setQty(int qty) {
		Qty = qty;
	}
	public float getEntry() {
		return Entry;
	}
	public void setEntry(float entry) {
		Entry = entry;
	}
	public float getUsd() {
		return Usd;
	}
	public void setUsd(float usd) {
		Usd = usd;
	}
	@Override
	public String toString() {
		return "Amounts [Qty=" + Qty + ", Entry=" + Entry + ", Usd=" + Usd + "]";
	}	
	
}
