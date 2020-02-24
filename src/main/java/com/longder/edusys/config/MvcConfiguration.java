package com.longder.edusys.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {


    /**
     * 资源映射，把url映射到src下classpath
     * @param registry ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**", "/templates/**")
                .addResourceLocations("classpath:/static/", "classpath:/templates/");

    }

    /**
     * thymeleaf模板引擎配置
     * @return
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    /**
     * thymeleaf模板引擎配置
     * @return
     */
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        Set<IDialect> conditionSet = new HashSet<>();
        conditionSet.add(new SpringSecurityDialect());
        templateEngine.setAdditionalDialects(conditionSet);
        return templateEngine;
    }

    /**
     * 视图解析器
     * @return
     */
    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    /**
     * 验证码工具的bean
     * @return
     */
    @Bean
    public Producer producer(){
        Properties properties = new Properties();
        properties.put("kaptcha.border","yes");
        properties.put("kaptcha.border.color","105,179,90");

        properties.put("kaptcha.image.width","200");
        properties.put("kaptcha.image.height","50");

        properties.put("kaptcha.textproducer.font.color","blue");
        properties.put("kaptcha.textproducer.char.string","abcdefghijklmnopqrstuvwxyz0123456789");
        properties.put("kaptcha.textproducer.char.length","4");
        properties.put("kaptcha.textproducer.char.space","10");
        properties.put("kaptcha.textproducer.font.size","30");
        properties.put("kaptcha.textproducer.font.names","微软雅黑");


        Config producerConfig = new Config(properties);
        DefaultKaptcha producer = new DefaultKaptcha();
        producer.setConfig(producerConfig);

        return producer;
    }



}
