package edu.fiuba.algo3.entregas;

import edu.fiuba.algo3.constants.AugmenterType;
import edu.fiuba.algo3.engine.questions.*;
import edu.fiuba.algo3.engine.score.augmenters.ThreeMultiplier;
import edu.fiuba.algo3.engine.score.augmenters.TwoMultiplier;
import edu.fiuba.algo3.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntregaDosTest {

    /***
     * Una Pregunta de Múltiple Choice con penalidad puede crearse indicándole cuales son las opciones correctas
     */
    @Test
    public void opcionesCorrectasEnMCconPenalidadSumaUnPuntoCadaUna() {

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

        assertEquals(2, question.calculatePoints(listaOpcionesElegidas));
    }

    /***
     * Una Pregunta de Group Choice puede crearse indicándole cuales son las opciones correctas
     */
    @Test
    public void opcionesCorrectasGroupChoiceSumaUnPunto() {

        List<GameOption> listaOpciones = new ArrayList<GameOption>();

        OptionGroup grupoPares = new OptionGroup("Pares");
        OptionGroup grupoImpares = new OptionGroup("Impares");

        GameOption opcionUno = new GameOption("1");
        GameOption opcionDos = new GameOption("2");
        GameOption opcionTres = new GameOption("3");
        GameOption opcionCuatro = new GameOption("4");

        grupoPares.addOptions(opcionDos, opcionCuatro);        
        grupoImpares.addOptions(opcionUno, opcionTres);
        
        listaOpciones.add(opcionUno);
        listaOpciones.add(opcionDos);
        listaOpciones.add(opcionTres);
        listaOpciones.add(opcionCuatro);

        GroupChoiceQuestion question = new GroupChoiceQuestion("Agrupar las opciones según corresponda.", listaOpciones, Arrays.asList(grupoPares, grupoImpares));

        List<GameOption> listaOpcionesCorrectas = new ArrayList<GameOption>();

        listaOpcionesCorrectas.add(opcionUno);
        listaOpcionesCorrectas.add(opcionDos);
        listaOpcionesCorrectas.add(opcionTres);
        listaOpcionesCorrectas.add(opcionCuatro);

        question.setCorrectOptions(listaOpcionesCorrectas);

        List<GameOption> listaOpcionesElegidas = new ArrayList<GameOption>();

        GameOption opcionElegidaUno = new GameOption("1");
        GameOption opcionElegidaDos = new GameOption("2");
        GameOption opcionElegidaTres = new GameOption("3");
        GameOption opcionElegidaCuatro = new GameOption("4");

        OptionGroup grupoParesElegidos = new OptionGroup("Pares");
        OptionGroup grupoImparesElegidos = new OptionGroup("Impares");
        
        grupoParesElegidos.addOptions(opcionElegidaDos, opcionElegidaCuatro);
        grupoImparesElegidos.addOptions(opcionElegidaUno, opcionElegidaTres);

        listaOpcionesElegidas.add(opcionElegidaUno);
        listaOpcionesElegidas.add(opcionElegidaDos);
        listaOpcionesElegidas.add(opcionElegidaTres);
        listaOpcionesElegidas.add(opcionElegidaCuatro);

        assertEquals(1, question.calculatePoints(listaOpcionesElegidas));
    }

    /***
     * Una Pregunta de Ordered Choice puede crearse indicándole cuales son las opciones correctas
     */
    @Test
    public void opcionesEnOrdenCorrectoSumaUnPunto() {
        GameOption opcionUno = new GameOption("1");
        GameOption opcionDos = new GameOption("2");
        GameOption opcionTres = new GameOption("3");

        List<GameOption> listaOpciones = new ArrayList<GameOption>();
        listaOpciones.add(opcionUno);
        listaOpciones.add(opcionDos);
        listaOpciones.add(opcionTres);

        OrderedChoiceQuestion question = new OrderedChoiceQuestion("Ordenar los numeros de menor a mayor", listaOpciones);

        List<GameOption> listaOpcionesCorrectas = new ArrayList<GameOption>();
        listaOpcionesCorrectas.add(opcionUno);
        listaOpcionesCorrectas.add(opcionDos);
        listaOpcionesCorrectas.add(opcionTres);

        question.setCorrectOptions(listaOpcionesCorrectas);

        List<GameOption> listaOpcionesElegidas = new ArrayList<GameOption>();
        listaOpcionesElegidas.add(opcionUno);
        listaOpcionesElegidas.add(opcionDos);
        listaOpcionesElegidas.add(opcionTres);

        assertEquals(1, question.calculatePoints(listaOpcionesElegidas));
    }

    /***
     * Una Pregunta de Multiple Choice con penalidad recibe una lista de respuestas y asigna
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
     * Una Pregunta de Group Choice recibe una lista de respuestas y asigna
     * correctamente puntos a los jugadores que respondieron en el orden correcto las opciones.
     */
    @Test
    public void calculoDePreguntaGroupChoiceAsignaPuntosALosJugadoresTest() {
    	List<Player> jugadores = new ArrayList<>();
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");
		jugadores.add(jugadorUno);
		jugadores.add(jugadorDos);

        List<GameOption> listaOpciones = new ArrayList<GameOption>();

        OptionGroup grupoPares = new OptionGroup("Pares");
        OptionGroup grupoImpares = new OptionGroup("Impares");

        GameOption opcionUno = new GameOption("1");
        GameOption opcionDos = new GameOption("2");
        GameOption opcionTres = new GameOption("3");
        GameOption opcionCuatro = new GameOption("4");
        
        grupoPares.addOptions(opcionDos, opcionCuatro);
        grupoImpares.addOptions(opcionUno, opcionTres);

        listaOpciones.add(opcionUno);
        listaOpciones.add(opcionDos);
        listaOpciones.add(opcionTres);
        listaOpciones.add(opcionCuatro);

        GroupChoiceQuestion question = new GroupChoiceQuestion("Agrupar las opciones según corresponda.", listaOpciones, Arrays.asList(grupoPares,grupoImpares));

        List<GameOption> listaOpcionesCorrectas = new ArrayList<GameOption>();

        listaOpcionesCorrectas.add(grupoPares);
        listaOpcionesCorrectas.add(grupoImpares);
        
        question.setCorrectOptions(listaOpcionesCorrectas);

        //Opciones elegidas por Jugador 1
        List<GameOption> opcionesJugadorUno = new ArrayList<GameOption>();
        
        OptionGroup grupoParesJug1 = new OptionGroup("Pares");
        OptionGroup grupoImparesJug1 = new OptionGroup("Impares");
        
        grupoParesJug1.addOptions(opcionDos, opcionCuatro);
        grupoImparesJug1.addOptions(opcionUno, opcionTres);

        opcionesJugadorUno.add(grupoParesJug1);
        opcionesJugadorUno.add(grupoImparesJug1);

        //Opciones elegidas por Jugador 2
        List<GameOption> opcionesJugadorDos = new ArrayList<GameOption>();
        
        OptionGroup grupoParesJug2 = new OptionGroup("Pares");
        OptionGroup grupoImparesJug2 = new OptionGroup("Impares");
        
        grupoParesJug2.addOptions(opcionDos, opcionTres, opcionCuatro);
        grupoImparesJug2.addOptions(opcionUno);

        opcionesJugadorDos.add(grupoParesJug2);
        opcionesJugadorDos.add(grupoImparesJug2);
		
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
     * Una Pregunta de Ordered Choice recibe una lista de respuestas y asigna
     * correctamente puntos a los jugadores que respondieron en el orden correcto las opciones.
     */
    @Test
    public void calculoDePreguntaOrderedChoiceAsignaPuntosALosJugadoresTest() {
    	List<Player> jugadores = new ArrayList<>();
		Player jugadorUno = new Player("JugadorUno");
		Player jugadorDos = new Player("JugadorDos");
		jugadores.add(jugadorUno);
		jugadores.add(jugadorDos);

        GameOption opcionUno = new GameOption("1");
        GameOption opcionDos = new GameOption("2");
        GameOption opcionTres = new GameOption("3");

        List<GameOption> listaOpciones = new ArrayList<GameOption>();
        listaOpciones.add(opcionUno);
        listaOpciones.add(opcionDos);
        listaOpciones.add(opcionTres);

        OrderedChoiceQuestion question = new OrderedChoiceQuestion("Ordenar los numeros de menor a mayor", listaOpciones);

        List<GameOption> listaOpcionesCorrectas = new ArrayList<GameOption>();
        listaOpcionesCorrectas.add(opcionUno);
        listaOpcionesCorrectas.add(opcionDos);
        listaOpcionesCorrectas.add(opcionTres);

        question.setCorrectOptions(listaOpcionesCorrectas);

        List<GameOption> opcionesJugadorUno = new ArrayList<GameOption>();
        opcionesJugadorUno.add(opcionUno);
        opcionesJugadorUno.add(opcionDos);
        opcionesJugadorUno.add(opcionTres);

        List<GameOption> opcionesJugadorDos = new ArrayList<GameOption>();
        opcionesJugadorDos.add(opcionUno);
        opcionesJugadorDos.add(opcionTres);
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
     * Una Pregunta de Verdadero/Falso con Penalidad es contestada correctamente por jugador que utiliza
     * un multiplicador x2 y se asigna correctamente 2 veces el valor de la respuesta correcta.
     */

    @Test
    public void calculoDePreguntaTrueFalseAsignaPuntosAlJugadorConMultiplicadorx2Test() {
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
		game.nextTurn(opcionFalse, new TwoMultiplier());

        assertEquals(new Score(-1), jugadorUno.getScore());
        assertEquals(new Score(2), jugadorDos.getScore());
    }

    /***
     * Una Pregunta de Verdadero/Falso clasico es contestada correctamente por jugador que utiliza
     * un multiplicador x3 y se asigna correctamente 3 veces el valor de la respuesta correcta.
     */

    @Test
    public void calculoDePreguntaTrueFalseAsignaPuntosAlJugadorConMultiplicadorx3Test() {
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
		game.nextTurn(opcionFalse, new ThreeMultiplier());

        assertEquals(new Score(-1), jugadorUno.getScore());
        assertEquals(new Score(3), jugadorDos.getScore());
    }

    /***
     * Una Pregunta de Verdadero/Falso con Penalidad es contestada incorrectamnte por jugador
     * que utiliza un multiplicador x2 y le resta 2 veces el valor.
     */

    @Test
    public void calculoDePreguntaTrueFalseRestaPuntosAlJugadorConMultiplicadorx2Test() {
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
		
		game.nextTurn(opcionFalse);
		game.nextTurn(opcionTrue, new TwoMultiplier());

        assertEquals(new Score(1), jugadorUno.getScore());
        assertEquals(new Score(-2), jugadorDos.getScore());
    }
}
