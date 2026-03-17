package dao;

import connection.ConnectionFactory;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User logar(String email, String senha) {
        String sql = "SELECT nickname, email, senha FROM usuario WHERE email = ? AND senha = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nickname = rs.getString("nickname");
                    String userEmail = rs.getString("email");
                    String userSenha = rs.getString("senha");
                    return new User(nickname, userSenha, userEmail);
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao realizar login no banco.", e);
        }
    }

    public void salvar(User user) {
        String sql = "INSERT INTO usuario (nickname, email, senha) VALUES (?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getNickname());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getSenha());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário no banco.", e);
        }
    }
}