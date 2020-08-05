package viewresolver;

import java.util.Locale;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 *
 * @author milena
 */
public class JsonViewResolver implements ViewResolver
{
  @Override
  public View resolveViewName(String string, Locale locale) throws Exception
  {
    MappingJackson2JsonView view = new MappingJackson2JsonView();
    view.setPrefixJson(true); // facil visualização
    return view;
  }
}
