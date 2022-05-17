package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class FlowAndDate {
	
	LocalDate date;
	double flow;
	
	public FlowAndDate(LocalDate date, double flow) {
		super();
		this.date = date;
		this.flow = flow;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getFlow() {
		return flow;
	}
	public void setFlow(double flow) {
		this.flow = flow;
	}
	
	

}
