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

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
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
            result.setType("fail");
            result.setMsg("이미 등록되어 있는 아이디 입니다.");
        } else {
            userSet.add(user);
            result.setType("success");
            result.setMsg("등록되었습니다. 로그인 해주세요.");
        }

        ObjectMapper objectMapper = (ObjectMapper)req.getServletContext().getAttribute("objectMapper");
        resp.setContentType("application/json");
        resp.getWriter().println(objectMapper.writeValueAsString(result));
    }
}