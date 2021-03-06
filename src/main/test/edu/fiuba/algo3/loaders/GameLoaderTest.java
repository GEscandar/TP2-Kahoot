package edu.fiuba.algo3.loaders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.model.Game;
import edu.fiuba.algo3.model.Player;

public class GameLoaderTest {
	
	@Test
	public void generarJuegoTest(){
		List<Player> lista = new ArrayList<Player>();
		lista.add(new Player(""));
		Game game = GameLoader.loadGame(lista);
		assertEquals(lista, game.getPlayers());
		assertTrue(!game.getQuestions().isEmpty());
	}

}
