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
 * Créé le 9 juil. 2019.
 * </p>
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import enums.TypeAction;
import enums.TypeKeys;
import enums.TypeStat;
import enums.TypeTarget;
import mock.gameservice.GameServiceMockForPartyGameServiceTest;
import players.Guerrier;
import players.Mage;
import players.Player;
import players.Rodeur;
import utils.PartyGameServiceImpl;

public class PartyGameServiceTest {
	
	private static PartyGameServiceImpl partyGameService;
	private static GameServiceMockForPartyGameServiceTest mock;
	
	@BeforeClass
	public static void initialSetup() {
		partyGameService = new PartyGameServiceImpl();
		mock = new GameServiceMockForPartyGameServiceTest();
		PartyGameServiceImpl.setGameService(mock);
	}

	@Test
	public void testMinimalValue() {
		assertEquals(1, partyGameService.minimalValue(0));
		assertEquals(2, partyGameService.minimalValue(2));
	}
	
	@Test
	public void testDoBoost() {
		Player player = new Guerrier(10, 50, 10, 0, 0, "test");
		partyGameService.doBoost(player, 10, TypeStat.LIFE);
		assertEquals(50, player.getLife());
		player.setLife(45);
		partyGameService.doBoost(player, 2, TypeStat.LIFE);
		assertEquals(47, player.getLife());
		partyGameService.doBoost(player, 10, TypeStat.AGILITY);
		assertEquals(10, player.getAgility());
		partyGameService.doBoost(player, 10, TypeStat.STRENGTH);
		assertEquals(20, player.getStrength());
		partyGameService.doBoost(player, 10, TypeStat.INTELLIGENCE);
		assertEquals(10, player.getIntelligence());
		assertEquals(player, partyGameService.doBoost(player, 10, TypeStat.EMPTY));
	}
	
	@Test
	public void testDoAction() {
		Player player = new Guerrier(10, 50, 10, 0, 0, "test");
		partyGameService.doAction(TypeAction.DAMAGE, player, 5, null);
		assertEquals(45, player.getLife());
		partyGameService.doAction(TypeAction.BOOST, player, 5, TypeStat.INTELLIGENCE);
		assertEquals(5, player.getIntelligence());
	}
	
	@Test
	public void testChangeStat() {
		List<Player> players = new ArrayList<>();
		Player player1 = new Guerrier(10, 0, 10, 0, 0, "test");
		Player player2 = new Mage(10, 50, 10, 0, 0, "test");
		players.add(player1);
		players.add(player2);
		players = partyGameService.changeStat(players, 1, 10, TypeAction.BOOST, TypeStat.INTELLIGENCE);
		assertEquals(10, players.get(1).getIntelligence());
		assertEquals(2, partyGameService.changeStat(players, 0, 10, TypeAction.BOOST, TypeStat.INTELLIGENCE).size());
	}
	
	@Test
	public void testverifyTarget() {
		List<Player> players = new ArrayList<>();
		Player player2 = new Guerrier(10, 50, 10, 0, 0, "test");
		players.add(player2);
		assertFalse(partyGameService.verifyTarget(players, player2, 0));
		assertTrue(partyGameService.verifyTarget(players, new Guerrier(), 0));
		assertFalse(partyGameService.verifyTarget(players, player2, 1));
	}
	
	@Test
	public void testFindOther() {
		List<Player> players = new ArrayList<>();
		Player player1 = new Guerrier(10, 0, 10, 0, 0, "p 1");
		Player player2 = new Guerrier(10, 50, 10, 0, 0, "p 2");
		players.add(player1);
		players.add(player2);
		assertEquals(1, partyGameService.findOther(players, player1));
		Player player3 = new Rodeur(10, 50, 0, 10, 0, "p 3");
		players.add(player3);
		mock.setNbAsk(4);
		assertEquals(1, partyGameService.findOther(players, player3));
		assertEquals(0, partyGameService.findOther(new ArrayList<Player>(), player1));
	}
	
