
package main;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Product {
    private String name;
    private String type;
    private float price;
    private int stock;
    private int idProduct;
    Product(String aName, String aType, float aPrice, int aStock, int aIdProduct){
        name  = aName;
        type = aType;
        price = aPrice;
        stock = aStock;
        idProduct = aIdProduct;
    }
    //setters
    public void setName(String name){
           this.name = name;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setPrice(float price){
        this.price = price;
    }
    public void setStock(int stock){
        this.stock = stock;
    }
    public void setIdProduct(int id){
        this.idProduct = id;
    }    
    
    //getters
    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public float getPrice(){
        return price;
    }
    public int getStock(){
        return stock;
    }
    public int getIdProduct(){
        return idProduct;
    }
    
    public static void createProduct(String name, String type, float price, int stock, int idProduct){
         try {
            // Obtener la instancia única de la conexión a la base de datos
            DatabaseConnection dbConn = DatabaseConnection.getInstance();
            Connection conn = dbConn.getConnection();

            if (conn != null) {
                // Crear una tabla si no existe
                Statement stmt = conn.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS productos ("
                        + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + " nombre TEXT NOT NULL,"
                        + " tipo TEXT NOT NULL,"
                        + " precio FLOAT NOT NULL,"
                        + " stock INTEGER NOT NULL,"
                        + " id_producto INTEGER NOT NULL"
                        + ");";
                stmt.execute(sql);

                // Usar PreparedStatement para insertar valores
            String insertSQL = "INSERT INTO productos (nombre, tipo, precio, stock, id_producto) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            // Asignar los parámetros de la función a la consulta SQL
            pstmt.setString(1, name);  
            pstmt.setString(2, type); 
            pstmt.setFloat(3, price); 
            pstmt.setInt(4, stock);
            pstmt.setInt(5, idProduct);

            // Ejecuta la consulta 
            pstmt.executeUpdate();
            System.out.println("producto insertado correctamente.");
        }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void deleteProduct(int id){
        try {
            //conexon a la DB
            DatabaseConnection dbConn = DatabaseConnection.getInstance();
            Connection conn = dbConn.getConnection();
             
            //consulta SQL
            String slq = "DELETE FROM productos WHERE id_producto = ?";
            try(PreparedStatement pstmt = conn.prepareStatement(slq)){
               //agrego el parametro
                pstmt.setInt(1, id);
                //ejecuto la consulta
                int deleted = pstmt.executeUpdate();
                if (deleted > 0) System.out.println("Producto eliminado correctamente");
                else System.out.println("No se encontró el producto");
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    
    public static void updateProduct(int id, String newName, String newType, float newPrice, int newStock){
        try{
            //conexion a la DB
            DatabaseConnection dbConn = DatabaseConnection.getInstance();
            Connection conn = dbConn.getConnection();
            //consulta SQL
            String sql = "UPDATE productos SET nombre = ?, tipo = ?, precio = ?, stock = ? WHERE id_producto = ?" ;
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                //agrego los parametros
                pstmt.setString(1,newName);
                pstmt.setString(2,newType);
                pstmt.setFloat(3, newPrice);
                pstmt.setInt(4, newStock);
                pstmt.setInt(5,id);
                
                //ejecuto la consulta
                int updated = pstmt.executeUpdate();
                if (updated > 0) System.out.println("Cambios realizados");
                else System.out.println("No se encontró el producto");
            }
            
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void addStock(int id, int increment){
        try{
            DatabaseConnection dbConn = DatabaseConnection.getInstance();
            Connection conn = dbConn.getConnection();
            
            String sql = "SELECT stock FROM productos WHERE id_producto = ?";
            String sql2 = "UPDATE productos SET stock = ? WHERE id_producto = ?";
            
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                int currentStock = Integer.parseInt(rs.getString(1));
                int newStock = currentStock + increment;
                try(PreparedStatement pstmt2 = conn.prepareStatement(sql2)){
                    pstmt2.setInt(1, newStock);
                    pstmt2.setInt(2, id);
                    int updated = pstmt2.executeUpdate();
                    if (updated > 0) System.out.println("Se ha actualizado el stock");
                    else System.out.println("No se encontro el producto");
                }
                
            }
            
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void subStock(int id, int decrement){
        try{
            DatabaseConnection dbConn = DatabaseConnection.getInstance();
            Connection conn = dbConn.getConnection();
            
            String sql = "SELECT stock FROM productos WHERE id_producto = ?";
            String sql2 = "UPDATE productos SET stock = ? WHERE id_producto = ?";
            
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                int currentStock = Integer.parseInt(rs.getString(1));
                int newStock = currentStock - decrement;
                try(PreparedStatement pstmt2 = conn.prepareStatement(sql2)){
                    pstmt2.setInt(1, newStock);
                    pstmt2.setInt(2, id);
                    int updated = pstmt2.executeUpdate();
                    if (updated > 0) System.out.println("Se ha actualizado el stock");
                    else System.out.println("No se encontro el producto");
                }
                
            }
            
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
