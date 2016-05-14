import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;

public class Scenario2Test {
	
	Launcher launcher;
	Game game;
	Player player;
	Board board;
	Level level;
	
	public static void move1(Game game, Direction dir, int numSteps) {
        Player player = game.getPlayers().get(0);
        for (int i = 0; i < numSteps; i++) {
            game.move(player, dir);
        }
	}

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
		//player.setDirection(Direction.EAST);
		//Move the player east and get score
		game.move(player, Direction.EAST);
		assertEquals(10, player.getScore());
		
		//Move the player to the opposite direction and again to the same location
		game.move(player, Direction.WEST);
		game.move(player, Direction.EAST);
		
		//Points are same means pellet consumed
		int score = player.getScore();
		assertEquals(10,score);
	}
	
	@Test
	public void scene2()
	{
		//Move in one direction and get score
		game.move(player, Direction.WEST);
		assertEquals(10, player.getScore());
		
		//Move to the previous location and check score 
		game.move(player, Direction.EAST);
		assertEquals(10, player.getScore());
	}
	
	@Test
	public void scene3()
	{
		//Game hasn't started and you move to the ghost (I traced a path by counting manually)
		move1(game, Direction.EAST, 6);
		move1(game, Direction.NORTH, 6);
		move1(game, Direction.WEST, 2);
		move1(game, Direction.NORTH, 2);
		move1(game, Direction.WEST, 3);
		
		//Player is alive and moves towards a ghost
		assertTrue(player.isAlive());
		move1(game, Direction.WEST,1);
		
		//Player dies and game ends
		assertFalse(player.isAlive());
		game.stop();
	}
	
	@Test
	public void scene4()
	{
		assertTrue(player.isAlive());
		assertTrue(game.isInProgress());
		int score = player.getScore();
		game.move(player, Direction.NORTH);
		assertEquals(score, player.getScore());
	}
	
	
	@Test
	public void scene5() throws InterruptedException
	{
		
		assertTrue(player.isAlive());
		int h = board.getHeight();
		int w = board.getWidth();
		
		for(int i=0;i<h; i++)
		{
			for(int j=0; j<w; j++)
			{
				Square square = board.squareAt(i, j);
				if(!(i==15 && j==12) && !(square.getOccupants().isEmpty()))
				{
					if(square.getOccupants().get(0) instanceof Pellet)
					{
						square.getOccupants().get(0).leaveSquare();
					}
				}
			}
		}
		
		//After the last pellet is consumed, end the game
		assertTrue(player.isAlive());
		Thread.sleep(1000L);
		game.move(player, Direction.EAST);
		Thread.sleep(200L);
		int pellets = level.remainingPellets();
		assertEquals(0, pellets);
		game.stop();
		
	}
	
	@After
	public void tearDown() throws Exception {
		launcher.dispose();
	}
	

}
