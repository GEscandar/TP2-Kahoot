package edu.fiuba.algo3.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import edu.fiuba.algo3.engine.score.augmenters.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.fiuba.algo3.constants.AugmenterType;
import edu.fiuba.algo3.constants.ResourceConstants;
import edu.fiuba.algo3.engine.questions.TrueFalseQuestion;
import edu.fiuba.algo3.exceptions.QuestionsNotLoadedException;
import edu.fiuba.algo3.loaders.QuestionLoader;

public class GameTest {

    private Game game;
    private Player jugadorUno;
    private Player jugadorDos;
    private List<Question> questions;

    /*
     * Ver preguntas en archivo 'preguntas-test.json'
     * */

    @BeforeEach
    public void setUp() {

        jugadorUno = new Player("Jugador uno");
        jugadorDos = new Player("Jugador dos");

        try {
            questions = QuestionLoader.loadQuestions(ResourceConstants.QUESTIONS_TEST_PATH);
        } catch (QuestionsNotLoadedException e) {
            fail();
        }
        game = new Game(Arrays.asList(jugadorUno, jugadorDos), questions);
        game.start();
    }

    @Test
    public void pasarDeTurnoCambiaElJugadorTest() {
        assertEquals(jugadorUno, game.getCurrentPlayer());
        game.nextTurn(new ArrayList<GameOption>(), new TwoMultiplier());
        assertEquals(jugadorDos, game.getCurrentPlayer());
    }

    @Test
    public void iniciarJuegoSeleccionaPreguntaInicialTest() {
        game.start();
        assertEquals(questions.get(0), game.getCurrentQuestion());
    }

    @Test
    public void iterarTodasLasPreguntasFinalizaElJuegoTest() {
        List<Question> questions = new ArrayList<>();
        List<GameOption> listaOpciones = Mockito.mock(ArrayList.class);
        questions.add(new TrueFalseQuestion("¿1 es mayor a 2?", listaOpciones));
        questions.add(new TrueFalseQuestion("¿1 es mayor a 2?", listaOpciones));
        game.setQuestions(questions);
        game.start();
        game.nextTurn(new ArrayList<GameOption>(), new TwoMultiplier());
        game.nextTurn(new ArrayList<GameOption>(), new TwoMultiplier());
        game.nextTurn(new ArrayList<GameOption>(), new TwoMultiplier());
        game.nextTurn(new ArrayList<GameOption>(), new TwoMultiplier());
        assertTrue(game.isOver());
    }

    @Test
    public void unJugadorConMasPuntajeQueElOtroResultaGanadorTest() {
        Player jugadorUno = Mockito.mock(Player.class);
        Player jugadorDos = Mockito.mock(Player.class);
        List<GameOption> listaOpciones = Mockito.mock(ArrayList.class);
        Mockito.when(jugadorUno.getScore()).thenReturn(new Score(100));
        Mockito.when(jugadorDos.getScore()).thenReturn(new Score(50));
        List<Player> lista = new ArrayList<>();
        lista.add(jugadorUno);
        lista.add(jugadorDos);
        List<Question> questions = new ArrayList<>();
        questions.add(new TrueFalseQuestion("¿1 es mayor a 2?", listaOpciones));
        game.setQuestions(questions);
        Game game = new Game(lista, questions);
        game.start();
        game.nextTurn(new ArrayList<GameOption>(), new TwoMultiplier());
        assertEquals(jugadorUno, game.getWinner());
    }

    @Test
    public void ambosJugadoresRespondenTodoMalYLosPuntajesSonMenosUnoTest() {
        while (!game.isOver()) game.nextTurn(new ArrayList(), new NoMultiplier());

        for (Player player : game.getPlayers()) {
            Assertions.assertEquals(new Score(-1), player.getScore());
        }
    }

    @Test
    public void ambosJugadoresRespondenTodoBienYLosPuntajesSonNueveTest() {
        while (!game.isOver())
            game.nextTurn(game.getCurrentQuestion().getCorrectOptions(), new NoMultiplier());

        for (Player player : game.getPlayers()) {
            Assertions.assertEquals(new Score(9), player.getScore());
        }
    }

    @Test
    public void ambosJugadoresAgotanSuExclusividadYNoGananPuntajeEnLasPrimerasDosPreguntasTest() {

        while (!game.isOver()) {
            game.nextTurn(game.getCurrentQuestion().getCorrectOptions(), new ExclusivityMultiplier());
        }

        for (Player player : game.getPlayers()) {
            Assertions.assertEquals(new Score(6), player.getScore());
        }
    }

