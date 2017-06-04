package org.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.example.model.beans.Administrator;

public class Count {
	
	private static final Logger log = LogManager.getLogger("Count: ");
	
	private Connection con;
	
	public Count(Connection con){
		this.con=con;
	}
	
	public boolean login(String mail, String password){
		String sql="select count(id) as count from administrator where mail=? and password= ?";
		int count=0;
		
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1, mail);
			st.setString(2, password);
			
			ResultSet rs=st.executeQuery();
			
			if(rs.next()){
				count=rs.getInt("count");
			}
			
			rs.close();
			
		} catch (SQLException e) {
			log.error("Try login: "+e.getMessage());
			return false;
		}
		
		return (count>0);
	}
	
	public List<Administrator> listAdministrators(){
		String sql="select * from administrator";
		
		List<Administrator> admins=new ArrayList<>();
		
		try{
			PreparedStatement st=con.prepareStatement(sql);
			
			ResultSet rs=st.executeQuery();
			
			while(rs.next()){
				admins.add(
						new Administrator(
								rs.getString("mail"), 
								rs.getString("password"), 
								rs.getString("name"), 
								rs.getString("state"), 
								rs.getInt("id_question")
						)
				);
			}
			
			rs.close();
			
		}
		catch(SQLException e){
			admins.clear();
			log.error("Error to list administrators");
		}
		
		return admins;
	} 

}
