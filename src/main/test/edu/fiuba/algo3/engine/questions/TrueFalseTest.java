package edu.fiuba.algo3.engine.questions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.Score;

import java.util.ArrayList;
import java.util.List;

public class TrueFalseTest {
	
	@Test
	public void preguntaCorrectaSumaUnPuntoIncorrectaNoSumaNadaTest() {
		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");

		listaOpciones.add(opcionTrue);
		listaOpciones.add(opcionFalse);

		TrueFalseQuestion question = new TrueFalseQuestion("¿1 es mayor que 2?", listaOpciones);
		
		question.setCorrectOption(opcionFalse);
		
		assertEquals(new Score(1), question.calculatePoints(opcionFalse));
		assertEquals(new Score(0), question.calculatePoints(opcionTrue));
	}
	
	@Test
	public void preguntaCorrectaSumaUnPuntoIncorrectaRestaTest() {
		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");

		listaOpciones.add(opcionTrue);
		listaOpciones.add(opcionFalse);

		TrueFalseWithPenaltyQuestion question = new TrueFalseWithPenaltyQuestion("¿1 es mayor que 2?", listaOpciones);

		question.setCorrectOption(opcionFalse);
		
		assertEquals(new Score(1), question.calculatePoints(opcionFalse));
		assertEquals(new Score(-1), question.calculatePoints(opcionTrue));
		
	}
	
}
