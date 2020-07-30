package edu.fiuba.algo3.entregas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.engine.questions.MultipleChoicePartialQuestion;
import edu.fiuba.algo3.engine.questions.MultipleChoiceQuestion;
import edu.fiuba.algo3.engine.questions.MultipleChoiceWithPenaltyQuestion;
import edu.fiuba.algo3.engine.questions.TrueFalseWithPenaltyQuestion;
import edu.fiuba.algo3.engine.score.ScoreCalculator;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.MatchResult;
import edu.fiuba.algo3.model.Player;

public class EntregaUnoTest {
	
	/***
	 * Una Pregunta de Verdadero/Falso con penalidad puede crearse indicándole cual es la respuesta correcta
	 */
	@Test
	public void preguntaCorrectaSumaUnPuntoIncorrectaRestaTest() {
		TrueFalseWithPenaltyQuestion question = new TrueFalseWithPenaltyQuestion("¿1 es mayor que 2?");
		
		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");
		
		question.setCorrectOption(opcionFalse);
		
		assertEquals(1, question.calculatePoints(opcionFalse));
		assertEquals(-1, question.calculatePoints(opcionTrue));
		
	}
	
	/***
	 * Una Pregunta de Múltiple Choice clásico puede crearse indicándole cuales son las opciones correctas
	 */
	@Test
	public void opcionesCorrectasSumaUnPunto() {
		MultipleChoiceQuestion question = new MultipleChoiceQuestion("¿Que numeros son impares?");
		
		GameOption opcionUno = new GameOption("1");
		GameOption opcionTres = new GameOption("3");
		
		List<GameOption> listaOpcionesCorrectas = new ArrayList<GameOption>();
		listaOpcionesCorrectas.add(opcionUno);
		listaOpcionesCorrectas.add(opcionTres);
		
		question.setCorrectOptions(listaOpcionesCorrectas);
		
		List<GameOption> listaOpcionesElegidas = new ArrayList<GameOption>();
		listaOpcionesElegidas.add(opcionUno);						
		listaOpcionesElegidas.add(opcionTres);
		
		assertEquals(1, question.calculatePoints(listaOpcionesElegidas));
	}
	
	/***
	 * Una Pregunta de Múltiple Choice con puntaje parcial puede crearse indicándole cuales son las opciones correctas
	 */
	@Test
	public void opcionesCorrectasSumaUnPuntoCadaUna() {
		MultipleChoiceWithPenaltyQuestion question = new MultipleChoiceWithPenaltyQuestion("¿Que numeros son impares?");
		GameOption opcionUno = new GameOption("1");
		GameOption opcionTres = new GameOption("3");
		
		List<GameOption> listaOpcionesCorrectas = new ArrayList<GameOption>();
		listaOpcionesCorrectas.add(opcionUno);
		listaOpcionesCorrectas.add(opcionTres);
		
		question.setCorrectOptions(listaOpcionesCorrectas);
		
		List<GameOption> listaOpcionesElegidas = new ArrayList<GameOption>();
		listaOpcionesElegidas.add(opcionUno);	
		listaOpcionesElegidas.add(opcionTres);
		
		assertEquals(2, question.calculatePoints(listaOpcionesElegidas));
	}
	
	/***
	 * Una Pregunta de Verdadero/Falso con penalidad recibe una lista de respuestas y asigna correctamente puntos a 
	 * los jugadores que respondieron correctamente, y resta correctamente puntos a los jugadores que respondieron en forma incorrecta
	 */
	@Test
	public void calculoDePreguntaTrueFalseConPenalidadAsignaPuntosALosJugadoresTest() {		
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");
		
		TrueFalseWithPenaltyQuestion question = new TrueFalseWithPenaltyQuestion("¿1 es mayor que 2?");
		
		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");
		
		question.setCorrectOption(opcionFalse);
		
		MatchResult resultJugadorUno = new MatchResult(jugadorUno, opcionTrue);
		MatchResult resultJugadorDos = new MatchResult(jugadorDos, opcionFalse);
		ScoreCalculator.calculateAndAssignPoints(resultJugadorUno, resultJugadorDos, question);
		
		assertEquals(-1, jugadorUno.getScore().getValue());
		assertEquals(1, jugadorDos.getScore().getValue());
	}
	
	/***
	 * Una Pregunta de Multiple Choice clasico recibe una lista de respuestas y asigna 
	 * correctamente puntos a los jugadores que respondieron correctamente
	 */
	@Test
	public void calculoDePreguntaMultipleChoiceAsignaPuntosALosJugadoresTest() {		
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");
		
		MultipleChoiceQuestion question = new MultipleChoiceQuestion("¿Que numeros son impares?");
		
		GameOption opcionUno = new GameOption("1");
		GameOption opcionDos = new GameOption("2");
		GameOption opcionTres = new GameOption("3");
		
		List<GameOption> listaOpcionesCorrectas = new ArrayList<GameOption>();
		listaOpcionesCorrectas.add(opcionUno);
		listaOpcionesCorrectas.add(opcionTres);
		
		question.setCorrectOptions(listaOpcionesCorrectas);
		
		List<GameOption> opcionesJugadorUno = new ArrayList<GameOption>();
		opcionesJugadorUno.add(opcionUno);						
		opcionesJugadorUno.add(opcionTres);
		
		List<GameOption> opcionesJugadorDos = new ArrayList<GameOption>();
		opcionesJugadorDos.add(opcionUno);						
		opcionesJugadorDos.add(opcionDos);
		
		MatchResult resultJugadorUno = new MatchResult(jugadorUno, opcionesJugadorUno);
		MatchResult resultJugadorDos = new MatchResult(jugadorDos, opcionesJugadorDos);
		ScoreCalculator.calculateAndAssignPoints(resultJugadorUno, resultJugadorDos, question);
		
		assertEquals(1, jugadorUno.getScore().getValue());
		assertEquals(0, jugadorDos.getScore().getValue());
	}
	
	/***
	 * Una Pregunta de Multiple Choice con puntaje parcial recibe una lista de respuestas y 
	 * asigna correctamente puntos a los jugadores según las opciones correctas que hayan respondido
	 */
	@Test
	public void calculoDePreguntaMultipleChoiceConPuntajeParcialAsignaPuntosALosJugadoresTest() {		
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");
		
		MultipleChoicePartialQuestion question = new MultipleChoicePartialQuestion("¿Que numeros son impares?");
		
		GameOption opcionUno = new GameOption("1");
		GameOption opcionTres = new GameOption("3");
		
		List<GameOption> listaOpcionesCorrectas = new ArrayList<GameOption>();
		listaOpcionesCorrectas.add(opcionUno);
		listaOpcionesCorrectas.add(opcionTres);
		
		question.setCorrectOptions(listaOpcionesCorrectas);
		
		List<GameOption> opcionesJugadorUno = new ArrayList<GameOption>();
		opcionesJugadorUno.add(opcionUno);	
		
		List<GameOption> opcionesJugadorDos = new ArrayList<GameOption>();
		opcionesJugadorDos.add(opcionUno);						
		opcionesJugadorDos.add(opcionTres);
		
		MatchResult resultJugadorUno = new MatchResult(jugadorUno, opcionesJugadorUno);
		MatchResult resultJugadorDos = new MatchResult(jugadorDos, opcionesJugadorDos);
		ScoreCalculator.calculateAndAssignPoints(resultJugadorUno, resultJugadorDos, question);
		
		assertEquals(1, jugadorUno.getScore().getValue());
		assertEquals(2, jugadorDos.getScore().getValue());
	}

}
