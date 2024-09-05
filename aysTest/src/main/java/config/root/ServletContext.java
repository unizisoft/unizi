package config.root;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles2.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import egovframework.example.cmmn.web.EgovImgPaginationRenderer;
import egovframework.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;



@Configuration
@ComponentScan(
		basePackages="egovframework",
		includeFilters= {
				@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Controller.class)
		},
		excludeFilters= {
				@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Service.class)
				,@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Repository.class)
				,@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Configuration.class)
		}
		
)
public class ServletContext extends WebMvcConfigurationSupport {

	@Bean
	@Override
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter rmha = super.requestMappingHandlerAdapter();
		return rmha;
	}
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		return sessionLocaleResolver;
	}
	
	/**
	 //쿠키를 이용한 Locale 이용시 이부분 주석 풀어서 사용하고 위에 있는 SessionLocaleResolver는 주석처리한다...
	  
	 @Bean
	 public CookieLocaleResolver clocaleResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		return cookieLocaleResolver;
	 }
	 */
	
	/*must not be private or final; change the method's modifiers to continue private 안됨*/
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}
	
	@Override
	protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
		smer.setDefaultErrorView("cmmn/egovError");
		Properties mappings = new Properties();
		mappings.setProperty("org.springframework.dao.DataAccessException", "cmmn/dataAccessFailure");
		mappings.setProperty("org.springframework.transaction.TransactionException", "cmmn/transactionFailure");
		mappings.setProperty("egovframework.rte.fdl.cmmn.exception.EgovBizException", "cmmn/egovError");
		mappings.setProperty("org.springframework.security.AccessDeniedException", "cmmn/egovError");
		smer.setExceptionMappings(mappings);
		exceptionResolvers.add(smer);
	}
	
	@Bean
	public UrlBasedViewResolver urlBasedViewResolver(){
		UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		urlBasedViewResolver.setOrder(1);
		urlBasedViewResolver.setViewClass(JstlView.class);
		urlBasedViewResolver.setPrefix("/WEB-INF/jsp/egovframework/example/");
		urlBasedViewResolver.setSuffix(".jsp");	
		return urlBasedViewResolver;
	}
	
	@Bean 
	public EgovImgPaginationRenderer egovImgPaginationRenderer() {
		EgovImgPaginationRenderer egovImgPaginationRenderer = new EgovImgPaginationRenderer();
		return egovImgPaginationRenderer;
	}
	
	@Bean
	public DefaultPaginationManager paginationManager(EgovImgPaginationRenderer imageRenderer){
		DefaultPaginationManager defaultPaginationManager = new DefaultPaginationManager();
		Map<String, PaginationRenderer> rendererType = new HashMap<String, PaginationRenderer>();
		rendererType.put("image",imageRenderer);
		defaultPaginationManager.setRendererType(rendererType);
		return defaultPaginationManager;
	}
	
	protected void addViewController(ViewControllerRegistry registry){
		registry.addViewController("/cmmn/validator.do").setViewName("cmmn/validator");
	}
	
	
	//tiles 세팅도 여기에 해보자
	@Bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(org.springframework.web.servlet.view.tiles3.TilesView.class);
		tilesViewResolver.setOrder(0);
		return tilesViewResolver;
	}
	
	@Bean
    public TilesConfigurer tilesConfigurer() {
        final TilesConfigurer configurer = new TilesConfigurer();

        configurer.setDefinitions(new String[]{"/WEB-INF/tiles/tiles.xml"});
        configurer.setCheckRefresh(true);
        return configurer;
    }
}
