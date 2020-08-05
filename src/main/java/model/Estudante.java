package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author milena
 */
@Getter
@Setter
@Entity(name = "ESTUDANTE")
public class Estudante 
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long codEstudante;
  @NotEmpty
  @NotNull 
  @Length(min=3)
  @Column(unique = true)
  private String matricula;
  @NotEmpty
  @NotNull
  @Length(min=3)
  private String nome;
  @NotEmpty
  @NotNull
  @Length(min=3)
  private String sobrenome;

  public Estudante(){}

  public Estudante(Long codEstudante, String matricula, String nome, String sobrenome){
    this.codEstudante = codEstudante;
    this.matricula = matricula;
    this.nome = nome;
    this.sobrenome = sobrenome;
  }

  @Override
  public String toString()
  {
    return "";
  }
}
