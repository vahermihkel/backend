//package ee.mihkel.webshop.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);
//        TokeniLugeja tokeniLugeja = new TokeniLugeja(authenticationManager());
//
//        http
//                .cors().and().headers().xssProtection().disable().and()
//                .csrf().disable()
//                .addFilter(tokeniLugeja)
//                .authorizeRequests()
//                .antMatchers("/products").permitAll()
//                .antMatchers("/signup").permitAll()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated();
//    }
//}
