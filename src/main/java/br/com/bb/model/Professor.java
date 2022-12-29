package br.com.bb.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
   
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String titulo;

    @OneToMany(mappedBy = "tutor")
    @JsonIgnore
    private List<Aluno> tutorados;

    @OneToOne(mappedBy = "titular", fetch = FetchType.LAZY)
    @JsonIgnore
    private Curso cursosMinistrado;

}
