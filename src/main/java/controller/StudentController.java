package controller;

import javax.validation.Valid;
import model.Estudante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repository.EstudanteDAO;

/**
 *
 * @author milena
 */
@Controller
@RequestMapping("/student")
@Configuration
@ComponentScan("repository")
public class StudentController 
{
  @Autowired
  private EstudanteDAO estudanteDAO;

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView view(Estudante estudante) {
    estudante.setCodEstudante(estudanteDAO.getLastID() + 1);
    return new ModelAndView("student/view");
  }

  @RequestMapping(method = RequestMethod.POST)
  @Transactional
  public ModelAndView save(@Valid Estudante estudante, Errors errors) 
  {
    if (errors.hasErrors()) {
      return view(estudante);
    } else {
      estudante.setCodEstudante(null);
      estudanteDAO.save(estudante);
      return new ModelAndView("redirect:/student");
    }
  }
    
  @RequestMapping(method = RequestMethod.GET,value = "/list")
  public ModelAndView list()
  {
    return new ModelAndView("/student/list")
       .addObject("lista", estudanteDAO.findAll());
  }
  
  @RequestMapping(method = RequestMethod.GET, value = "{codEstudante}")
  public ModelAndView show(@PathVariable("codEstudante") Long codEstudante)
  {
    Estudante e = estudanteDAO.findById(codEstudante);
    ModelAndView view = new ModelAndView("/student/show");
    if(e!=null && e.getCodEstudante()!=null)
    {
      view.addObject("estudante",e);
    }
    else
    {
      view.setViewName("/student/list");
    }
    return view;
  }
  
  @RequestMapping(method = RequestMethod.POST, value = "update")
  @Transactional
  public ModelAndView update(Estudante estudante, Errors errors) 
  {    
    if (errors.hasErrors()) {
      return view(estudante);
    }
    else
    {
      estudanteDAO.save(estudante);
      return new ModelAndView("redirect:/student/"+estudante.getCodEstudante());
    }
  }
  
  @RequestMapping(method = RequestMethod.GET, value = "delete/{codEstudante}")
  @Transactional
  public ModelAndView delete(@PathVariable("codEstudante") Long codEstudante) 
  {    
    Estudante e = estudanteDAO.findById(codEstudante);
    if(e!=null && e.getCodEstudante()!=null)
    {
      estudanteDAO.delete(e);
    }
    else
    {
      list();
    }
    return list();
  }
  
}