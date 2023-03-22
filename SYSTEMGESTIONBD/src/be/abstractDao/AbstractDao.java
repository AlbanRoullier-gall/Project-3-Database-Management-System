package be.abstractDao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<A,B> implements InterfaceAbstractDao<A,B>{
	
	// Un membre marqué dans une classe comme étant protégé (mot clé protected) peut être manipulé :

	//Dans la classe qui définit ce membre,
	//Dans les classes qui dérivent de la classe considérée,
	//Et dans toutes les classes (et les types) définies dans le même package que celle qui définit le membre protégé.
	
	protected Connection conn;
	
	public AbstractDao(Connection conn) {
		this.conn =conn;
	}
	
	/* Méthode de recherche de l'objet
	 * 
	 * @param id L'id de l'objet
	 * 
	 * @return l'objet créé et ses dépendances éventuelles
	 */
	public abstract Optional<A> getFromID(B id);
	
	/* Méthode de liste des objets d'une table
	 * 
	 * @param Expression régulière à appliquer sur l'ID, si le string null: pas de condition.
	 * 
	 * @return une liste avec les objets
	 */
	public abstract List<A> getListe(String regExpr);
	
	/* Méthode de création de l'objet
	 * 
	 * @param objet
	 * 
	 * @return L'objet créé avec les éventuels valeurs générées par la Base de Données 
	 */
	public abstract boolean insert(A objet) throws Exception;
	
	/* Méthode de suppression de l'objet
	 * 
	 * @param objet
	 * 
	 * @return true si la suppression est executée
	 */
	public abstract boolean delete(A object) throws Exception;
	
	/* Méthode de mise à jour de l'objet
	 * 
	 * @param objet à mettre à jour
	 * 
	 * @return true si la mise à jour est executée
	 */
	public abstract boolean update(A object) throws Exception;
	
	/* Méthode de compte du nombre d'occurences d'un objet
	 * 
	 * @return nombre de lignes
	 */
	public abstract int count();
	
	/* Méthode de liste des objets d'une table
	 * 
	 * 
	 * @return une liste avec les objets
	 */
	public abstract List<A> getListe();

}
