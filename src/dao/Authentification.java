package dao;

import java.sql.ResultSet;
import databaseconnection.GetConnection;
import bean.Administrateur;

public class Authentification implements IAuthentification {
	// check admin with the given login and psw
	//if exist return object of the corresponding administrateur 
	//else return null
	public Administrateur Login(String login , String password){
		try {
			GetConnection.connect();
			ResultSet rs = null;
			rs = GetConnection.Select("select * from administrateur where login='"+login+"' and password='"+password+"'");
			if(rs.next()) {
				Administrateur admin = new Administrateur(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("username"));
				return admin;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// just a test
	/*public static void main(String[] args) {
		System.out.println(new Authentification().Login("mehdi","password"));	
	}*/
}
