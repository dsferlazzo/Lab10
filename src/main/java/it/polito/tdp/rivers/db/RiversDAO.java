package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Caratteristiche;
import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.FlowAndDate;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {
	/**
	 * metodo per estrapolare dal database tutti i fiumi
	 * @return
	 */
	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public Caratteristiche getCaratteristiche(String name){
		
		String sql = "SELECT MIN(f.day) as min, MAX(f.day) as max, COUNT(*) AS n, AVG(f.flow) AS fmed "
				+ "FROM river r, flow f "
				+ "WHERE r.name=? AND r.id=f.river "
				+ "GROUP BY r.name "
				+ "";
		
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, name);
			ResultSet res = st.executeQuery();
			
			res.next();
			Caratteristiche result = new Caratteristiche(res.getDate("min").toLocalDate(),
				res.getDate("max").toLocalDate(), res.getInt("n"), res.getDouble("fmed"));
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
	}
	/**
	 * dato un fiume, ritorna la lista di tutti valori di flow
	 * @param name
	 * @return
	 */
	public List<Double> getFlows(int id){
		
		String sql = "SELECT flow "
				+ "FROM flow f "
				+ "WHERE f.river = ? "
				+ "ORDER BY f.day";
		List<Double> result = new ArrayList<Double>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();
			
			
			while(res.next()) {
				//System.out.println(res.getDouble("flow"));	//DEBUGGING
				result.add(res.getDouble("flow"));
			}
			conn.close();
			//System.out.println(result.size());	//DEBUGGING
			return result;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
	}
	

}
