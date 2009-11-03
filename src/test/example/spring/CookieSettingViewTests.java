package example.spring;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.View;

import javax.servlet.http.Cookie;
import java.util.Collections;
import java.util.Map;

public class CookieSettingViewTests {

    @Test
    public void shouldAddCookiesToResponse() throws Exception {
        View delegate = mock(View.class);

        Map<String, ?> model = Collections.emptyMap();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        CookieSettingView view = new CookieSettingView(delegate);
        view.addCookie("key1", "test1");
        view.addCookie("key2", "test2");

        view.render(model, request, response);

        Cookie cookie = response.getCookie("key1");
        assertThat(cookie, notNullValue());
        assertThat(cookie.getValue(), equalTo("test1"));

        cookie = response.getCookie("key2");
        assertThat(cookie, notNullValue());
        assertThat(cookie.getValue(), equalTo("test2"));

        verify(delegate).render(model, request, response);
    }
}
