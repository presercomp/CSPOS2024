package models;
import controllers.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UsersDAO {
    public boolean create(Users u){
        String sql = "INSERT INTO users (nickname, secret) VALUES (?, ?)";
        try {
            Connection conn = DB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, u.getNickname());
            stmt.setString(2, u.getSecret());
            return stmt.executeUpdate() > 0;
        } catch(SQLException x_X){
            System.out.println("Error al ejecutar la insercion");
            System.out.println(x_X);
            return false;
        }
    } 
    
    public List<Users> read(){
        String sql = "SELECT * FROM users";
        List<Users> users = new ArrayList<>();
        try {
            Connection conn = DB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Users u = new Users(
                        rs.getInt("id"),
                        rs.getString("nickname"), 
                        rs.getString("secret"),
                        Boolean.parseBoolean(rs.getString("active"))
                );
                users.add(u);
            }        
        }catch(SQLException x_X){
            System.out.println("Error listando los usuarios");
            System.out.println(x_X);
        } 
        return users;        
    }
        
    public Users read(int id){
        String sql = "SELECT * FROM users";
        List<Users> users = new ArrayList<>();
        try {
            Connection conn = DB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Users(
                        rs.getInt("id"),
                        rs.getString("nickname"), 
                        rs.getString("secret"),
                        Boolean.parseBoolean(rs.getString("active"))
                );
            }    
        }catch(SQLException x_X){
            System.out.println("Error listando los usuarios");
            System.out.println(x_X);
        } 
        return null; 
    }
}
