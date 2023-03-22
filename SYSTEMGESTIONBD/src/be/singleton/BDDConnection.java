package be.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDDConnection {
		//Instanciation de l'URL de connexion
		private String url = "jdbc:mysql://localhost:8889/sgbd";

		// Instanciation du nom de l'utilisateur
		private String user = "root";

		// Instanciation du mot de passe de l'utilisateur
		private String password = "root";

		// Declaration de l'objet Connection
		private static Connection conn;
		
		// Declaration de l'objet Connection Singleton  (Limitation de ses effets de modification à la classe avec le mot clef static, Ouverture de la lecture de son état depuis plusieurs threads avec le mot clef volatile) 
		private static volatile BDDConnection single;

		/**
		 * Constructeur privé
		 */
		private BDDConnection() {
			try {
				// Instanciation de la classe com.mysql.jdbc.Driver (du Driver)
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// Ouverture de la connection à la base de donnée précisée
				conn = DriverManager.getConnection(url, user, password);
				
			} catch (SQLException | ClassNotFoundException e) {
				
				// Message d'erreur délivré lorsqu'une exception est interceptée 
				System.out.println("Erreur lors de l'initialisation de la connexion");
			}
		}

		/**
		 * Méthode permettant de retourner l'instance de la connexion (à la base de donnée).
		 * Et de la créer si elle n'existe pas.
		 */

		public static Connection getInstance() {
			if (single == null) {
				// on utilise le mot clef synchronized afin de permettre la modification de la Connexion déclarée statique (Une clée unique d'accès permet à chaque thread de modifier l'un après l'autre le bloc de code de la classe. Et c'est la classe qui donne et reprend cette clé après chaque interaction avec un thread) 
				synchronized (Connection.class) {
					if (single == null) {
						single = new BDDConnection();
					}
				}
			}
			return conn;
		}
}
