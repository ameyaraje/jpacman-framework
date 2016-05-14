import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;

public class Scenario4Test {
	
	Launcher launcher;
	Game game;
	Player player;
	
	@Before
	public void setup()
	{
		launcher = new Launcher();
		launcher.launch();
		launcher.getGame();
		game =  launcher.getGame();
		game.start();
		player = game.getPlayers().get(0);
	}

	@Test
	public void scene1()
	{
		assertTrue(game.isInProgress());
		assertTrue(player.isAlive());
		game.stop();
		assertFalse(game.isInProgress());
	}
	
	@Test
	public void scene2()
	{
		game.start();
		assertTrue(game.isInProgress());
		assertTrue(player.isAlive());
	}
	
	@After
	public void tearDown() throws Exception {
		launcher.dispose();
	}

}
