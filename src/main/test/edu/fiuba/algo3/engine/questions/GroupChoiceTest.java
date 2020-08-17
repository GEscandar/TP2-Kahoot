package edu.fiuba.algo3.engine.questions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.model.GameOption;
import edu.fiuba.algo3.model.OptionGroup;

public class GroupChoiceTest {
	
	@Test
	public void opcionesCorrectasSumaUnPunto() {
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
		
		listaOpcionesCorrectas.add(grupoPares);
		listaOpcionesCorrectas.add(grupoImpares);
		
		question.setCorrectOptions(listaOpcionesCorrectas);
		
		List<GameOption> listaOpcionesElegidas = new ArrayList<GameOption>();				
		
		OptionGroup grupoParesElegido = new OptionGroup("Pares");
		OptionGroup grupoImparesElegido = new OptionGroup("Impares");
		
		grupoParesElegido.addOptions(opcionDos, opcionCuatro);
		grupoImparesElegido.addOptions(opcionUno, opcionTres);
		
		listaOpcionesElegidas.add(grupoParesElegido);
		listaOpcionesElegidas.add(grupoImparesElegido);
		
		assertEquals(1, question.calculatePoints(listaOpcionesElegidas));
	}

	@Test
	public void gruposDevueltosPorLaPreguntaSonIgualesALosCreados() {
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
		listaOpcionesCorrectas.add(grupoPares);
		listaOpcionesCorrectas.add(grupoImpares);
		
		question.setCorrectOptions(listaOpcionesCorrectas);
		
		assertTrue(question.getOptionGroupList().contains(grupoPares));
		assertTrue(question.getOptionGroupList().contains(grupoImpares));		
	}
	
	@Test
	public void opcionesIncorrectasDevuelveCero() {
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
		listaOpcionesCorrectas.add(grupoPares);
		listaOpcionesCorrectas.add(grupoImpares);
		
		question.setCorrectOptions(listaOpcionesCorrectas);
		
		List<GameOption> listaOpcionesElegidas = new ArrayList<GameOption>();				
		
		OptionGroup grupoParesElegido = new OptionGroup("Pares");
		OptionGroup grupoImparesElegido = new OptionGroup("Impares");
		
		grupoParesElegido.addOptions(opcionCuatro);
		grupoImparesElegido.addOptions(opcionUno, opcionDos, opcionTres);
		
		listaOpcionesElegidas.add(grupoParesElegido);
		listaOpcionesElegidas.add(grupoImparesElegido);
		
		assertEquals(0, question.calculatePoints(listaOpcionesElegidas));
	}

}
