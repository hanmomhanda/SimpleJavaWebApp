package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class SignUpServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    ServletContext context;

    ObjectMapper objectMapper;
    String userId = "abcde";
    String password = "qwert";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Set<User> userSet = new HashSet<User>();
        when(context.getAttribute("userSet")).thenReturn(userSet);
        objectMapper = new ObjectMapper();
        when(context.getAttribute("objectMapper")).thenReturn(objectMapper);

        userSet.add(new User(userId, password));
    }

    @Test
    public void testOk() throws Exception {
        when(request.getServletContext()).thenReturn(context);
        when(request.getParameter("userId")).thenReturn("otherId");
        when(request.getParameter("password")).thenReturn("otherPassword");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        new SignUpServlet().doPost(request, response);

        String result = sw.getBuffer().toString().trim();
        JsonNode node = objectMapper.readTree(result);

        assertEquals("success", node.get("type").textValue());
    }

    @Test
    public void testFail() throws Exception {
        when(request.getServletContext()).thenReturn(context);
        when(request.getParameter("userId")).thenReturn(userId);
        when(request.getParameter("password")).thenReturn(password);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        new SignUpServlet().doPost(request, response);

        String result = sw.getBuffer().toString().trim();
        JsonNode node = objectMapper.readTree(result);

        assertEquals("fail", node.get("type").textValue());
    }
}
