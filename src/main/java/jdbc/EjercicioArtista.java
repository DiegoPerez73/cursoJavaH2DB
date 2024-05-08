package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class EjercicioArtista {

	public long insertar(Connection connection, String bio, String fechaNacimiento, String apellido, String nombre,
			String email) throws SQLException {
		String sql = "INSERT INTO Artista(Bio, FechaNacimiento, Apellido, Nombre, Email)" + "VALUES(?,?,?,?,?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, bio);
			pstmt.setDate(2, Date.valueOf(fechaNacimiento));
			pstmt.setString(3, apellido);
			pstmt.setString(4, nombre);
			pstmt.setString(5, email);
			pstmt.executeUpdate();

			try (ResultSet setClave = pstmt.getGeneratedKeys()) { // permite obtener la clave.
				setClave.next();
				return setClave.getLong(1);
			}
		}
	}

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
		String selectq = "SELECT * FROM Artista WHERE ID = 1";

		try (ResultSet resultado = stmt.executeQuery(selectq)) {
			while (resultado.next())
				System.out.println(resultado.getInt("ID") + ": " + resultado.getString("Bio") + " "
						+ resultado.getDate("FechaNacimiento") + " " + resultado.getString("Apellido") + " "
						+ resultado.getString("Nombre") + " " + resultado.getString("Email"));

			String sqlUpdate = "UPDATE Artista SET NOMBRE = ?, " + "APELLIDO = ?, Email = ?, Bio = ? WHERE ID = ?";
			try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
				pstmt.setString(1, "Natalia");
				pstmt.setString(2, "Romeo");
				pstmt.setString(3, "Nati@email.com");
				pstmt.setString(4, "Contadora");
				pstmt.setInt(5, 1);
				pstmt.execute();
			}

		}

//		String sql = "INSERT INTO Artista(Bio, FechaNacimiento, Apellido, Nombre, Email)"
//				+ "VALUES(?,?,?,?,?)";
//		try (PreparedStatement pstmt = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
//			pstmt.setString(1, "test");
//			pstmt.setDate(2, Date.valueOf("1990-04-05"));		
//			pstmt.setString(3, "testApellido2");
//			pstmt.setString(4, "testNombre2");
//			pstmt.setString(5, "testEmail2");
//			pstmt.executeUpdate();
//			
//			try(ResultSet getClave = pstmt.getGeneratedKeys()){ //permite obtener la clave.
//				getClave.next();
//				System.out.println(getClave.getLong(1));
//			}
//		}

		EjercicioArtista ejercicioArtista = new EjercicioArtista();
		long idNuevo = ejercicioArtista.insertar(connection, "bioTest", "1990-04-05", "apellidoTest", "nombreTest",
				"emailTest");
		System.out.println(idNuevo);

		connection.close();
		stmt.close();

	}
}
