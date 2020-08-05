package conf;

import java.io.File;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author milena
 */
public class SpringServlet extends AbstractAnnotationConfigDispatcherServletInitializer
{
  private int maxUploadSizeInMb = 600 * 1024 * 1024; // 5 MB
  
  /*Utilizado para o que sera usado antes da config classes rolar/
  */
  @Override
  protected Class<?>[] getRootConfigClasses()
  {
    return new Class[]{AppWebConfiguration.class,JPAConfig.class};
  }

  /*Metodo para definir todas as configurações que vai subir no Spring
   * AppConfig, JPAConfig 
   */
  @Override
  protected Class<?>[] getServletConfigClasses()
  {
    return new Class[]{};
  }

  /* Metodo para definir o primeiro caminho que o Spring ira buscar
   * Normalmente é o /
   */
  @Override
  protected String[] getServletMappings()
  {
    return new String[]{"/"};
  }
  
  /*
  * Metodo para setar o charset do Spring para toda a aplicação
  * */
 @Override
 protected Filter[] getServletFilters() 
 {
  //Charset
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    
  //Method HTTP Filter (para PUT e DELETE)
   HiddenHttpMethodFilter httpMethodFilter = new HiddenHttpMethodFilter();
    return new Filter[]{characterEncodingFilter,httpMethodFilter};
 }
 
  /*Customiza a forma de registro do form*/
  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    // upload temp file will put here
    File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));
          registration.setMultipartConfig(new MultipartConfigElement(
                  uploadDirectory.getAbsolutePath(),
                  maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2));
  }
}
