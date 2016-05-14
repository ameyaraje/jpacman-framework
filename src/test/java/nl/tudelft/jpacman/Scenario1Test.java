

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.*;


import org.junit.Test;

public class Scenario1Test {
	
	private Launcher launcher;
	
	@Before
	public void init()
	{
		launcher  = new Launcher();
		launcher.launch();
	}
	
	@Test
	public void gameStarted()
	{
		Game game = launcher.getGame();
		game.start();
		assertTrue(game.isInProgress());
	}
	
	@After
	public void tearDown() throws Exception {
		launcher.dispose();
	}

}
