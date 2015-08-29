package be.gerard.core.config;

import be.gerard.common.security.config.AuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * SecurityConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
@EnableWebSecurity
@Import(AuthenticationConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationConfig authenticationConfig;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        //web.ignoring().antMatchers();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authenticationProvider(authenticationConfig.commonAuthenticationProvider())
                .authorizeRequests()
                .antMatchers("/javax.faces.resource/**").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/templates/**").permitAll()
                .antMatchers("/admin/**").authenticated()//.access("hasRole('USER')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/public/login.xhtml").permitAll()
                .loginProcessingUrl("j_spring_security_check")
                .defaultSuccessUrl("/admin/translation/List.xhtml", true)
                .failureUrl("/public/login.xhtml?error")
                .usernameParameter("j_username").passwordParameter("j_password")
                .and()
                .logout().logoutSuccessUrl("/public/login.xhtml?logout").invalidateHttpSession(true)
                .and()
                .csrf();
    }

}
