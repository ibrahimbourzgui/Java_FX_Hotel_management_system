package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseconnection.GetConnection;
import bean.Client;

public class GestionClient implements IGestionClient {
	/* Get Functions start*/
	@Override
	public ArrayList<Client> GetAllClients() {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * from client");
		ArrayList<Client> ClientList = new ArrayList<>();
		try{
			while(rs.next()) {
				ClientList.add(new Client(rs.getInt("id"),rs.getString("nom"),
						rs.getString("prenom"),rs.getString("telephone"),rs.getString("cin")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		if(ClientList.size()>0)
			return ClientList;
		else 
			return null;
	}
	public ArrayList<String> GetAllCin() {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select cin from client");
		ArrayList<String> ClientList = new ArrayList<>();
		try{
			while(rs.next()) {
				ClientList.add(rs.getString("cin"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		if(ClientList.size()>0)
			return ClientList;
		else 
			return null;
	}

	@Override
	public Client GetClientById(int id) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * from client where id = '"+id+"'");
		Client c = null;
		try{
			while(rs.next()) {
					c = new Client(rs.getInt("id"),rs.getString("nom"),
							rs.getString("prenom"),rs.getString("telephone"),rs.getString("cin"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		return c;
	}

	@Override
	public Client GetClientByCin(String cin) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * from client where cin = '"+cin+"'");
		Client c = null;
		try{
			while(rs.next()) {
					c = new Client(rs.getInt("id"),rs.getString("nom"),
							rs.getString("prenom"),rs.getString("telephone"),rs.getString("cin"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		return c;
	}

	@Override
	public ArrayList<Client> GetClientByNom(String nom) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * from client where nom like '%"+nom+"%'");
		ArrayList<Client> ClientList = new ArrayList<>();
		try{
			while(rs.next()) {
				ClientList.add(new Client(rs.getInt("id"),rs.getString("nom"),
							rs.getString("prenom"),rs.getString("telephone"),rs.getString("cin")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		if(ClientList.size()>0)
			return ClientList;
		else 
			return null;
	}

	@Override
	public ArrayList<Client> GetClientByTelephone(String telephone) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * from client where telephone like '%"+telephone+"%'");
		ArrayList<Client> ClientList = new ArrayList<>();
		try{
			while(rs.next()) {
				ClientList.add(new Client(rs.getInt("id"),rs.getString("nom"),
							rs.getString("prenom"),rs.getString("telephone"),rs.getString("cin")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		if(ClientList.size()>0)
			return ClientList;
		else 
			return null;
	}
	

	@Override
	public int GetNumberClient() {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select count(*) from client");
		int c = 0;
		try{
			while(rs.next()) {
					c = rs.getInt("count(*)");
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		return c;
	}
	/* Get Functions end*/
	/* Update Functions start*/

	@Override
	public Boolean UpdateNom(int id, String newnom) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update client set nom = '"+newnom+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean UpdatePrenom(int id, String newprenom) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update client set prenom = '"+newprenom+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean UpdateTelephone(int id, String newtelephone) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update client set telephone = '"+newtelephone+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean UpdateCin(int id, String newcin) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update client set cin = '"+newcin+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean UpdateAll(int id, String newnom, String newprenom, String newtelephone, String newcin) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update client set nom = '"+newnom+"' ,prenom = '"+newprenom+"' ,"
				+ " telephone = '"+newtelephone+"' ,cin = '"+newcin+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean UpdateAll(int id, Client newclient) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update client set nom = '"+newclient.getNom()+"' ,prenom = '"+newclient.getPrenom()+"' ,"
				+ " telephone = '"+newclient.getTelephone()+"' ,cin = '"+newclient.getCin()+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}
	/* Update Functions end */
	/* Add Functions start */

	@Override
	public Boolean AddClient(String newnom, String newprenom, String newtelephone, String newcin) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("insert into client(nom,prenom,telephone,cin) values('"+newnom+"','"+newprenom+"','"+newtelephone+"','"+newcin+"')");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean AddClient(Client newclient) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("insert into client(nom,prenom,telephone,cin) values('"+newclient.getNom()+"','"+newclient.getPrenom()+"',"
				+ "'"+newclient.getTelephone()+"','"+newclient.getCin()+"')");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}
	/* Add Functions end */
	/* Drop Functions start */

	@Override
	public Boolean DropClientById(int id) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("delete from client where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean DropClientByCin(String cin) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("delete from client where cin='"+cin+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}
	/* Drop Functions end */

	
	public static void main(String[] args) {
		/* Get Functions Tests */
		//System.out.println(new GestionClient().GetAllClients());
		//System.out.println(new GestionClient().GetClientById(5));
		//System.out.println(new GestionClient().GetClientByCin("9903"));
		//System.out.println(new GestionClient().GetClientByNom("osus"));
		//System.out.println(new GestionClient().GetClientByTelephone("53"));
		
		/* Update Functions Tests */
		//System.out.println(new GestionClient().UpdateCin(1,"cin2"));
		//System.out.println(new GestionClient().UpdateNom(1,"Nom2"));
		//System.out.println(new GestionClient().UpdatePrenom(1,"Prenom2"));
		//System.out.println(new GestionClient().UpdateTelephone(1,"Tele2"));
		//System.out.println(new GestionClient().UpdateAll(2,"Nom3","Prenom3","Tele3","Cin"));
		//Client c = new Client(0,"nom4", "prenom4", "telephone4", "cin4");
		//System.out.println(new GestionClient().UpdateAll(1, c));
		
		/* Add Functions Tests */
		//System.out.println(new GestionClient().AddClient("NewNom","NewPrenom","NewTele","NewCin"));
		//Client c = new Client(0,"NewNom2","NewPrenom2","NewTele2","NewCin2");
		//System.out.println(new GestionClient().AddClient(c));

		/* Drop Functions Tests */
		//System.out.println(new GestionClient().DropClientByCin("Cin"));
		//System.out.println(new GestionClient().DropClientById(1));
		//System.out.println(new GestionClient().GetAllCin());
		

	}
}
 