package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainJdbc {

	public static void main(String[] args) throws SQLException {

		String URL = "jdbc:h2:tcp://localhost/~/test";
		String USR = "sa";
		String PWD = "123";
		Connection connection = DriverManager.getConnection(URL, USR, PWD);
		Statement stmt = connection.createStatement();
		
//		 String sqlCreate = "CREATE TABLE Persona("
//		+ "ID BIGINT, "
//		+ "NOMBRE VARCHAR(20), "
//		+ "Apellido VARCHAR(20),"
//		+ "PRIMARY KEY (ID))";
		
		//Uso el try (with-resources0), que no tiene catch. Si algo falla cierra el buffer.
//		try(stmt){
//		stmt.execute(sqlCreate);
//		}
		
		
		
		/*
		//Eliminar una tabla
		String sqlDrop = "DROP TABLE Persona";
		//Eliminar con condicional (Si existe)
		String sqlDropConditional = "DROP TABLE IF EXISTS Persona";
		
		stmt.execute(sqlDrop);
		 */
		
//		String sqlInsert = "INSERT INTO Persona VALUES (1,'Pepa','Pepe')";
//		stmt.execute(sqlInsert);
//		
//		String sqlInsert1 = "INSERT INTO Persona VALUES (3,'Silvina','Gonn')";
//		stmt.execute(sqlInsert1);
//		
//		String sqlInsert2 = "INSERT INTO Persona VALUES (4,'Silvina','Kers')";
//		stmt.execute(sqlInsert2);
//		
//		String sqlUpdate = "UPDATE Persona SET Nombre = 'Diego'," +
//				"Apellido = 'Perez' WHERE Id = 1";
//		stmt.execute(sqlUpdate);
		
//		String sqlDelete = "DELETE FROM Persona WHERE Id = 1";
//		stmt.execute(sqlDelete);
		
		String selectq = "SELECT * FROM Persona";
		
		// La respuesta sql de un select de una tabla, es de tipo ResultSet.
		ResultSet result = stmt.executeQuery(selectq);		
		try(ResultSet resultado = stmt.executeQuery(selectq)){
		while(resultado.next())
		System.out.println(
//		resultado.getInt(1) + ": "
//		+ resultado.getString(2) + " "
//		+ resultado.getString(3));
//		Esto es lo mismo que llamarlo: 
				resultado.getInt("ID") + ": "
				+ resultado.getString("NOMBRE") + " "
				+ resultado.getString("Apellido"));
		}
		
		// Cuando tengo solo 1 respuesta
		String selectByName = "SELECT NOMBRE from Persona where ID = 1";
		try(ResultSet resultado1 = stmt.executeQuery(selectByName)){
		while(resultado1.next())
			System.out.println(resultado1.getString("NOMBRE"));
		}
		
		//Sentencias con variables. Se ejecuta la query con ?, despues se utiliza PreparedStatement. Y los seteo por separado.
		// Cuando escribo cada set, el primer parametro es el index segun la query. y el 2do param es el nuevo valor.
		String sql = "UPDATE PERSONA SET NOMBRE = ?, "
				+ "APELLIDO = ? WHERE ID = ?";
				try(PreparedStatement pstmt = connection.prepareStatement(sql)){
				pstmt.setString(1, "Diego");
				pstmt.setString(2, "Perez");
				pstmt.setInt(3, 3);
				pstmt.execute();
				}
				
		
				
	}

}