    @Test
    public void juegoCompletoSinMultiplicadoresTest() {

        List<List<GameOption>> respuestasJugador1 = Arrays.asList(
                Arrays.asList(new GameOption("3"), new GameOption("4")), // suma cero
                Arrays.asList(new GameOption("2"), new GameOption("3"), new GameOption("4")), // suma uno
                Arrays.asList(new GameOption("1"), new GameOption("2")), // suma cero
                Arrays.asList(), // suma cero
                Arrays.asList(new GameOption("Verdadero")), // suma uno
                Arrays.asList(new GameOption("Falso")), // resta uno
                Arrays.asList() // suma cero
        );

        List<List<GameOption>> respuestasJugador2 = Arrays.asList(
                Arrays.asList(new GameOption("2"), new GameOption("4")), // suma uno
                Arrays.asList(new GameOption("2"), new GameOption("3"), new GameOption("4")), // suma uno
                Arrays.asList(new GameOption("1"), new GameOption("2")), // suma cero
                Arrays.asList(new GameOption("1"), new GameOption("2"), new GameOption("3"), new GameOption("4")), // suma uno
                Arrays.asList(new GameOption("Verdadero")), // suma uno
                Arrays.asList(new GameOption("Falso")), // resta uno
                Arrays.asList() // suma cero
        );

        Iterator<List<GameOption>> iterador1 = respuestasJugador1.iterator();
        Iterator<List<GameOption>> iterador2 = respuestasJugador2.iterator();

        while (!game.isOver()) {
            if (game.getCurrentPlayer().equals(jugadorUno))
                game.nextTurn(iterador1.next(), new NoMultiplier());
            else
                game.nextTurn(iterador2.next(), new NoMultiplier());
        }

        Assertions.assertEquals(new Score(1), jugadorUno.getScore());
        Assertions.assertEquals(new Score(3), jugadorDos.getScore());
        Assertions.assertEquals(jugadorDos, game.getWinner());
    }

    @Test
    public void juegoCompletoConMultiplicadoresTest() {

        List<List<GameOption>> respuestasJugador1 = Arrays.asList(
                Arrays.asList(new GameOption("3"), new GameOption("4")), // suma cero
                Arrays.asList(new GameOption("2"), new GameOption("3"), new GameOption("4")), // suma uno -> suma dos
                Arrays.asList(new GameOption("1"), new GameOption("2")), // suma cero
                Arrays.asList(), // suma cero
                Arrays.asList(new GameOption("Verdadero")), // suma uno -> suma uno porque se queda sin usos del multiplicador
                Arrays.asList(new GameOption("Falso")), // resta uno -> resta tres
                Arrays.asList() // suma cero
        );

        List<ScoreAugmenter> multiplicadoresJugador1 = Arrays.asList(
                new NoMultiplier(),
                new TwoMultiplier(),
                new NoMultiplier(),
                new ExclusivityMultiplier(),
                new TwoMultiplier(),
                new ThreeMultiplier(),
				new NoMultiplier()
        );

        // 0 + 2 + 0 + 0 + 1 - 3 = 1

        List<List<GameOption>> respuestasJugador2 = Arrays.asList(
                Arrays.asList(new GameOption("2"), new GameOption("4")), // suma uno -> suma dos
                Arrays.asList(new GameOption("2"), new GameOption("3"), new GameOption("4")), // suma uno
                Arrays.asList(new GameOption("1"), new GameOption("2")), // suma cero
                Arrays.asList(new GameOption("1"), new GameOption("2"), new GameOption("3"), new GameOption("4")), // suma uno -> suma cuatro
                Arrays.asList(new GameOption("Verdadero")), // suma uno -> suma tres
                Arrays.asList(new GameOption("Falso")), // resta uno -> resta dos
                Arrays.asList() // suma cero
        );

        List<ScoreAugmenter> multiplicadoresJugador2 = Arrays.asList(
                new ExclusivityMultiplier(),
                new NoMultiplier(),
                new NoMultiplier(),
                new ExclusivityMultiplier(),
                new ThreeMultiplier(),
                new TwoMultiplier(),
                new NoMultiplier());

        // 2 + 1 + 0 + 4 + 3 - 2 = 8

        Iterator<List<GameOption>> iterador1 = respuestasJugador1.iterator();
        Iterator<List<GameOption>> iterador2 = respuestasJugador2.iterator();
        Iterator<ScoreAugmenter> multiplicadores1 = multiplicadoresJugador1.iterator();
        Iterator<ScoreAugmenter> multiplicadores2 = multiplicadoresJugador2.iterator();

        while (!game.isOver()) {
            if (game.getCurrentPlayer().equals(jugadorUno))
                game.nextTurn(iterador1.next(), multiplicadores1.next());
            else
                game.nextTurn(iterador2.next(), multiplicadores2.next());
        }

        Assertions.assertEquals(new Score(0), jugadorUno.getScore());
        Assertions.assertEquals(new Score(8), jugadorDos.getScore());
        Assertions.assertEquals(jugadorDos, game.getWinner());
    }

    @Test
    public void sePasaDeTurnoHastaElFinalYSeObtieneElNumeroCorrectoTest() {

        while (!game.isOver()) {
            game.nextTurn(game.getCurrentQuestion().getCorrectOptions(), new ExclusivityMultiplier());
        }
        Assertions.assertEquals(game.getQuestions().size() * game.getPlayers().size(), game.getTurnCount());
    }

    @Test
    public void sePuedeSaberSiUnAugmenterExistenteEsValidoPorSuNombreTest() {
        boolean augmenterValido = game.isAugmenterAvailable(AugmenterType.EXCLUSIVITY.toString());
        Assertions.assertTrue(augmenterValido);
    }

    @Test
    public void sePuedeSaberSiUnAugmenterAgotadoEsValidoPorSuNombreYNoDebeSerloTest() {
        game.getCurrentPlayer().getAugmenter(new ThreeMultiplier());
        boolean augmenterValido = game.isAugmenterAvailable(AugmenterType.MULTIPLY_PER_THREE.toString());
        Assertions.assertFalse(augmenterValido);
    }
}
