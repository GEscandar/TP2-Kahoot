package edu.fiuba.algo3.entregas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.fiuba.algo3.model.Score;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.engine.questions.TrueFalseQuestion;
import edu.fiuba.algo3.engine.score.ScoreCalculator;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.MatchResult;
import edu.fiuba.algo3.model.Player;

public class EntregaCeroTest {

	/***
	 * Una Pregunta de Verdadero/Falso clásico puede crearse indicándole cual es la respuesta correcta
	 */
	@Test
	public void preguntaCorrectaSumaUnPuntoIncorrectaNoSumaNadaTest() {
		TrueFalseQuestion question = new TrueFalseQuestion("¿1 es mayor que 2?");
		
		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");
		
		question.setCorrectOption(opcionFalse);
		
		assertEquals(1, question.calculatePoints(opcionFalse));
		assertEquals(0, question.calculatePoints(opcionTrue));
	}
	
	/***
	 * Una Pregunta de Verdadero/Falso clásico recibe una lista de respuestas y asigna correctamente puntos a los jugadores que respondieron correctamente
	 */
	@Test
	public void calculoDePreguntaTrueFalseAsignaPuntosALosJugadoresTest() {		
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");
		
		TrueFalseQuestion question = new TrueFalseQuestion("¿1 es mayor que 2?");
		
		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");
		
		question.setCorrectOption(opcionFalse);

		MatchResult resultJugadorUno = jugadorUno.answerQuestion(opcionTrue);
		MatchResult resultJugadorDos = jugadorDos.answerQuestion(opcionFalse);
		ScoreCalculator.calculateAndAssignPoints(question, resultJugadorUno, resultJugadorDos);

		assertEquals(new Score(0), jugadorUno.getScore());
		assertEquals(new Score(1), jugadorDos.getScore());
	}
}
