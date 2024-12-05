package cat.iticbcn;

import java.sql.*;

import com.amazonaws.services.iot.client.AWSIotMessage;

import org.json.JSONObject; // Asegúrate de incluir esta librería en tu proyecto

public class AccesMethodsToDB {

    //Select assistència
    public void selectAssistencia (Connection con) {
        String sql = "SELECT * FROM assistencia"; // Consulta SQL
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                int id = rs.getInt("id");
                Date data = rs.getDate("data");
                String estat = rs.getString("estat");
                String modificat_per = rs.getString("modificat_per");
                System.out.println("ID: " + id + ", Data: " + data + ", Estat: " +  estat + ", Modificat per: " + modificat_per);
            }
        
        } catch (SQLException e) {
            System.out.println("Error al executar la consulta: " + e.getMessage());
        }
    }

    //Select assistir
    public void selectAssistir (Connection con) {
        String sql = "SELECT * FROM assistir"; // Consulta SQL
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                int id_usuari = rs.getInt("id_usuari");
                int id_assistencia = rs.getInt("id_assistencia");
                System.out.println("ID Usuari: " + id_usuari + ", ID Assitència: " + id_assistencia);
            }
        
        } catch (SQLException e) {
            System.out.println("Error al executar la consulta: " + e.getMessage());
        }
    }

    //Select autenticació
    public void selectAutenticacio (Connection con) {
        String sql = "SELECT * FROM autenticacio"; // Consulta SQL
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String contrasenya = rs.getString("contrasenya");
                System.out.println("ID: " + id + ", Nom: " + nom + ", Contrasenya: " + contrasenya);
            }
        
        } catch (SQLException e) {
            System.out.println("Error al executar la consulta: " + e.getMessage());
        }
    }

    //Select autenticar
    public void selectAutenticar (Connection con) {
        String sql = "SELECT * FROM autenticar"; // Consulta SQL
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                int id_usuari = rs.getInt("id_usuari");
                int id_autenticacio = rs.getInt("id_autenticacio");
                System.out.println("ID Usuari: " + id_usuari + ", ID Autenticació: " + id_autenticacio);
            }

        } catch (SQLException e) {
            System.out.println("Error al executar la consulta: " + e.getMessage());
        }
    }

    //Select espai
    public void selectEspai (Connection con) {
        String sql = "SELECT * FROM autenticacio"; // Consulta SQL
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String edifici = rs.getString("edifici");
                String pis = rs.getString("pis");
                System.out.println("ID: " + id + ", Nom: " + nom + ", Edifici: " + edifici + ", Pis: " + pis);
            }
        
        } catch (SQLException e) {
            System.out.println("Error al executar la consulta: " + e.getMessage());
        }
    }

    //Select lectura
    public void selectLectura (Connection con) {
        String sql = "SELECT * FROM lectura"; // Consulta SQL
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                int id = rs.getInt("id");
                Timestamp data_hora = rs.getTimestamp("data_hora");
                String uid = rs.getString("uid");
                System.out.println("ID: " + id + ", Data i hora: " + data_hora + ", UID: " + uid);
            }
        
        } catch (SQLException e) {
            System.out.println("Error al executar la consulta: " + e.getMessage());
        }
    }

    //Select registrar
    public void selectRegistrar (Connection con) {
        String sql = "SELECT * FROM registrar"; // Consulta SQL
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                int id_espai = rs.getInt("id_espai");
                int id_lectura = rs.getInt("id_lectura");
                System.out.println("ID Espai: " + id_espai + ", ID Lectura: " + id_lectura);
            }

        } catch (SQLException e) {
            System.out.println("Error al executar la consulta: " + e.getMessage());
        }
    }

    //Select tenir
    public void selectTenir (Connection con) {
        String sql = "SELECT * FROM tenir"; // Consulta SQL
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                int id_usuari = rs.getInt("id_usuari");
                int id_lectura = rs.getInt("id_lectura");
                System.out.println("ID Usuari: " + id_usuari + ", ID Lectura: " + id_lectura);
            }

        } catch (SQLException e) {
            System.out.println("Error al executar la consulta: " + e.getMessage());
        }
    }

    public void selectUsuari (Connection con) {
        String sql = "SELECT * FROM usuari"; // Consulta SQL
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String rol = rs.getString("rol");
            String correu = rs.getString("correu");
            String uid = rs.getString("uid");
            System.out.println("ID: " + id + ", Nom: " + nom + ", Rol: " + rol + ", Correu: " + correu + ", UID: " + uid);
        }
        
        } catch (SQLException e) {
            System.out.println("Error al executar la consulta: " + e.getMessage());
        }
    }

    public void insertRegistry(Connection con, AWSIotMessage message) {
        String payload = message.getStringPayload(); // Obtenir el payload del missatge
    
        try {
            // Parsear el payload JSON
            JSONObject jsonObject = new JSONObject(payload);
    
            // Suposant que el JSON té aquestes claus: id_espai, id_lectura
            int idEspai = jsonObject.getInt("id_espai");
            int idLectura = jsonObject.getInt("id_lectura");
    
            // Preparar la sentencia SQL per insertar en la tabla "registrar"
            String sql = "INSERT INTO registrar (id_espai, id_lectura) VALUES (?, ?)";
    
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, idEspai); // Asignar valores
                pstmt.setInt(2, idLectura);
    
                // Executar l'INSERT
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Dades inserides correctament a la taula 'registrar'.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al fer l'insert a la base de dades: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al processar el payload del missatge: " + e.getMessage());
        }
    }
    
}
