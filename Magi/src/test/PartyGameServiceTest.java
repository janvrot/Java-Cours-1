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
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import enums.TypeAction;
import enums.TypeStat;
import mock.gameservice.GameServiceMockForPartyGameServiceTest;
import players.Guerrier;
import players.Player;
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
		Player player2 = new Guerrier(10, 50, 10, 0, 0, "test");
		players.add(player1);
		players.add(player2);
		players = partyGameService.changeStat(players, 1, 10, TypeAction.BOOST, TypeStat.INTELLIGENCE);
		assertEquals(10, players.get(1).getIntelligence());
		assertEquals(1, partyGameService.changeStat(players, 0, 10, TypeAction.BOOST, TypeStat.INTELLIGENCE).size());
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
		Player player1 = new Guerrier(10, 0, 10, 0, 0, "test");
		Player player2 = new Guerrier(10, 50, 10, 0, 0, "test");
		players.add(player1);
		players.add(player2);
	}

}

