package nl.tudelft.jpacman.board;
import static org.junit.Assert.*;

import java.io.IOException;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.ghost.Ghost;
import nl.tudelft.jpacman.npc.ghost.Navigation;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.Pellet;



public class Scenario3Test {
	
	Launcher launcher;
	Game game;
	Player player;
	Board board;
	MapParser parser;
	Level level;
	
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
	public void scene1() throws IOException, InterruptedException
	{
		level = game.getLevel();
		board = level.getBoard();
		Square square = board.squareAt(1,1);
		
		//Find the nearest ghost
		Ghost ghost = (Ghost)Navigation.findNearest(Ghost.class, square);
		square = ghost.getSquare();
		
		//Wait for a tick and then move
		Thread.sleep(1000L);
		assertNotEquals(square,ghost.getSquare());
		
	}
	
	@Test
	public void scene2()
	{
		//Added in this package as Square is abstract and can be instantiated
		Square square = new BasicSquare();
		square.put(mock(Pellet.class));
		
		//Occupant 0 is a pellet
		Unit prev =square.getOccupants().get(0);
		square.put(mock(Ghost.class));
		
		//Here occupant 1 is a ghost
		Unit next = square.getOccupants().get(1);
		assertFalse(prev.equals(next));
	}
	
	@Test
	public void scene3()
	{
		//Added in this package as Square is abstract and can be instantiated
		Square square = new BasicSquare();
		square.put(mock(Pellet.class));
		Ghost ghost = mock(Ghost.class);
		square.put(ghost);
		
		//Here occupant 1 is a ghost
		Unit prev =square.getOccupants().get(1);
		square.remove(ghost);
		
		//Occupant 0 is a pellet
		Unit next = square.getOccupants().get(0);
		assertFalse(prev.equals(next));
	}

	
	@Test
	public void scene4() throws InterruptedException
	{
		game.start();
		assertTrue(game.isInProgress());
		assertTrue(player.isAlive());
		Thread.sleep(5000L);
		assertFalse(player.isAlive());
		game.stop();
	}
	
	
	@After
	public void tearDown() throws Exception {
		launcher.dispose();
	}
}
