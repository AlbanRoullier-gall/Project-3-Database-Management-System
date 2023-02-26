package be.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDDConnection {
	// URL de connexion
		private String url = "http://localhost:8888/phpMyAdmin5/";

		// Nom du user
		private String user = "root@localhost";

		// Mot de passe de l'utilisateur
		private String password = "";

		// Objet connection
		private static Connection conn;

		private volatile static BDDConnection single;

		/**
		 * Constructeur privé
		 */
		private BDDConnection() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Erreur lors de l'initialisation de la connexion");
			}
		}

		/**
		 * Méthode permettant de retourner l'instance de la connexion à base de donnée
		 * et de la créer si elle n'existe pas
		 */

		public static Connection getInstance() {
			if (single == null) {
				synchronized (Connection.class) {
					if (single == null) {
						single = new BDDConnection();
					}
				}
			}
			return conn;
		}
}
