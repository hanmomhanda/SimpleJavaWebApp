package controller;

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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


public class UserStatsServletTest {

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

        new UserStatsServlet().doGet(request, response);

        String result = sw.getBuffer().toString().trim();

        assertTrue(result.contains("<h1>등록 사용자 수 : " + size + "</h1>"));
    }

    @Test
    public void testFail() throws Exception {
        when(request.getServletContext()).thenReturn(context);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        new UserStatsServlet().doGet(request, response);

        String result = sw.getBuffer().toString().trim();

        assertFalse(result.contains("<h1>등록 사용자 수 : " + (size - 1) + "</h1>"));
    }
}
