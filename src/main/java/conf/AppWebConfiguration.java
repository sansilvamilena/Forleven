package conf;

import com.google.common.cache.CacheBuilder;
import controller.IndexController;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import viewresolver.JsonViewResolver;

/**
 *
 * @author milena
 */
@EnableWebMvc
@ComponentScan(basePackageClasses =
{
    IndexController.class
})
@EnableCaching
@PropertySource("classpath:application.properties")
public class AppWebConfiguration extends WebMvcConfigurerAdapter
{
   /*
   * Adiciona properties
   * Extremamente importante ser static para poder utilizar nesta classe também
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() 
  { 
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
    propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("application.properties"));
    propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
    return propertySourcesPlaceholderConfigurer;
  }

  /*
   * Gerenciador de Cache de memoria
   */
  @Bean
  public CacheManager cacheManager()	
  {
    //return new ConcurrentMapCacheManager(); utiliza o cacheable normal
    GuavaCacheManager guavaCacheManager = new GuavaCacheManager();
    CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
                    .expireAfterAccess(5, TimeUnit.MINUTES)
                    .maximumSize(100);
    guavaCacheManager.setCacheBuilder(cacheBuilder);
    return guavaCacheManager;
  }
  
  /*Permite que resources sejam colocados antes da web-inf
   * CSS, JS
   */
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
          configurer.enable();
  }

  /*Define os primeiros resources de resolução do Spring
   * prefixo, sufixo.
   */
  @Bean
  public InternalResourceViewResolver internalResourceViewResolver()
  {
    InternalResourceViewResolver resolver
            = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
  }
  
  /*Interface para mensagens de validação*/
  @Bean
  public MessageSource messageSource()
  {
    ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
    reloadableResourceBundleMessageSource.setBasename("WEB-INF/resources/messages");
    reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
    reloadableResourceBundleMessageSource.setCacheSeconds(1);
    return reloadableResourceBundleMessageSource;
  }
  
  /*
   *  Interface para converter as datas para o padrao Spring
   */
  @Bean
  public FormattingConversionService mvcConversionService()
  {
    DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
    DateFormatter dateFormatter = new DateFormatter("dd/MM/yyyy");
    DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
    formatterRegistrar.setFormatter(dateFormatter);
    formatterRegistrar.registerFormatters(conversionService);
    return conversionService;
  }
  
 /*
  * Metodo interface para receber varios tipos de header (html/json/xml...)
  * */
  @Bean
  public ViewResolver viewResolver(ContentNegotiationManager contentNegotiationManager)
  {
    List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
    resolvers.add(internalResourceViewResolver());
    resolvers.add(new JsonViewResolver());

    ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
    contentNegotiatingViewResolver.setContentNegotiationManager(contentNegotiationManager);
    contentNegotiatingViewResolver.setViewResolvers(resolvers);
    return contentNegotiatingViewResolver;
  }
  
  /*Permite o Spring receber multipart form*/
  @Bean
  public MultipartResolver multipartResolver()
  {
    return new StandardServletMultipartResolver();
  } 
  
}
