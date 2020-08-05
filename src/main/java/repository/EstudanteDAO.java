package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import model.Estudante;
import org.springframework.stereotype.Repository;

/**
 *
 * @author milena
 */
@Repository
public class EstudanteDAO 
{
  @PersistenceContext
  private EntityManager em;
  
  public void save(Estudante estudante)
  {
    em.merge(estudante);
  }
  
  public Long getLastID()
  {
    try
    {
      return (Long) em.createQuery("select count(e) from ESTUDANTE e").getSingleResult();
    }
    catch(NoResultException e)
    {
      return Long.valueOf(1);
    }
  }
  
  public List<Estudante> findAll()
  {
    List<Estudante> dados = new ArrayList<>();
    try
    {
      dados = em.createQuery("select new ESTUDANTE(e.codEstudante,e.matricula,e.nome,e.sobrenome) "
              + "from ESTUDANTE e ", Estudante.class)
              .getResultList();
    }
    catch(NoResultException e){   }
    return dados;
  }
  
  public Estudante findById(Long codEstudante)
  {
    Estudante estudante = new Estudante();
    try
    {
      estudante =  em.createQuery("select e from ESTUDANTE e where e.codEstudante=:codEstudante",Estudante.class)
              .setParameter("codEstudante", codEstudante)
              .getSingleResult();
    }
    catch(NoResultException e){}
    return estudante;
  }
  
  public void delete(Estudante estudante)
  {
    em.remove(estudante);
  }
}
