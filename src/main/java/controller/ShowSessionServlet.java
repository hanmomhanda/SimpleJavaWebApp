package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/showSession")
public class ShowSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        StringBuilder sb = new StringBuilder();
        final Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attrName = attributeNames.nextElement();
            sb.append(attrName).append(" : ").append(session.getAttribute(attrName)).append("\n");
        }
        resp.getWriter().println("<h1>Session Attributes</h1>");
        resp.getWriter().println("<hr/>");
        resp.getWriter().println("<h4>" + sb.toString() + "</h4>");
    }
}
