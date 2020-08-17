package edu.fiuba.algo3.loaders;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fiuba.algo3.constants.QuestionType;
import edu.fiuba.algo3.constants.ResourceConstants;
import edu.fiuba.algo3.engine.questions.GroupChoiceQuestion;
import edu.fiuba.algo3.exceptions.QuestionsNotLoadedException;
import edu.fiuba.algo3.model.Question;

public class QuestionLoaderTest {
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionLoaderTest.class);
	
	@Test
	public void cargarListaDePreguntasDePruebaYComprobarElNumeroDeElementosTest() {
		try {
			List<Question> lista = QuestionLoader.loadQuestions(ResourceConstants.QUESTIONS_TEST_PATH);
			assertEquals(7, lista.size());
		} catch (Exception ex) {
			logger.error("Pregunta no cargada", ex);
			fail();
		}
	}
	
	@Test
	public void cargarListaDePreguntasYComprobarQueHayaUnaDeCadaTipoTest() {
		try {
			List<Question> lista = QuestionLoader.loadQuestions(ResourceConstants.QUESTIONS_TEST_PATH);
			for(QuestionType type : QuestionType.values()) {
				List<Question> listaPreguntas = lista.stream().filter(elem -> elem.getType().equals(type)).collect(Collectors.toList());
				assertEquals(1, listaPreguntas.size());
			}
		} catch (Exception ex) {
			logger.error("Pregunta no cargada", ex);
			fail();
		}
	}
	
	@Test
	public void cargarListaDePreguntasYComprobarQueHayaCargadoTodosSusValoresTest() {
		try {
			List<Question> lista = QuestionLoader.loadQuestions(ResourceConstants.QUESTIONS_TEST_PATH);
			for(Question question : lista) {
				assertNotNull(question.getCorrectOptions());
				assertNotNull(question.getOptions());
				assertNotNull(question.getText());
				assertNotNull(question.getType());	
				if(question instanceof GroupChoiceQuestion) {
					GroupChoiceQuestion groupQuestion = (GroupChoiceQuestion) question;
					assertNotNull(groupQuestion.getOptionGroupList());
					assertTrue(!groupQuestion.getOptionGroupList().isEmpty());
				}
			}
		} catch (Exception ex) {
			logger.error("Pregunta no cargada", ex);
			fail();
		}
	}
	
	@Test
	public void cargarListaDePreguntasErroneaDebeTirarErrorTest() {
		assertThrows(QuestionsNotLoadedException.class, () -> {
			QuestionLoader.loadQuestions("");
		});
	}
}
