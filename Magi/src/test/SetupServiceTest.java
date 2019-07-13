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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mock.gameservice.GameServiceMockForSetupServiceTest;
import players.Guerrier;
import players.Mage;
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
	
	@Before
	public void setupChooseClassFunction() {
		mock.setChooseTreat(0);
	}

	@Test
	public void testChooseClassFunction() {	
		assertNull(setupService.chooseClass());
		assertTrue(setupService.chooseClass() instanceof Guerrier);
		assertTrue(setupService.chooseClass() instanceof Rodeur);
		assertTrue(setupService.chooseClass() instanceof Mage);		
	}
	
	@Before
	public void setupChooseStat() {
		mock.setChooseTreat(1);
	}

}

