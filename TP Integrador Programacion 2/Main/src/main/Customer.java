
package main;
import java.sql.*;

public class Customer extends Person{
    private int idCustomer;
    Customer(String aName, char aGender, String aEmail, int aDni, int aIdCustomer){
        super(aName, aGender,aEmail,aDni);
        idCustomer = aIdCustomer;
    }
    
    //setters
    public void setId(int id){
        this.idCustomer = id;
    }
    
    //getters
    public int getId(){
        return idCustomer;
    }
    
    public static void createCustomer(String name, String gender, String email,int dni){
        try {
            // Obtener la instancia única de la conexión a la base de datos
            DatabaseConnection dbConn = DatabaseConnection.getInstance();
            Connection conn = dbConn.getConnection();

            if (conn != null) {
                // Crear una tabla si no existe
                Statement stmt = conn.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS clientes ("
                        + " id_cliente INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + " nombre TEXT NOT NULL,"
                        + " genero TEXT NOT NULL,"
                        + " email TEXT NOT NULL,"
                        + " dni INTEGER"
                        + ");";
                stmt.execute(sql);

                // Usar PreparedStatement para insertar valores
            String insertSQL = "INSERT INTO clientes (nombre, genero, email, dni) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            // Asignar los parámetros de la función a la consulta SQL
            pstmt.setString(1, name);  
            pstmt.setString(2, gender); 
            pstmt.setString(3, email); 
            pstmt.setInt(4, dni);

            // Ejecuta la consulta 
            pstmt.executeUpdate();
            System.out.println("Cliente creado correctamente.");
        }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
