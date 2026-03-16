package dao;

import connection.StatusConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public void salvar(User user) {
        try {
            Connection cn = StatusConnection.conect();
            String sql = "INSERT INTO usuario(email, nome, senha) VALUES (?,?,?)";
            PreparedStatement stmt = cn.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getNickname());
            stmt.setString(3, user.getSenha());
            stmt.executeUpdate();
            cn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public User logar(String email, String senha){
        User usuario = null;
        try{

            Connection cn = StatusConnection.conect();
            String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

            PreparedStatement stmt = cn.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                usuario = new User(null, null, null);
                usuario.setNickname(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }

            cn.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return usuario;
    }
}
