package ru.kornilov.reha.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.kornilov.reha.config.security.SecurityConfig;


@Import({SecurityConfig.class})
@Configuration
@ComponentScan("ru.kornilov.reha")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    @Value("C:/Users/Serega/Desktop/T-System/Reha/src/uploads")
    private String uploadPath;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("main/login");
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/");
        return freeMarkerConfigurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("js/**").addResourceLocations("/WEB-INF/resources/js/");
        registry.addResourceHandler("img/**").addResourceLocations("/WEB-INF/resources/img/");
        registry.addResourceHandler("css/**").addResourceLocations("/WEB-INF/resources/css/");
//        registry.addResourceHandler("upload/**")
//                .addResourceLocations("/WEB-INF/resources/uploads/");
        registry.addResourceHandler("upload/**")
                .addResourceLocations("file:/C:/Users/Serega/Desktop/T-System/Reha/src/uploads/");
    }
}