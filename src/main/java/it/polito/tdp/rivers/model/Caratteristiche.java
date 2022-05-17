package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Caratteristiche {
	
	LocalDate min;
	LocalDate max;
	int nMisurazioni;
	double avgFlusso;
	
	public Caratteristiche(LocalDate min, LocalDate max, int nMisurazioni, double avgFlusso) {
		super();
		this.min = min;
		this.max = max;
		this.nMisurazioni = nMisurazioni;
		this.avgFlusso = avgFlusso;
	}
	public LocalDate getMin() {
		return min;
	}
	public void setMin(LocalDate min) {
		this.min = min;
	}
	public LocalDate getMax() {
		return max;
	}
	public void setMax(LocalDate max) {
		this.max = max;
	}
	public int getnMisurazioni() {
		return nMisurazioni;
	}
	public void setnMisurazioni(int nMisurazioni) {
		this.nMisurazioni = nMisurazioni;
	}
	public double getAvgFlusso() {
		return avgFlusso;
	}
	public void setAvgFlusso(double avgFlusso) {
		this.avgFlusso = avgFlusso;
	}
	
	

}
