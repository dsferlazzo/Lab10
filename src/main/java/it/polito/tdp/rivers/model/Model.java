package it.polito.tdp.rivers.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao;
	Map<String, River> nameMap;		//INUTILE, MA POTREBBE WORKARE IN FUTURO
	
	/**
	 * Metodo per estrapolare i fiumi dal database
	 * @return
	 */
	public List<River> getRivers(){		//DA CONTROLLARE (POSSIBILE TOGLIERE IMPLEMENTAZIONE DELLA MAPPA)
		dao = new RiversDAO();
		nameMap = new HashMap<String, River>();
		List<River> rivers = dao.getAllRivers();
		for(River r : rivers)
			nameMap.put(r.getName(), r);
		return rivers;
		
	}
	/**
	 * metodo per estrapolare le caratteristiche di un fiume, per poi inserirle nell interfaccia grafica
	 * @param r
	 * @return
	 */
	public Caratteristiche getCaratteristiche(River r) {
		String name = r.getName();
		dao = new RiversDAO();
		return dao.getCaratteristiche(name);
	}
	
	public String svolgiSimulazione(double k, River r) {
		List<Double> flowVal = dao.getFlows(r.getId());
		String result = "";
		
		Simulatore s = new Simulatore(flowVal);
		s.init(k);
		s.run();
		
		double cMed = s.getcMed();
		int ggInsufficienti = s.getnInsufficiente();
		
		result += "Numero di giorni in cui non si è potuta effettuare l'erogazione minima: " +
				ggInsufficienti + "\nOccupazione media del bacino giornaliera: " + cMed;
		return result;
	}

}
