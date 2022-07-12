//package ee.mihkel.webshop.configuration;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class TokeniLugeja extends BasicAuthenticationFilter {
//
//    public TokeniLugeja(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println(request.getMethod());
//        System.out.println(request.getRequestURI());
//        System.out.println(request.getHeader("Authorization"));
//
//        super.doFilterInternal(request, response, chain);
//
//    }
//}
