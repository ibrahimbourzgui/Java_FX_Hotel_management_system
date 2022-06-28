package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import databaseconnection.GetConnection;import javafx.util.converter.LocalDateTimeStringConverter;
import bean.Chambre;

public class GestionChambre implements IGestionChambre {

	// Get Functions Start 
	@Override
	public ArrayList<Chambre> GetAllChambres(){
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * from chambre");
		ArrayList<Chambre> ChambreList = new ArrayList<>();
		try{
			while(rs.next()) {
					ChambreList.add(new Chambre(rs.getInt("id"),rs.getInt("numero"),rs.getString("type"),
							rs.getInt("enservice"),rs.getDouble("prix"),rs.getInt("disponibilité")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		if(ChambreList.size()>0)
			return ChambreList;
		else 
			return null;
	}
	public ArrayList<Integer> GetAllUnusedChambresNum(LocalDate dateFin){
 		GetConnection.connect();
		ResultSet rs = null;
		ArrayList<Integer> ChambreList = new ArrayList<>();
		rs = GetConnection.Select("select numero from chambre where disponibilité > '0'  and numero not in (select numero_chambre from reservation) union"
				+ " select numero_chambre from reservation where date_fin <= '"+dateFin+"';");
		try{
			while(rs.next()) {
					ChambreList.add(rs.getInt("numero"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		if(ChambreList.size()>0)
			return ChambreList;
		else 
			return null;
	}
	@Override
	public Chambre GetChambreById(int id) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * from chambre where id = '"+id+"'");
		Chambre c = null;
		try{
			while(rs.next()) {
					c = new Chambre(rs.getInt("id"),rs.getInt("numero"),rs.getString("type"),
							rs.getInt("enservice"),rs.getDouble("prix"),rs.getInt("diponibilité"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		return c;
	}
	
	@Override
	public Double GetChambrePrixByNum(int num) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select prix from chambre where numero = '"+num+"'");
		Double c = 0.0;
		try{
			while(rs.next()) {
					c = rs.getDouble("prix");
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage()+"  GetChambrePrixByNum");
		}
		GetConnection.disconnect();
		System.out.println("L9it lprix : "+ c);
		return c;
	}

	@Override
	public Chambre GetChambreByNum(int num) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * from chambre where numero = '"+num+"'");
		Chambre c = null;
		try{
			while(rs.next()) {
					c = new Chambre(rs.getInt("id"),rs.getInt("numero"),rs.getString("type"),
							rs.getInt("enservice"),rs.getDouble("prix"),rs.getInt("diponibilité"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		return c;
	}

	@Override
	public ArrayList<Chambre> GetChambreByType(String type) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * from chambre where type like '%"+type+"%'");
		ArrayList<Chambre> ChambreList = new ArrayList<>();
		try{
			while(rs.next()) {
					ChambreList.add(new Chambre(rs.getInt("id"),rs.getInt("numero"),rs.getString("type"),
							rs.getInt("enservice"),rs.getDouble("prix"),rs.getInt("diponibilité")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		if(ChambreList.size()>0)
			return ChambreList;
		else 
			return null;
	}

	@Override
	public ArrayList<Chambre> GetChambreByPrix(double prix) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * from chambre where prix <= '"+prix+"'");
		ArrayList<Chambre> ChambreList = new ArrayList<>();
		try{
			while(rs.next()) {
					ChambreList.add(new Chambre(rs.getInt("id"),rs.getInt("numero"),rs.getString("type"),
							rs.getInt("enservice"),rs.getDouble("prix"),rs.getInt("diponibilité")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		if(ChambreList.size()>0)
			return ChambreList;
		else 
			return null;
	}
	
	
	@Override
	public int GetNumberChambre() {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select count(*) from chambre");
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
	// Get Functions end
	// Update Functions start

	@Override
	public Boolean UpdateNum(int id, int newnum) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update chambre set numero = '"+newnum+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean UpdateType(int id, String newtype) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update chambre set type = '"+newtype+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean UpdatePrix(int id, double newprix) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update chambre set prix = '"+newprix+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean UpdateEnService(int id, int newenservice) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update chambre set enservice = '"+newenservice+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean UpdateDisponibilite(int id, int newdisponibilite) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update chambre set disponibilité = '"+newdisponibilite+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean UpdateAll(int id, int newnum, String newtype, int newenservice, double newprix, int newdisponibilite) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update chambre set numero = '"+newnum+"', type = '"+newtype+"',"
				+ " prix = '"+newprix+"',enservice = '"+newenservice+"', disponibilité = '"+newdisponibilite+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;	
		}

	@Override
	public Boolean UpdateAll(int id, Chambre newchambre) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("Update chambre set numero = '"+newchambre.getNumero()+"', type = '"+newchambre.getType()+"',"
				+ " prix = '"+newchambre.getPrix()+"',enservice = '"+newchambre.getEnService()+"', "
				+ "disponibilité = '"+newchambre.getDisponibilite()+"' where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}
	// Update Functions end
		
	// Add Function start
	@Override
	public Boolean AddChambre(int newnum, String newtype, int newenservice, double newprix, int newdisponibilite) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("insert into chambre(numero,type,prix,enservice,disponibilité) values('"+newnum+"','"+newtype+"','"+newprix+"','"+newenservice+"','"+newdisponibilite+"')");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean AddChambre(Chambre newchambre) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("insert into chambre(numero,type,prix,enservice,disponibilité) values('"+newchambre.getNumero()+"','"+newchambre.getType()
		+"','"+newchambre.getPrix()+"','"+newchambre.getEnService()+"','"+newchambre.getDisponibilite()+"')");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}
	// Add Function end
	// Drop Functions start
	@Override
	public Boolean DropChambreById(int id) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("delete from chambre where id='"+id+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}

	@Override
	public Boolean DropChambreByNum(int Num) {
		GetConnection.connect();
		int status = 0;
		status = GetConnection.Maj("delete from chambre where numero='"+Num+"'");		
		GetConnection.disconnect();
		if(status == 0)
			return false;
		else 
			return true;
	}
	// Drop Functions ends
	// Used function
	
	/* Actualiser enservice 1 */ 
	public Boolean SetChambreOnServiceTrue(int numero) {
		GetConnection.connect();
		int num=0;
		try {
			num = GetConnection.Maj("update chambre set enservice = 1 where numero ='"+numero+"'");
		} 
		catch (Exception e) {
			return false;
		}
		GetConnection.disconnect();
		if(num > 0)
		return true;
		else return false;
	};
	/* Actualiser Prix Total */ 
	public Boolean SetChambreOnServiceFalse(int numero) {
		GetConnection.connect();
		int num=0;
		try {
			num = GetConnection.Maj("update chambre set enservice = 0 where numero ='"+numero+"'");
		} 
		catch (Exception e) {
			return false;
		}
		GetConnection.disconnect();
		if(num > 0)
		return true;
		else return false;
	};
	
	public static void main(String[] args) {
		/*Tests For Select Functions*/
		// test GetAll
		//for(Chambre c : new GestionChambre().GetAllChambres()) {
		//	System.out.println(c.toString());
		//}
		//System.out.println(new GestionChambre().GetAllUnusedChambresNum(LocalDate.parse("2021-12-24")));
		
		// test Get by num
		//System.out.println(new GestionChambre().GetChambreById(7));
		//System.out.println(new GestionChambre().GetChambreByNum(1));

		// test get by type(All chambre.type contains the input)  and prix(All chambre.prix <= prix)  
		//System.out.println(new GestionChambre().GetChambreByType("cliim"));
		//System.out.println(new GestionChambre().GetChambreByPrix(199));	
		
		/*Tests For Select Functions*/
		//System.out.println(new GestionChambre().UpdateNum(9,8));	
		//System.out.println(new GestionChambre().UpdateType(1,"ana type jdiid"));	
		//System.out.println(new GestionChambre().UpdatePrix(1,200.96));	
		//System.out.println(new GestionChambre().UpdateDisponibilite(2,1));
		//System.out.println(new GestionChambre().UpdateAll(1,5,"type jdiiid",7,200.01,7));	
		//Chambre c = new Chambre(0, 7, "ana Type mn Objet Chambre ", 1, 0.99,1);
		//System.out.println(new GestionChambre().UpdateAll(12,c));	
		
		/* Tests For Insert Functions*/
		//System.out.println(new GestionChambre().AddChambre(55, "ana Chambre Jdida", 5, 5.99,5));	
		//Chambre c = new Chambre(0, 66, "ana Chambre Jdida 2 ", 6, 6.99,6);
		//System.out.println(new GestionChambre().AddChambre(c));	

		/* Test Fro Drop functions*/
		//System.out.println(new GestionChambre().DropChambreById(2));
		//System.out.println(new GestionChambre().DropChambreByNum(66));
		/* Test for Used function*/
		//System.out.println(new GestionChambre().SetChambreOnServiceTrue(66));
		System.out.println(new GestionChambre().GetChambrePrixByNum(9));
	

	}
}
