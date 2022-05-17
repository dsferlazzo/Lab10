package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Simulatore {
	
	//CODA DEGLI EVENTI
	
	private PriorityQueue<Double> coda;	//L'EVENTO E DATO DAL FLOW IN DE TB
	
	//PARAMETRI DELLA SIMULAZIONE, DA IMPOSTARE ALL'INIZIO(NEL METODO INIT())
	
	double fMed;	//NON NECESSARIO, OTTENIBILE DALLE CARATTERISTICHE DEL FIUME
	double Q;		//CAPIENZA TOTALE DEL BACINO
	double C;		//OCCUPAZIONE INIZIALE DEL BACINO, =Q/2, NON NECESSARIO
	double fOut;	//MINIMO VALORE DI OUT, =0.8*fMed
	
	//OUTPUT DELLA SIMULAZIONE
	
	int nInsufficiente;
	List<Double> listC;
	double cMed;	//DA FARE COME AVERAGE DELLA LISTA
	
	//STATO DEL MONDO, CHE CAMBIA AD OGNI EVENTO
	
	
	public Simulatore(List<Double> eventi) {
		//INIZIALIZZO LA CODA DEGLI EVENTI
		coda = new PriorityQueue<Double>(eventi);
		System.out.println("Dimensione priorityqueue: " + coda.size());		//DEBUGGING
		
		//INIZIALIZZO I PARAMETRI DI OUTPUT DELLA SIMULAZIONE
		listC = new ArrayList<Double>();
		nInsufficiente=0;
		
		//INIZIALIZZO FMED
		double somma=0;
		for(Double d : eventi)
			somma+=d;
		fMed = somma/eventi.size();
		
		
	}
	
	public void init(double k) {
		Q= fMed*k*30*24*60*60;
		C=Q/2;
		fOut = 0.8*fMed*24*60*60;
	}
	
	public void run() {
		while(!this.coda.isEmpty()) {
			Double fIn = this.coda.poll();
			//System.out.println(fIn);		//DEBUGGING
			processEvent(fIn);	
		}
		double somma = 0;
		for(Double d : listC)
			somma+=d;
		cMed = somma/listC.size();
	}

	private void processEvent(Double fIn) {	//TODO
		
		//DEVO PRIMA CALCOLARE FOUT
		double fOutInterno;
		double fInInterno;
		double d = Math.random();
		if(d<=0.05) {
			fOutInterno=10*fOut;
		}
		else fOutInterno = fOut;
		fInInterno = fIn*24*60*60;
		
		C+= fInInterno-fOutInterno;
		if(C>Q)
			C=Q;	//TRACIMAZIONE
		if(C<0) {
			C=0;	//NEL CASO C NON SIA SUFFICIENTE, => NINSUFFICIENTE++
			nInsufficiente++;
		}
		System.out.println("Valore del TB: " + C);
		listC.add(C);
		
	}

	public int getnInsufficiente() {
		return nInsufficiente;
	}

	public double getcMed() {
		return cMed;
	}

	

}
