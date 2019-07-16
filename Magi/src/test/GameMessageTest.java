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

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import players.Guerrier;
import players.Mage;
import players.Player;
import players.Rodeur;
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
	
	@Test
	public void testChooseAttack() {
		List<Player> players = new ArrayList<>();
		players.add(new Guerrier());
		players.add(new Rodeur());
		players.add(new Mage());
		
		assertEquals("Choisissez une attaque (1:Coup d’épée, 2:Coup de Rage)", gameMessage.chooseAttack(players.get(0)));
		assertEquals("Choisissez une attaque (1:Tir à l’Arc, 2:Concentration)", gameMessage.chooseAttack(players.get(1)));
		assertEquals("Choisissez une attaque (1:Boule de Feu, 2:Soin)", gameMessage.chooseAttack(players.get(2)));
	}

}

