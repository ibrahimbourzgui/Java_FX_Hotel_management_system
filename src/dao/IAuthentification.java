package dao;

import bean.Administrateur;

public interface IAuthentification {

	public Administrateur Login(String login , String password);
}
