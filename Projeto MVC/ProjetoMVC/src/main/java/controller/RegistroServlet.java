package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Se alguém acessar /registro no navegador, manda para a tela correta
        resp.sendRedirect(req.getContextPath() + "/registro.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String nickname = req.getParameter("nickname");
        String senha = req.getParameter("senha");

        if (email == null || nickname == null || senha == null ||
                email.isBlank() || nickname.isBlank() || senha.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("text/plain; charset=UTF-8");
            resp.getWriter().println("Preencha email, nickname e senha.");
            return;
        }

        User user = new User(nickname, senha, email);
        UserDAO dao = new UserDAO();
        dao.salvar(user);

        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}