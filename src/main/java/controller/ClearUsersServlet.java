package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/clearUsers")
public class ClearUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<User> userSet = (Set<User>)req.getServletContext().getAttribute("userSet");
        userSet.clear();

        resp.getWriter().println("<h1>사용자를 모두 삭제했습니다.</h1>");
    }
}
