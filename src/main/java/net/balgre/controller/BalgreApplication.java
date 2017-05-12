package net.balgre.controller;

import net.balgre.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.nio.charset.Charset;

/**
 * What :
 * Why :
 * How :
 *
 * @author  김진국
 * @version 1.0
 * @since   2017/04/10
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017.04.10  김진국          최초 생성
 *  2017.04.17  김진국          SpringBootApplication 수정 - Using default security password: 때문에 수정
 *  </pre>
 *
 */

@Controller
@ComponentScan("net.balgre")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BalgreApplication {

    private static final Logger logger = LoggerFactory.getLogger(BalgreApplication.class);

    @Autowired
    private MainService service;


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BalgreApplication.class, args);
    }

    /**
     * SVN 이용시 한글 깨짐 문제 http://theeye.pe.kr/archives/2206 - 김진국
     *
     * @return the http message converter
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    /**
     * SVN 이용시 한글 깨짐 문제 http://theeye.pe.kr/archives/2206 - 김진국
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
//
//    @RequestMapping("/")
////    @ResponseBody
//    public String home() {
//
//        logger.info("lll~~~ 프로그램 실행 시 기본 접속 메인 화면 . lll~~~");
//
//        return "main/main";
////		return "/index";
//    }
}
