package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjercicioArtista {

	public static void main(String[] args) throws SQLException {
		String URL = "jdbc:h2:tcp://localhost/~/test";
		String USR = "sa";
		String PWD = "123";
		Connection connection = DriverManager.getConnection(URL, USR, PWD);
		Statement stmt = connection.createStatement();

//		String sqlCreate = "CREATE TABLE Artista("
//				+ "ID BIGINT AUTO_INCREMENT, "
//				+ "Bio VARCHAR(200), "
//				+ "FechaNacimiento DATE,"
//				+ "Apellido VARCHAR(25),"
//				+ "Nombre VARCHAR(25),"
//				+ "Email VARCHAR(100),"
//				+ "PRIMARY KEY (ID))";
//		
//		
//		try(stmt){
//		stmt.execute(sqlCreate);
//		}

//		String sqlDrop = "DROP TABLE Artista";
//		stmt.execute(sqlDrop);

//		String sqlInsert = "INSERT INTO Artista "
//				+ "(Bio, FechaNacimiento, Apellido, Nombre, Email)"
//				+ "VALUES ('Bio de artista2', '1992-04-02', 'Perez', 'Diego', 'mail2@mail.com') ";
//		stmt.execute(sqlInsert);

//		String sqlDelete = "DELETE FROM Artista WHERE Id = 2";
//		stmt.execute(sqlDelete);

		// Imprimir en consola->
//		String selectq = "SELECT * FROM Artista WHERE ID = 1";
//		ResultSet result = stmt.executeQuery(selectq);
//		try (ResultSet resultado = stmt.executeQuery(selectq)) {
//			while (resultado.next())
//				System.out.println(resultado.getInt("ID") + ": " + resultado.getString("Bio") + " "
//						+ resultado.getDate("FechaNacimiento") + " " + resultado.getString("Apellido") + " "
//						+ resultado.getString("Nombre") + " " + resultado.getString("Email"));
//		}
		
		String sqlUpdate = "UPDATE Artista SET NOMBRE = ?, "
				+ "APELLIDO = ?, Email = ?, Bio = ? WHERE ID = ?";
				try(PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)){
				pstmt.setString(1, "Natalia");
				pstmt.setString(2, "Romeo");
				pstmt.setString(3, "Nati@email.com");
				pstmt.setString(4, "Contadora");
				pstmt.setInt(5, 1);
				pstmt.execute();
				}
				
				// Esto quedó inconcluso.
//				// prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				// ResultSet result = stmt.getGeneratedKeys();
				
	}
}
