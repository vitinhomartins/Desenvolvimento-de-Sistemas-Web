package model;

public class User {
    private String email;
    private String nickname;
    private String senha;

    public User() {
    }

    public User(String nickname, String senha, String email) {
        this.email = email;
        this.senha = senha;
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}