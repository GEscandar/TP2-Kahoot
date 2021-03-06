package edu.fiuba.algo3.engine.questions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.model.GameOption;

public class OrderedChoiceTest {

	
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
	
	@Test
	public void opcionesEnOrdenIncorrectoDevuelveCero() {
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
		listaOpcionesElegidas.add(opcionTres);
		listaOpcionesElegidas.add(opcionDos);
		
		assertEquals(0, question.calculatePoints(listaOpcionesElegidas));
	}
}
