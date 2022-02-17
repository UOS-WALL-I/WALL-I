package NPC.walli.config;

import NPC.walli.filter.AdminCheckFilter;
import NPC.walli.filter.Filter;
import NPC.walli.filter.LoginCheckFilter;
import NPC.walli.filter.MypageCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Bean
//    public FilterRegistrationBean loginCheckFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new LoginCheckFilter());
//        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.addUrlPatterns("/*");
//
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean adminCheckFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new AdminCheckFilter());
//        filterRegistrationBean.setOrder(2);
//        filterRegistrationBean.addUrlPatterns("/admin");
//
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean myPageCheckFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new MypageCheckFilter());
//        filterRegistrationBean.setOrder(3);
//        filterRegistrationBean.addUrlPatterns("/update");
//
//        return filterRegistrationBean;
//    }
}
