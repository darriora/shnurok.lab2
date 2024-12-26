package ru.ssau.tk.shnurok.lab2.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import ru.ssau.tk.shnurok.lab2.functions.factory.ArrayTabulatedFunctionFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class LoginSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();

        // Инициализируем фабрику по умолчанию
        if (session.getAttribute("FACTORY_KEY") == null) {
            session.setAttribute("FACTORY_KEY", new ArrayTabulatedFunctionFactory());
        }

        // Продолжаем обработку после успешного логина
        response.sendRedirect("/");
    }
}
