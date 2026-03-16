package model;

public class User {
    private String senha, email, nickname;
    public User(String nickname, String senha, String email) {
        this.email = email;
        this.senha = senha;
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNickname() {
        return nickname;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
