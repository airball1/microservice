package com.imooc.course;

import com.imooc.course.filter.CourseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liuzike
 * @Date 1/6/21
 **/
@SpringBootApplication
public class MainProvider {
    public static void main(String[] args) {

        SpringApplication.run(MainProvider.class,args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        CourseFilter courseFilter = new CourseFilter();
        filterRegistrationBean.setFilter(courseFilter);

        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        filterRegistrationBean.setUrlPatterns(urlPatterns);

        return filterRegistrationBean;
    }
}