	@Test
	public void testFindMe() {
		List<Player> players = new ArrayList<>();
		Player player1 = new Guerrier(10, 0, 10, 0, 0, "p 1");
		Player player2 = new Guerrier(10, 50, 10, 0, 0, "p 2");
		players.add(player1);
		players.add(player2);
		assertEquals(1, partyGameService.findMe(players, player2));
	}
	
	@Test
	public void testCheckTarget() {
		List<Player> players = new ArrayList<>();
		Player player1 = new Guerrier(10, 0, 10, 0, 0, "p 1");
		Player player2 = new Guerrier(10, 50, 10, 0, 0, "p 2");
		players.add(player1);
		players.add(player2);
		assertEquals(0, partyGameService.checkTarget(players, player1, TypeTarget.MYSELF));
		assertEquals(1, partyGameService.checkTarget(players, player1, TypeTarget.OTHER));
		assertEquals(-1, partyGameService.checkTarget(players, player1, TypeTarget.NONE));
	}
	
	@Test
	public void testDoAttack() {
		Player player2 = new Guerrier(10, 50, 10, 0, 0, "p 2");
		List<Map<TypeKeys, Object>> basicAttack = partyGameService.doAttack(1, player2);
		assertEquals(10, basicAttack.get(0).get(TypeKeys.AMOUNT));
		assertEquals(TypeAction.DAMAGE, basicAttack.get(0).get(TypeKeys.ACTION));
		assertNull(basicAttack.get(0).get(TypeKeys.STAT));
		assertEquals(TypeTarget.OTHER, basicAttack.get(0).get(TypeKeys.TARGET));
		
		List<Map<TypeKeys, Object>> specialAttack = partyGameService.doAttack(2, player2);
		assertEquals(5, specialAttack.get(0).get(TypeKeys.AMOUNT));
		assertEquals(TypeAction.DAMAGE, specialAttack.get(0).get(TypeKeys.ACTION));
		assertNull(specialAttack.get(0).get(TypeKeys.STAT));
		assertEquals(TypeTarget.MYSELF, specialAttack.get(0).get(TypeKeys.TARGET));
	}
	
	@Test
	public void testPlayGame() {
		List<Player> players = new ArrayList<>();
		Player player1 = new Guerrier(10, 0, 10, 0, 0, "p 1");
		Player player2 = new Guerrier(10, 1, 10, 0, 0, "p 2");
		Player player3 = new Guerrier(10, 50, 10, 0, 0, "p 3");
		players.add(player1);
		players.add(player2);
		players.add(player3);
		assertEquals(1, partyGameService.playGame(players).size());
		players.clear();
		assertEquals(0, partyGameService.playGame(players).size());
	}
	
	@Test
	public void testRemoveDeadPlayer() {
		List<Player> players = new ArrayList<>();
		Player player1 = new Guerrier(10, 5, 10, 0, 0, "p 1");
		players.add(player1);
		assertEquals(1, partyGameService.removeDeadPlayer(players, 0).size());
		player1.setLife(0);
		assertEquals(0, partyGameService.removeDeadPlayer(players, 0).size());
	}
	
	@Test
	public void testFindNextPlayer() {
		List<Player> players = new ArrayList<>();
		Player player1 = new Guerrier(10, 0, 10, 0, 0, "p 1");
		Player player2 = new Guerrier(10, 1, 10, 0, 0, "p 2");
		Player player3 = new Guerrier(10, 50, 10, 0, 0, "p 3");
		players.add(player1);
		players.add(player2);
		players.add(player3);
		assertEquals("p 1", partyGameService.findNextPlayer(players, null).getName());
		assertEquals("p 2", partyGameService.findNextPlayer(players, player1).getName());
		assertEquals("p 3", partyGameService.findNextPlayer(players, player2).getName());
		assertEquals("p 1", partyGameService.findNextPlayer(players, player3).getName());
	}

}

