
package main;

import java.sql.*;

public class Employee extends Person{
    private int idEmployee;
    Employee(String aName, char aGender, String aEmail, int aDni, int aIdEmployee){
        super(aName,aGender,aEmail,aDni);
        idEmployee = aIdEmployee;
    }
    //setters
    public void setId(int id){
        this.idEmployee = id;
    }
    //getters
    public int getId(){
        return idEmployee;
    }
    
    public static void createEmployee(String name, String gender,String email,int dni,int idEmployee){
         try {
            // Obtener la instancia única de la conexión a la base de datos
            DatabaseConnection dbConn = DatabaseConnection.getInstance();
            Connection conn = dbConn.getConnection();

            if (conn != null) {
                // Crear una tabla si no existe
                Statement stmt = conn.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS empleados ("
                        + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + " nombre TEXT NOT NULL,"
                        + " genero TEXT NOT NULL,"
                        + " email TEXT NOT NULL,"
                        + " dni INTEGER NOT NULL,"
                        + " id_empleado INTEGER NOT NULL"
                        + ");";
                stmt.execute(sql);

                // Usar PreparedStatement para insertar valores
            String insertSQL = "INSERT INTO empleados (nombre, genero, email, dni, id_empleado) VALUES (?, ?, ?, ?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            // Asignar los parámetros de la función a la consulta SQL
            pstmt.setString(1, name);  
            pstmt.setString(2, gender); 
            pstmt.setString(3, email); 
            pstmt.setInt(4, dni);
            pstmt.setInt(5, idEmployee);

            // Ejecuta la consulta 
            pstmt.executeUpdate();
            System.out.println("Empleado creado correctamente.");
        }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
