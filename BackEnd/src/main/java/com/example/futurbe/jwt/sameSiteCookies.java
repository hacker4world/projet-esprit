package com.example.futurbe.jwt;

import org.apache.catalina.Context;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class sameSiteCookies{

    @Bean
    public TomcatContextCustomizer sameSiteCookiesConfig() {
        return (TomcatContextCustomizer) context -> {
            Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
            cookieProcessor.setSameSiteCookies("Lax"); // Adjust if needed (Strict, None with Secure)

            Context tomcatContext = (Context) context;
            tomcatContext.setCookieProcessor(cookieProcessor);
        };
    }

}
