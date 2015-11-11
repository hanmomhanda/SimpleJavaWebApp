package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Result;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = new Result();
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = new User(userId, password);

        Set<User> userSet = (Set<User>)req.getServletContext().getAttribute("userSet");
        if (userSet.contains(user)) {
            result.setType("success");
            result.setMsg(userId + " 님 환영합니다.");
        } else {
            result.setType("fail");
            result.setMsg("등록되지 않은 사용자입니다. 먼저 등록해주세요.");
        }

        ObjectMapper objectMapper = (ObjectMapper)req.getServletContext().getAttribute("objectMapper");

        resp.getWriter().println(objectMapper.writeValueAsString(result));
    }
}