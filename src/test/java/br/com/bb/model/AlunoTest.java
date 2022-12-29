package br.com.bb.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import javax.persistence.Id;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AlunoTest {

    private static final String NOME = "nome";
    private static final int ID = 100;
    private static final Professor TUTOR = Professor.builder()
                                            .name("NOME")
                                            .id(101)
                                            .cursosMinistrado(null)
                                            .tutorados(new ArrayList<Aluno>())
                                            .build();
    private static final Curso CURSO = Curso.builder()
                                        .anos(5)
                                        .nome("2")
                                        .id(102)
                                        .titular(null)
                                        .gradeDeMaterias(new ArrayList<Materias>())
                                        .alunosMatriculados(new ArrayList<Aluno>())
                                        .build();



    @Test
    void testBuilder() {
        //GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        //WHEN
        var request = Aluno.builder()
                            .cursa(CURSO)
                            .id(ID)
                            .nome(NOME)
                            .tutor(TUTOR)
                            .build();

        //THEN
        assertFields(validator, request);

        factory.close();
    }

    @Test
    void testAllArgsConstructor()
    {
        //GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        //WHEN
        var request = new Aluno(ID, NOME, CURSO, TUTOR);

        //THEN
        assertFields(validator, request);

        factory.close();
    }

    @Test
    void noArgsConstructor()
    {
        //GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        //WHEN
        var request = new Aluno();
        request.setCursa(CURSO);
        request.setId(ID);
        request.setNome(NOME);
        request.setTutor(TUTOR);

        //THEN
        assertFields(validator, request);

        factory.close();
    }

    @Test
    void equalsAndHashcode()
    {
        EqualsVerifier.simple().forClass(Aluno.class)
                .withIgnoredAnnotations(Id.class)
                .withPrefabValues(Professor.class, 
                                    Professor.builder()
                                    .name("NOME")
                                    .id(101)
                                    .cursosMinistrado(null)
                                    .tutorados(new ArrayList<Aluno>())
                                    .build(), 
                                new Professor())
                .withPrefabValues(Curso.class,
                                    Curso.builder()
                                    .anos(5)
                                    .nome("2")
                                    .id(102)
                                    .titular(null)
                                    .gradeDeMaterias(new ArrayList<Materias>())
                                    .alunosMatriculados(new ArrayList<Aluno>())
                                    .build(),
                                 new Curso())
                                    
                .verify();
    }

    void assertFields(final Validator validador, final Aluno request)
    {
        final var violations = validador.validate(request);

        assertThat(violations).isEmpty();

        assertThat(request.getCursa()).isEqualTo(CURSO);
        assertThat(request.getId()).isEqualTo(ID);
        assertThat(request.getNome()).isEqualTo(NOME);
        assertThat(request.getTutor()).isEqualTo(TUTOR);

        
    }

}
