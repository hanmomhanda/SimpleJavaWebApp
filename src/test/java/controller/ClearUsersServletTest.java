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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


public class ClearUsersServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    ServletContext context;

    int size = 50;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Set<User> userSet = new HashSet<User>();
        for (int i = 0 ; i < size ; i++) {
            userSet.add(new User("id" + i, "pwd" + i));
        }
        when(context.getAttribute("userSet")).thenReturn(userSet);
    }

    @Test
    public void testOk() throws Exception {
        when(request.getServletContext()).thenReturn(context);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        assertTrue(((Set) request.getServletContext().getAttribute("userSet")).size() == size);
        new ClearUsersServlet().doGet(request, response);
        assertTrue(((Set) request.getServletContext().getAttribute("userSet")).size() == 0);
    }
}
