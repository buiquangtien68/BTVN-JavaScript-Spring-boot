package com.example.demo.security;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    String[] pathForSale={"/api/admin/manage-category","/api/admin/manage-product", "/api/admin/manage-brand","/api/admin/manage-order","/api/admin/manage-blog"};

    //cấu hình path
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->{
            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/user").hasRole("USER");
            auth.requestMatchers("/dashboard").hasRole("ADMIN");

            auth.requestMatchers("api/admin/**").hasRole("ADMIN");
            auth.requestMatchers(pathForSale).hasAnyRole("ADMIN","SALE");
            auth.requestMatchers("api/admin/manage-blog/").hasAnyRole("ADMIN","SALE","AUTHOR");
            auth.requestMatchers("api/user/**").authenticated();
            auth.anyRequest().authenticated();

            //ví dụ:

//            auth.requestMatchers("/author").hasAnyRole("ADMIN","USER");
//            auth.requestMatchers("/css/**", "/js/**", "/image/**").permitAll();
//            auth.requestMatchers(HttpMethod.GET, "/aa/**","/bb/**").hasRole("ADMIN");
//            auth.requestMatchers("/abc","bcd").hasAuthority("ROLE_USER");
//            auth.requestMatchers("/xxx","yyy").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");

        });

        //cấu hình login
        http.formLogin(formLogin->{
            formLogin.loginPage("/login"); //trang login do mình thiết kế
            formLogin.defaultSuccessUrl("/",true); //login thành công chuyenr hg về trang chủ
           formLogin.permitAll();//login ai cx truy cập được
        });

        //cấu hình logout
        http.logout(logout->{
            logout.logoutSuccessUrl("/"); // nếu logout thành công thì chuyển về trng chủ
            logout.deleteCookies("JSESSIONID"); // xóa cookie
            logout.invalidateHttpSession(true);//hủy session
            logout.clearAuthentication(true);//xóa thông tin xác thực
            logout.permitAll();//ai cx truy cập được
        });
        return http.build();
    }


}
