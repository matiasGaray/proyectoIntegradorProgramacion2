
package main;
import java.sql.*;


public class Inventory {
    
    public static void showInventory(){
        String tableName = "productos";
        try{
            DatabaseConnection dbConn = DatabaseConnection.getInstance();
            Connection conn = dbConn.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM " + tableName;
            ResultSet rs = stmt.executeQuery(sql);
            int columns = rs.getMetaData().getColumnCount();
            while(rs.next()){
                for (int i = 1; i <= columns; i++) {
                    System.out.print("\t" + rs.getString(i));
                }
                System.out.println();
            }
            
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            
        }
      
    }
    
    public static void showSomeProducts(String name){
        String tableName = "productos";
        String columName  = "nombre";
        String filterValue = "%"+name+"%";
        boolean coincidences = false;
        try{
            //Hago la conexion a la base de datos
            DatabaseConnection dbConn = DatabaseConnection.getInstance();
            Connection conn = dbConn.getConnection();
            //creo la consulta
            String sql = "SELECT * FROM " + tableName +" WHERE " + columName + " LIKE ?";
            //Uso preparedStatement para pasar el parametro a la consulta
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
             pstmt.setString(1,filterValue);
             //Ejecuto la consulta
            ResultSet rs = pstmt.executeQuery();
            int columns = rs.getMetaData().getColumnCount();
            //itero el resultado de la consulta
            while(rs.next()){
                coincidences = true;
                for (int i = 1; i <= columns; i++) {
                    System.out.print(  "\t"+rs.getObject(i) );
                }
                    System.out.println();
            }
            if (coincidences != true) System.out.println("No hay coincidencias");
            }
            
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            
        }
    }
    
}
