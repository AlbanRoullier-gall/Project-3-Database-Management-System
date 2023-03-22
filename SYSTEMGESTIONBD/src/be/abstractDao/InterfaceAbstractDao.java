package be.abstractDao;

import java.util.List;
import java.util.Optional;

import be.model.Bill;

/*
 * @author Alban Roullier-gall
 *
 *
 * @param <A> type des objets DAO (exemple: Address,Bill,Category,...)
 * @param <B> type de l'id des objets (exemple: String,Date,int,...)
 */

public interface InterfaceAbstractDao<A,B>{
	
	/* Méthode de recherche de l'objet
	 * 
	 * @param id L'id de l'objet
	 * 
	 * @return l'objet créé et ses dépendances éventuelles
	 */
	
	default Optional<A> getFromID(B id)
	{
		throw new UnsupportedOperationException();
	}

	
	/* Méthode de liste des objets d'une table
	 * 
	 * @param Expression régulière à appliquer sur l'ID, si le string null: pas de condition.
	 * 
	 * @return une liste avec les objets
	 */
	default List<A> getListe(String regExpr)
	{
		throw new UnsupportedOperationException();
	}
	
	/* Méthode de création de l'objet
	 * 
	 * @param objet
	 * 
	 * @return L'objet créé avec les éventuels valeurs générées par la Base de Données 
	 */
	default boolean insert(A objet) throws Exception
	{
		throw new UnsupportedOperationException();
	}
	
	
	/* Méthode de suppression de l'objet
	 * 
	 * @param objet
	 * 
	 * @return true si la suppression est executée
	 */
	default boolean delete(A object) throws Exception
	{
		throw new UnsupportedOperationException();
	}
	
	
	/* Méthode de mise à jour de l'objet
	 * 
	 * @param objet à mettre à jour
	 * 
	 * @return true si la mise à jour est executée
	 */
	default boolean update(A object) throws Exception
	{
		throw new UnsupportedOperationException();
	}
	
	/* Méthode de compte du nombre d'occurences d'un objet
	 * 
	 * @return nombre de lignes
	 */
	default int count() {
		throw new UnsupportedOperationException();
	}

	/* Méthode d'occurences d'un objet
	 * 
	 * @return toutes les lignes
	 */
	default List<A> getListe()
	{
		throw new UnsupportedOperationException();
	}
}
