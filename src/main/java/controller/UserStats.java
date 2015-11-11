package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/userStats")
public class UserStats extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<User> userSet = (Set<User>)req.getServletContext().getAttribute("userSet");
        int countOfUser = userSet.size();

        resp.getWriter().println("<h1>등록 사용자 수 : " + countOfUser + "</h1>");
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (User user : userSet) {
            sb.append("<h2>" + (i++) + " - " + user.getUserId() + "</h2>\n");
        }
        resp.getWriter().println(sb.toString());
        resp.getWriter().println("<h1>등록 사용자 수 : " + countOfUser + "</h1>");
    }
}
