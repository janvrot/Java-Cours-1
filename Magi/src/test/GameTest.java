/**
 * <p>
 * Copyright © 2019 THALES Communications, France. All rights reserved.
 * </p>
 * <p>
 * Ce document est la propriété de THALES Communications, France,
 * il ne peut être ni reproduit, ni utilisé, ni communiqué, ni distribué
 * à  des tiers sans son autorisation préalable.
 * </p>
 * <p>
 * Créé le 27 juin 2019.
 * </p>
 */
package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import utils.Game;

public class GameTest {
	
	private static Game game;

	@Test
	public void testgetResponse() {
		game = new Game();
		String errorInput = "add 5";
	    InputStream in = new ByteArrayInputStream(errorInput.getBytes());
	    System.setIn(in);
	    
	    assertEquals(-1, game.getResponse());
	    
	    String workingInput = "5";
	    in = new ByteArrayInputStream(workingInput.getBytes());
	    System.setIn(in);
	    
	    assertEquals(5, game.getResponse());
	}
	
	@Test
	public void testQuestion() {
		
	}

}

