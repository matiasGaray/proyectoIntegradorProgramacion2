
package main;
import java.sql.*;

public class DatabaseConnection {
    //unica instancia de la clase
    private static DatabaseConnection instance;
    
    //variable donde se almacena la base de datos
    private Connection connection;
    //url de la base de datos
    private String url;
    
    //constructor privado para que solo exista una instancia
    private DatabaseConnection() throws SQLException {
        this.url = "jdbc:sqlite:src/main/db/basedatos.db";
        try{
            //Establece la conexion
            this.connection = DriverManager.getConnection(url);
        } catch(SQLException ex) {
            throw new SQLException("Error al conectar a la base de datos: "+ ex.getMessage());
        }
    }
    
    public static DatabaseConnection getInstance() throws SQLException {
        //Si la instancia no existe la cre
        if (instance == null){
            instance = new DatabaseConnection();
        }
        //si se cerro la conexion, crea una nueva instancia
        else if (instance.getConnection().isClosed()){
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    //obtener la conexion
    public Connection getConnection(){
        return connection;
    }
}
