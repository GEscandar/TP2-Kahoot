package edu.fiuba.algo3.engine.score;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.engine.score.augmenters.ExclusivityMultiplier;
import edu.fiuba.algo3.engine.score.augmenters.ThreeMultiplier;
import edu.fiuba.algo3.model.Score;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.engine.questions.TrueFalseQuestion;
import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.MatchResult;
import edu.fiuba.algo3.model.Player;

public class ScoreCalculatorTest {
	
	@Test
	public void calculoDePreguntaTrueFalseAsignaPuntosALosJugadoresTest() {		
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");

		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");

		listaOpciones.add(opcionTrue);
		listaOpciones.add(opcionFalse);
		
		TrueFalseQuestion question = new TrueFalseQuestion("¿1 es mayor que 2?", listaOpciones);
		
		question.setCorrectOption(opcionFalse);

		List<MatchResult> resultadosRonda = new ArrayList<MatchResult>();
		
		Score matchScorePlayerOne = question.calculatePoints(opcionTrue);
		Score matchScorePlayerTwo = question.calculatePoints(opcionFalse);
		
		resultadosRonda.add(new MatchResult(jugadorUno, matchScorePlayerOne));
		resultadosRonda.add(new MatchResult(jugadorDos, matchScorePlayerTwo));
		
		ScoreCalculator.calculateRoundEndResults(resultadosRonda);
		assertEquals(new Score(0), jugadorUno.getScore());
		assertEquals(new Score(1), jugadorDos.getScore());
	}
	
	@Test
	public void calculoDePreguntaTrueFalseConMultiplicadorPorTresNoFuncionaSiNoTienePenalidadTest() {		
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");

		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");

		listaOpciones.add(opcionTrue);
		listaOpciones.add(opcionFalse);
		
		TrueFalseQuestion question = new TrueFalseQuestion("¿1 es mayor que 2?", listaOpciones);

		question.setCorrectOption(opcionFalse);

		List<MatchResult> resultadosRonda = new ArrayList<MatchResult>();
		
		Score matchScorePlayerOne = question.calculatePoints(opcionTrue);
		Score matchScorePlayerTwo = question.calculatePoints(opcionFalse, new ThreeMultiplier());
		
		resultadosRonda.add(new MatchResult(jugadorUno, matchScorePlayerOne));
		resultadosRonda.add(new MatchResult(jugadorDos, matchScorePlayerTwo));
		
		ScoreCalculator.calculateRoundEndResults(resultadosRonda);
		
		assertEquals(new Score(0), jugadorUno.getScore());
		assertEquals(new Score(1), jugadorDos.getScore());
	}
	
	@Test
	public void calculoDePreguntaTrueFalseAsignaPuntosALosJugadoresConExclusividadTest() {		
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");

		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");

		listaOpciones.add(opcionTrue);
		listaOpciones.add(opcionFalse);
		
		TrueFalseQuestion question = new TrueFalseQuestion("¿1 es mayor que 2?", listaOpciones);

		question.setCorrectOption(opcionFalse);
		
		List<MatchResult> resultadosRonda = new ArrayList<MatchResult>();
		
		Score matchScorePlayerOne = question.calculatePoints(opcionTrue);
		Score matchScorePlayerTwo = question.calculatePoints(opcionFalse, new ExclusivityMultiplier());
		
		resultadosRonda.add(new MatchResult(jugadorUno, matchScorePlayerOne));
		resultadosRonda.add(new MatchResult(jugadorDos, matchScorePlayerTwo));
		
		ScoreCalculator.calculateRoundEndResults(resultadosRonda);

		assertEquals(new Score(0), jugadorUno.getScore());
		assertEquals(new Score(2), jugadorDos.getScore());
	}
	
	@Test
	public void calculoDePreguntaTrueFalseAsignaPuntosALosJugadoresConExclusividadYAmbosContestanBienTest() {		
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");

		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");

		listaOpciones.add(opcionTrue);
		listaOpciones.add(opcionFalse);
		
		TrueFalseQuestion question = new TrueFalseQuestion("¿1 es mayor que 2?", listaOpciones);

		question.setCorrectOption(opcionFalse);
		
		List<MatchResult> resultadosRonda = new ArrayList<MatchResult>();
		
		Score matchScorePlayerOne = (question.calculatePoints(opcionFalse, new ExclusivityMultiplier()));
		Score matchScorePlayerTwo = question.calculatePoints(opcionFalse, new ExclusivityMultiplier());
		
		resultadosRonda.add(new MatchResult(jugadorUno, matchScorePlayerOne));
		resultadosRonda.add(new MatchResult(jugadorDos, matchScorePlayerTwo));
		
		ScoreCalculator.calculateRoundEndResults(resultadosRonda);


		assertEquals(new Score(0), jugadorUno.getScore());
		assertEquals(new Score(0), jugadorDos.getScore());
	}
	
	@Test
	public void calculoDePreguntaTrueFalseAsignaPuntosALosJugadoresConExclusividadYCuadriplicaElPuntajeTest() {		
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");

		List<GameOption> listaOpciones = new ArrayList<GameOption>();

		GameOption opcionTrue = new GameOption("True");
		GameOption opcionFalse = new GameOption("False");

		listaOpciones.add(opcionTrue);
		listaOpciones.add(opcionFalse);
		
		TrueFalseQuestion question = new TrueFalseQuestion("¿1 es mayor que 2?", listaOpciones);
		
		question.setCorrectOption(opcionFalse);
		
		List<MatchResult> resultadosRonda = new ArrayList<MatchResult>();
		
		Score matchScorePlayerOne = question.calculatePoints(opcionTrue, new ExclusivityMultiplier());
		Score matchScorePlayerTwo = question.calculatePoints(opcionFalse, new ExclusivityMultiplier());
		
		resultadosRonda.add(new MatchResult(jugadorUno, matchScorePlayerOne));
		resultadosRonda.add(new MatchResult(jugadorDos, matchScorePlayerTwo));
		
		ScoreCalculator.calculateRoundEndResults(resultadosRonda);

		assertEquals(new Score(0), jugadorUno.getScore());
		assertEquals(new Score(4), jugadorDos.getScore());
	}

}
