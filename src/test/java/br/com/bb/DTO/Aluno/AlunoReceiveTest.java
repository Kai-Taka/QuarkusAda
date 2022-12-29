package br.com.bb.DTO.Aluno;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.google.inject.spi.Message;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AlunoReceiveTest {

    private static final String NAME = "Nome";

    private static final String CURSO = "curso";

    @Test
    void testBuilderFunctionality()
    {
        //GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        //WHEN
        var request = AlunoReceive.builder()
                            .curso(CURSO)
                            .name(NAME)
                            .build();

        //THEN
        assertFields(validator, request);

        factory.close();
    }

    @Test 
    void constructorAllArgs()
    {
        //GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        //WHEN
        var request = new AlunoReceive(NAME, CURSO);
        //THEN
        assertFields(validator, request);

        factory.close();
    }

    @ParameterizedTest
    @MethodSource("invalidFields")
    void constructorNotAllowed(final String name, final String curso, final String errorMessage1, final String errorMessage2)
    {
        //GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        //WHEN
        var request = new AlunoReceive(name, curso);
        //THEN
        final var violations = validator.validate(request);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(2);
        
        List<String> violationsList = violations.stream().map(v -> v.getMessage()).collect(Collectors.toList());

        assertThat(violationsList.contains(errorMessage1)).isTrue();
        assertThat(violationsList.contains(errorMessage2)).isTrue();

        factory.close();
    }

    @Test
    void equalsAndHashCode()
    {
        EqualsVerifier.simple().forClass(AlunoReceive.class).verify();
    }


    static Stream<Arguments> invalidFields()
    {
        return Stream.of(
            Arguments.of(null, null, "Name cannot be null", "Curso cannot be null"),
            Arguments.of("    ", " ", "Name cannot be null", "Curso cannot be null")
        );
    }

    void assertFields(final Validator validador, final AlunoReceive request)
    {
        final var violations = validador.validate(request);

        assertThat(violations).isEmpty();

        assertThat(request.getName()).isEqualTo(NAME);
        assertThat(request.getCurso()).isEqualTo(CURSO);
    }
}
