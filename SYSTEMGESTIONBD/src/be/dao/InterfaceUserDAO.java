package be.dao;

import java.time.LocalDate;
import java.util.List;

import be.abstractDao.InterfaceAbstractDao;
import be.model.User;

public interface InterfaceUserDAO extends InterfaceAbstractDao<User,Integer> {
	/*
	 * Obtenir la liste des noms utilisateurs par une date de naissance
	 * @param date de naissance
	 * @return liste des utilisateurs
	 */
	List<User> getUserFromBirthdate(LocalDate birthdate);
}
