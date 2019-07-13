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
 * Créé le 4 juil. 2019.
 * </p>
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import mock.gameservice.GameServiceMockForSetupServiceTest;
import players.Guerrier;
import players.Mage;
import players.Player;
import players.Rodeur;
import utils.SetupServiceImpl;

public class SetupServiceTest {
	
	private static SetupServiceImpl setupService;
	private static GameServiceMockForSetupServiceTest mock;
	
	@BeforeClass
	public static void initialSetup() {
		setupService = new SetupServiceImpl();
		mock = new GameServiceMockForSetupServiceTest();
		SetupServiceImpl.setGameService(mock);
	}

	@Test
	public void testChooseClass() {
		mock.setChooseTreat(0);
		mock.setNbCall(0);
		assertNull(setupService.chooseClass());
		assertTrue(setupService.chooseClass() instanceof Guerrier);
		assertTrue(setupService.chooseClass() instanceof Rodeur);
		assertTrue(setupService.chooseClass() instanceof Mage);		
	}
	
	@Test
	public void testChooseStat() {
		mock.setChooseTreat(1);	
		Player player = setupService.chooseStat(new Guerrier());
		assertEquals(10, player.getLvl());
		assertEquals(50, player.getLife());
		assertEquals(10, player.getStrength());
	}
	
	@Test
	public void testSkillPointsLeft() {
		mock.setChooseTreat(2);
		mock.setNbCall(10);
		Player player = new Guerrier();
		player.setLvl(5);
		setupService.skillPointLeft(player);
		assertEquals(0, mock.getNbCall());
	}
	
	@Test
	public void testSetupGame() {
		mock.setChooseTreat(3);
		mock.setNbCall(0);
		assertTrue(setupService.setupGame().size() == 2);
	}
}

