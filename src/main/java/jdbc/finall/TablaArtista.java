package jdbc.finall;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class TablaArtista {

	private Connection con;
	private Statement stmt;

	public TablaArtista() throws SQLException {
		crearTabla();
	}

	public void crearTabla() throws SQLException {
		String URL = "jdbc:h2:tcp://localhost/~/test";
		String USR = "sa";
		String PWD = "123";

		con = DriverManager.getConnection(URL, USR, PWD);

		stmt = con.createStatement();

		eliminarTabla();

		String sqlCreate = "CREATE TABLE Artista(" + "ID BIGINT auto_increment, " + "Bio VARCHAR(2000), "
				+ "FechaNacimiento DATE, " + "Apellido VARCHAR(25), " + "Nombre VARCHAR(25), " + "Email VARCHAR(100), "
				+ "PRIMARY KEY (ID))";

		stmt.execute(sqlCreate);

	}

	public void eliminarTabla() throws SQLException {

		String sqlDrop = "DROP TABLE IF EXISTS Artista";
		stmt.execute(sqlDrop);
	}

	public long insertar(String bio, String fechaNacimiento, String apellido, String nombre, String email)
			throws SQLException {
		String sqlInsert = "INSERT INTO Artista(Bio, FechaNacimiento, Apellido, Nombre, Email)" + "VALUES(?,?,?,?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS)) {
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

	public void insertarLotes(String[] bios, String[] fechaNacimientos, String[] apellidos, String[] nombres,
			String[] emails) throws SQLException {

		for (int i = 0; i < nombres.length; i++) {
			try {
				insertar(bios[i], fechaNacimientos[i], apellidos[i], nombres[i], emails[i]);
				System.out.println(obtener(i));
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

	}

	public void actualizar(long id, String bio, String fechaNac, String apellido, String nombre, String mail)
			throws SQLException {

		String sqlUpdate = "UPDATE Artista SET Bio = ?, FechaNacimiento = ?,"
				+ " Apellido = ?, Nombre = ?, Email = ? WHERE ID = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sqlUpdate)) {

			pstmt.setString(1, bio);								
			pstmt.setDate(2, Date.valueOf(fechaNac));
			pstmt.setString(3, apellido);
			pstmt.setString(4, nombre);
			pstmt.setString(5, mail);
			pstmt.setLong(6, id);
			pstmt.execute();

		}
	}

	public String obtener(long id) throws SQLException {

		String salida;
		String sqlSelect = "SELECT * FROM Artista WHERE ID = " + id;
		try (ResultSet resultado = stmt.executeQuery(sqlSelect)) {
			resultado.next();
			salida = resultado.getInt(1) + ": (" + resultado.getString(2) + ") - " + resultado.getString(5) + " "
					+ resultado.getString(4) + " - " + resultado.getString(3) + " - " + resultado.getString(6);
		}
		return salida;
	}

	public void eliminar(long id) throws SQLException {

		String sqlDelete = "DELETE FROM Artista WHERE ID = " + id;
		try (PreparedStatement pstmt = con.prepareStatement(sqlDelete)) {
			pstmt.execute();
		}
	}

}
