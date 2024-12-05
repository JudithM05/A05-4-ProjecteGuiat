package cat.iticbcn;

import java.sql.*;

public class ClientIoT {

    // Configuración de la base de datos
    static final String url = "jdbc:postgresql://192.168.54.10/proyecto"; 
    static final String usuario = "programador1"; 
    static final String contrasena = "postgres"; 

    public static void main(String[] args) {

        DispositiuIot disp = new DispositiuIot(); // Asegúrate de que este sea un objeto válido

        try {
            // Conectar con el dispositivo IoT
            disp.conecta();
            disp.subscriu();

            // Establecer conexión a la base de datos
            try (Connection con = ConnectDB.getConnection(url, usuario, contrasena)) {
                System.out.println("Conexión establecida correctamente.");

                // Crear instancia de la clase que maneja consultas
                AccesMethodsToDB access = new AccesMethodsToDB();

                // Llamar a todos los métodos SELECT
                ejecutarTodosLosSelects(access, con);

            } catch (SQLException e) {
                System.out.println("Error de conexión a la base de datos: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error IoT: " + e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    // Método para llamar a todos los SELECT
    private static void ejecutarTodosLosSelects(AccesMethodsToDB access, Connection con) {
        System.out.println("\nEjecutando métodos SELECT...");
        access.selectAssistencia(con);
        access.selectAssistir(con);
        access.selectAutenticacio(con);
        access.selectAutenticar(con);
        access.selectEspai(con);
        access.selectLectura(con);
        access.selectRegistrar(con);
        access.selectTenir(con);
        access.selectUsuari(con);
        System.out.println("\nTodos los métodos SELECT se ejecutaron correctamente.");
    }
}
