package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Se alguém acessar /login no navegador, manda para a tela correta
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        UserDAO dao = new UserDAO();
        User usuario = dao.logar(email, senha);

        if (usuario != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("usuario", usuario);
            resp.sendRedirect(req.getContextPath() + "/home.jsp");
        } else {
            // Volta para o login com um "flag" de erro
            resp.sendRedirect(req.getContextPath() + "/login.jsp?erro=1");
        }
    }
}