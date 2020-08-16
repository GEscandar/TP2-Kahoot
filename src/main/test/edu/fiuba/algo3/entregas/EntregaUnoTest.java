package edu.fiuba.algo3.entregas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.engine.questions.MultipleChoicePartialQuestion;
import edu.fiuba.algo3.engine.questions.MultipleChoiceQuestion;
import edu.fiuba.algo3.engine.questions.MultipleChoiceWithPenaltyQuestion;
import edu.fiuba.algo3.engine.questions.TrueFalseWithPenaltyQuestion;
import edu.fiuba.algo3.engine.score.augmenters.NoMultiplier;
import edu.fiuba.algo3.model.Game;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.Player;
import edu.fiuba.algo3.model.Question;
import edu.fiuba.algo3.model.Score;

public class EntregaUnoTest {
	
	/***
	 * Una Pregunta de Verdadero/Falso con penalidad puede crearse indicándole cual es la respuesta correcta
	 */
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
	
	/***
	 * Una Pregunta de Múltiple Choice clásico puede crearse indicándole cuales son las opciones correctas
	 */
	@Test
	public void opcionesCorrectasSumaUnPunto() {
		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionUno = new GameOption("1");
		GameOption opcionDos = new GameOption("2");
		GameOption opcionTres = new GameOption("3");
		GameOption opcionCuatro = new GameOption("4");

		listaOpciones.add(opcionUno);
		listaOpciones.add(opcionDos);
		listaOpciones.add(opcionTres);
		listaOpciones.add(opcionCuatro);

		MultipleChoiceQuestion question = new MultipleChoiceQuestion("¿Que numeros son impares?", listaOpciones);
		
		List<GameOption> listaOpcionesCorrectas = new ArrayList<GameOption>();
		listaOpcionesCorrectas.add(opcionUno);
		listaOpcionesCorrectas.add(opcionTres);
		
		question.setCorrectOptions(listaOpcionesCorrectas);
		
		List<GameOption> listaOpcionesElegidas = new ArrayList<GameOption>();
		listaOpcionesElegidas.add(opcionUno);						
		listaOpcionesElegidas.add(opcionTres);
		
		assertEquals(new Score(1), question.calculatePoints(listaOpcionesElegidas, new NoMultiplier()));
	}
	
	/***
	 * Una Pregunta de Múltiple Choice con puntaje parcial puede crearse indicándole cuales son las opciones correctas
	 */
	@Test
	public void opcionesCorrectasSumaUnPuntoCadaUna() {
		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionUno = new GameOption("1");
		GameOption opcionDos = new GameOption("2");
		GameOption opcionTres = new GameOption("3");
		GameOption opcionCuatro = new GameOption("4");

		listaOpciones.add(opcionUno);
		listaOpciones.add(opcionDos);
		listaOpciones.add(opcionTres);
		listaOpciones.add(opcionCuatro);

		MultipleChoiceWithPenaltyQuestion question = new MultipleChoiceWithPenaltyQuestion("¿Que numeros son impares?", listaOpciones);
		
		List<GameOption> listaOpcionesCorrectas = new ArrayList<GameOption>();
		listaOpcionesCorrectas.add(opcionUno);
		listaOpcionesCorrectas.add(opcionTres);
		
		question.setCorrectOptions(listaOpcionesCorrectas);
		
		List<GameOption> listaOpcionesElegidas = new ArrayList<GameOption>();
		listaOpcionesElegidas.add(opcionUno);	
		listaOpcionesElegidas.add(opcionTres);
		
		assertEquals(new Score(2), question.calculatePoints(listaOpcionesElegidas, new NoMultiplier()));
	}
	
