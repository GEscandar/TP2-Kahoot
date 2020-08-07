package edu.fiuba.algo3.engine.questions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.model.GameOption;

public class MultipleChoicePenaltyTest {
	
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
		
		assertEquals(2, question.calculatePoints(listaOpcionesElegidas));
	}
	
	@Test
	public void opcionIncorrectaRestaUnPunto() {
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
		listaOpcionesElegidas.add(opcionCuatro);
		
		assertEquals(-1, question.calculatePoints(listaOpcionesElegidas));
	}

}
