package test;

import static enums.TypeFilter.CHOOSEATACK;
import static enums.TypeFilter.CHOOSECLASS;
import static enums.TypeFilter.CHOOSEEMPTY;
import static enums.TypeFilter.CHOOSELVL;
import static enums.TypeFilter.CHOOSEPLAYERS;
import static enums.TypeFilter.CHOOSESTATS;
import static enums.TypeFilter.CHOOSETARGET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import enums.TypeFilter;
import mock.gameservice.GameServiceMockForPlayerConditionServiceTest;
import utils.PlayerConditionsServiceImpl;

public class PlayerConditionsServiceTest {

	private static PlayerConditionsServiceImpl playerConditionsService;

	@Before
	public void setup() {
		playerConditionsService = new PlayerConditionsServiceImpl();
		PlayerConditionsServiceImpl.setGameService(new GameServiceMockForPlayerConditionServiceTest());
	}

	@Test
	public void testAddFilter() {

		// Cas de base false
		assertFalse(playerConditionsService.addFilter(-1, null));
		assertFalse(playerConditionsService.addFilter(-1, TypeFilter.CHOOSEATACK));

		// Cas de base true
		assertTrue(playerConditionsService.addFilter(1, null));

		// Test chooseClassCondition
		assertFalse(playerConditionsService.addFilter(0, CHOOSECLASS));
		assertFalse(playerConditionsService.addFilter(4, CHOOSECLASS));
		assertTrue(playerConditionsService.addFilter(1, CHOOSECLASS));

		// Test choosePlayers
		assertFalse(playerConditionsService.addFilter(0, CHOOSEPLAYERS));
		assertTrue(playerConditionsService.addFilter(2, CHOOSEPLAYERS));

		// Test chooseLvl
		assertFalse(playerConditionsService.addFilter(0, CHOOSELVL));
		assertFalse(playerConditionsService.addFilter(101, CHOOSELVL));
		assertTrue(playerConditionsService.addFilter(1, CHOOSELVL));

		// Test chooseStats
		assertFalse(playerConditionsService.addFilter(-2, CHOOSESTATS));
		assertFalse(playerConditionsService.addFilter(101, CHOOSESTATS));
		assertTrue(playerConditionsService.addFilter(0, CHOOSESTATS));
		assertTrue(playerConditionsService.addFilter(100, CHOOSESTATS));
		assertTrue(playerConditionsService.addFilter(10, CHOOSESTATS));

		// Test chooseAttack
		assertFalse(playerConditionsService.addFilter(0, CHOOSEATACK));
		assertFalse(playerConditionsService.addFilter(3, CHOOSEATACK));
		assertTrue(playerConditionsService.addFilter(1, CHOOSEATACK));

		// Test chooseTarget
		assertFalse(playerConditionsService.addFilter(0, CHOOSETARGET));
		assertTrue(playerConditionsService.addFilter(1, CHOOSETARGET));

		// Test default
		assertFalse(playerConditionsService.addFilter(0, CHOOSEEMPTY));

	}
	
	@Test
	public void testCheckCondition() {
		assertEquals(1, playerConditionsService.checkCondition("test", CHOOSETARGET));
	}

}
