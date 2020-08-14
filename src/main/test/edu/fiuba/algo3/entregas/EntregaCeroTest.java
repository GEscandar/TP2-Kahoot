package edu.fiuba.algo3.entregas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.fiuba.algo3.model.Score;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.engine.questions.TrueFalseQuestion;
import edu.fiuba.algo3.model.Game;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.Player;
import edu.fiuba.algo3.model.Question;

import java.util.ArrayList;
import java.util.List;

public class EntregaCeroTest {

	/***
	 * Una Pregunta de Verdadero/Falso clásico puede crearse indicándole cual es la respuesta correcta
	 */
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
	
	/***
	 * Una Pregunta de Verdadero/Falso clásico recibe una lista de respuestas y asigna correctamente puntos a los jugadores que respondieron correctamente
	 */
	@Test
	public void calculoDePreguntaTrueFalseAsignaPuntosALosJugadoresTest() {				
		List<Player> jugadores = new ArrayList<>();
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");
		jugadores.add(jugadorUno);
		jugadores.add(jugadorDos);
		
		

		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");
		
		listaOpciones.add(opcionTrue);
		listaOpciones.add(opcionFalse);

		List<Question> questions = new ArrayList<>();		
		TrueFalseQuestion question = new TrueFalseQuestion("¿1 es mayor que 2?", listaOpciones);		
		
		question.setCorrectOption(opcionFalse);
		questions.add(question);
		
		Game game = new Game(jugadores, questions);		
		game.start();
		
		game.nextTurn(opcionTrue);
		game.nextTurn(opcionFalse);		

		assertEquals(new Score(0), jugadorUno.getScore());
		assertEquals(new Score(1), jugadorDos.getScore());
	}
}
