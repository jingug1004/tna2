package net.balgre.config;

import net.balgre.interceptor.AuthInter;
import net.balgre.interceptor.LoginInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.Filter;
import java.nio.charset.Charset;

/**
 * Created by user on 2017-04-11 오전 10:06
 * Prac / net.balgre.controller
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author  숨 크리에이티브 개발팀 정호성
 * @version 1.0
 * @see
 * @since   2017/04/10
 * <p>
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    -------------------------------------------------------------------------------------
 *  2017/04/11   정호성          최초 배포 - 김진국 Velocity 연결 접속 불가(404)해서 전달 받음 - 하지만 상관 없었음!
 *  2017/04/18   김진국          인터셉터가 작동할 수 있도록 addInterceptors 메소드 등록
 *  </pre>
 */

@Configuration
@EnableWebMvc
@EnableAutoConfiguration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInter loginInter;

    @Autowired
    private AuthInter authInter;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//		registry.addResourceHandler("/vendors/**").addResourceLocations("classpath:/static/vendors/");
//		registry.addResourceHandler("/dist/**").addResourceLocations("classpath:/static/dist/");
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/font/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/static/plugins/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
//        https://okky.kr/article/346227 - 김진국 17/04/12 10:25 해결 완료
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("redirect:/login");
//		registry.addViewController("/admin/login").setViewName("login");
//		registry.addViewController("/error/401").setViewName("/error/401");
//		registry.addViewController("/error/404").setViewName("/error/404");
//		registry.addViewController("/error/500").setViewName("/error/500");
    }

    /**
     * Container customizer embedded servlet container customizer.
     *
     * @return the embedded servlet container customizer
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
//				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401");
//				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
//				ErrorPage error505Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
//
//				container.addErrorPages(error404Page, error505Page, error401Page);
            }
        };
    }

    /**
     * Response body converter http message converter.
     *
     * @return the http message converter
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    /**
     * Character encoding filter filter.
     *
     * @return the filter
     */
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

//    @Bean
//    public VelocityConfigurer velocityConfigurer() {
//        VelocityConfigurer configurer = new VelocityConfigurer();
////        configurer.setResourceLoaderPath("classpath:/templates");
//        configurer.setResourceLoader("classpath:/templates");
//        Properties properties = new Properties();
//        properties.setProperty("input.encoding", "UTF-8");
//        properties.setProperty("output.encoding", "UTF-8");
//        configurer.setVelocityProperties(properties);
//        return configurer;
//    }
//	@Bean
//	ServletRegistrationBean h2servletRegistration(){
//		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
//		registrationBean.addUrlMappings("/console/*");
//		return registrationBean;
//	}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInter)
                .addPathPatterns("/loginPost")
                .excludePathPatterns();

//        registry.addInterceptor(authInter)
//                .addPathPatterns("/plan")
////                추후에 addPathPatterns에 많이 넣어야 함. 웹 페이지 많이 만들어지면.
//                .excludePathPatterns();
        
        registry.addInterceptor(authInter)
                .addPathPatterns("/my/qna")
                .excludePathPatterns();
        
        registry.addInterceptor(authInter)
                .addPathPatterns("/my/index")
                .excludePathPatterns();
        
        registry.addInterceptor(authInter)
                .addPathPatterns("/input_form")
                .excludePathPatterns();
        
        registry.addInterceptor(authInter)
                .addPathPatterns("/my/qnaList")
                .excludePathPatterns();
        
        registry.addInterceptor(authInter)
                .addPathPatterns("/qna/delete")
                .excludePathPatterns();
        
        registry.addInterceptor(authInter)
                .addPathPatterns("/my/privacy")
                .excludePathPatterns();
        registry.addInterceptor(authInter)
        		.addPathPatterns("/payment/**")
        		.excludePathPatterns();
    }

}