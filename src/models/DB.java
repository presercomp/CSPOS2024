package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306/pos_db";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection conn = null;
    
    public DB(){
        //Lo dejamos en blanco
    }
    
    public static Connection getConnection(){
        if(conn == null){
           try{
               Class.forName("com.mysql.cj.jdbc.Driver");
               conn = DriverManager.getConnection(URL, USER, PASS);
           } catch(ClassNotFoundException x_X){
               System.out.println("Error: Driver no encontrado");
               System.out.println(x_X);
           } catch(SQLException x_X){
               System.out.println("Error al conectar a la base de datos");
               System.out.println(x_X);
           }
        }
        return conn;
    }
}