	/***
	 * Una Pregunta de Verdadero/Falso con penalidad recibe una lista de respuestas y asigna correctamente puntos a 
	 * los jugadores que respondieron correctamente, y resta correctamente puntos a los jugadores que respondieron en forma incorrecta
	 */
	@Test
	public void calculoDePreguntaTrueFalseConPenalidadAsignaPuntosALosJugadoresTest() {		
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

		TrueFalseWithPenaltyQuestion question = new TrueFalseWithPenaltyQuestion("¿1 es mayor que 2?", listaOpciones);
		
		question.setCorrectOption(opcionFalse);
		
		List<Question> questions = new ArrayList<>();	
        questions.add(question);
		
        Game game = new Game(jugadores, questions);		
		
		game.start();
		
		game.nextTurn(opcionTrue);
		game.nextTurn(opcionFalse);	

		assertEquals(new Score(-1), jugadorUno.getScore());
		assertEquals(new Score(1), jugadorDos.getScore());
	}
	
	/***
	 * Una Pregunta de Multiple Choice clasico recibe una lista de respuestas y asigna 
	 * correctamente puntos a los jugadores que respondieron correctamente
	 */
	@Test
	public void calculoDePreguntaMultipleChoiceAsignaPuntosALosJugadoresTest() {		
		List<Player> jugadores = new ArrayList<>();
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");
		jugadores.add(jugadorUno);
		jugadores.add(jugadorDos);
		
		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionUno = new GameOption("1");
		GameOption opcionDos = new GameOption("2");
		GameOption opcionTres = new GameOption("3");
		GameOption opcionCuatro = new GameOption("4");

		listaOpciones.add(opcionUno);
		listaOpciones.add(opcionDos);
		listaOpciones.add(opcionTres);
		listaOpciones.add(opcionCuatro);

		MultipleChoiceQuestion question = new MultipleChoiceQuestion("¿Que numeros son impares?", listaOpciones);

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
		
		List<Question> questions = new ArrayList<>();	
        questions.add(question);
		
        Game game = new Game(jugadores, questions);		
		
		game.start();
		
		game.nextTurn(opcionesJugadorUno);
		game.nextTurn(opcionesJugadorDos);	
		
		assertEquals(new Score(1), jugadorUno.getScore());
		assertEquals(new Score(0), jugadorDos.getScore());
	}
	
	/***
	 * Una Pregunta de Multiple Choice con puntaje parcial recibe una lista de respuestas y 
	 * asigna correctamente puntos a los jugadores según las opciones correctas que hayan respondido
	 */
	@Test
	public void calculoDePreguntaMultipleChoiceConPuntajeParcialAsignaPuntosALosJugadoresTest() {		
		List<Player> jugadores = new ArrayList<>();
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");
		jugadores.add(jugadorUno);
		jugadores.add(jugadorDos);

		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionUno = new GameOption("1");
		GameOption opcionDos = new GameOption("2");
		GameOption opcionTres = new GameOption("3");
		GameOption opcionCuatro = new GameOption("4");

		listaOpciones.add(opcionUno);
		listaOpciones.add(opcionDos);
		listaOpciones.add(opcionTres);
		listaOpciones.add(opcionCuatro);
		
		MultipleChoicePartialQuestion question = new MultipleChoicePartialQuestion("¿Que numeros son impares?", listaOpciones);

		List<GameOption> listaOpcionesCorrectas = new ArrayList<GameOption>();
		listaOpcionesCorrectas.add(opcionUno);
		listaOpcionesCorrectas.add(opcionTres);
		
		question.setCorrectOptions(listaOpcionesCorrectas);
		
		List<GameOption> opcionesJugadorUno = new ArrayList<GameOption>();
		opcionesJugadorUno.add(opcionUno);	
		
		List<GameOption> opcionesJugadorDos = new ArrayList<GameOption>();
		opcionesJugadorDos.add(opcionUno);						
		opcionesJugadorDos.add(opcionTres);

		List<Question> questions = new ArrayList<>();	
        questions.add(question);
		
        Game game = new Game(jugadores, questions);		
		
		game.start();
		
		game.nextTurn(opcionesJugadorUno);
		game.nextTurn(opcionesJugadorDos);	
		
		assertEquals(new Score(1), jugadorUno.getScore());
		assertEquals(new Score(2), jugadorDos.getScore());
	}

}
