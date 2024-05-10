package jdbc.finall;

import java.sql.SQLException;

public class mainApp {

	public static void main(String[] args) throws SQLException {
		
		// Constructor (Borra la tabla si existe, y la crea)
		TablaArtista tablaArtista = new TablaArtista();

		//Insert
		tablaArtista.insertar("prueba", "1995-02-02", "Perez", "Diego", "diego@mail.com");

		// Insert por lotes.
		String[] bios = new String[] { "Contadora", "Cheff", "Programador", "Costurero", "Carpintero" };
		String[] fechaNacimientos = new String[] { "1995-05-05", "1995-05-05", "1995-05-05", "1995-05-05",
				"1995-05-05" };
		String[] nombres = new String[] { "Victoria", "Ariel", "Sofia", "Elena", "Victor" };
		String[] apellidos = new String[] { "Don", "Donatti", "Costa", "Costa", "Sala" };
		String[] emails = new String[] { "test@mail.com", "test@mail.com", "test@mail.com", "test@mail.com",
				"test@mail.com" };

		tablaArtista.insertarLotes(bios, fechaNacimientos, nombres, apellidos, emails);

		tablaArtista.actualizar(2, "Ajedrecista", "1985-06-01", "Carlsen", "Magnus", "magnus@email.com");
	}

}
