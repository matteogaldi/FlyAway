package it.uniroma3.siw.flyAway.authentication

import it.uniroma3.siw.flyAway.model.Credentials
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class AuthenticationConfiguration(private val dataSource: DataSource) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            // authorization paragraph: qui definiamo chi può accedere a cosa
            .authorizeRequests()

            // chiunque (autenticato o no) può accedere alle pagine index, login, register, ai css e alle immagini
            .antMatchers(HttpMethod.GET, "/", "/index", "/login", "/register", "/css/**", "/images/**").permitAll()

            // chiunque (autenticato o no) può mandare richieste POST al punto di accesso per login e register
            .antMatchers(HttpMethod.POST, "/login", "/register").permitAll()

            // solo gli utenti autenticati con ruolo ADMIN possono accedere a risorse con path /admin/**
            .antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(Credentials.ADMIN_ROLE)
            .antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(Credentials.ADMIN_ROLE)

            // tutti gli utenti autenticati possono accere alle pagine rimanenti
            .anyRequest().authenticated()

            // login paragraph: qui definiamo come è gestita l'autenticazione
            // usiamo il protocollo formlogin
            .and().formLogin()

            // la pagina di login si trova a /login
            // NOTA: Spring gestisce il post di login automaticamente
            .loginPage("/login")

            // se il login ha successo, si viene rediretti al path /default
            .defaultSuccessUrl("/default")

            // logout paragraph: qui definiamo il logout
            .and().logout()

            // il logout è attivato con una richiesta GET a "/logout"
            .logoutUrl("/logout")
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))

            // in caso di successo, si viene reindirizzati alla /index page

            .logoutSuccessUrl("/index")
            .invalidateHttpSession(true)
            .clearAuthentication(true).permitAll();
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .authoritiesByUsernameQuery("SELECT email, role FROM credentials WHERE email=?")
            .usersByUsernameQuery("SELECT email, password, 1 as enabled FROM credentials WHERE email=?")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}