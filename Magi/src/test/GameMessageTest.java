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
 * Créé le 8 juil. 2019.
 * </p>
 */
package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import utils.GameMessage;

public class GameMessageTest {
	
	private static GameMessage gameMessage;
	
	@BeforeClass
	public static void setUp() {
		gameMessage = new GameMessage();
	}

	@Test
	public void testLeftSkillPointsMessage() {
		gameMessage.leftSkillPointsMessage(1);
		assertEquals(2, gameMessage.getElements().size());
		gameMessage.leftSkillPointsMessage(0);
		assertEquals(0, gameMessage.getElements().size());
	}

}

