package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import enums.TypeFilter;
import mock.PlayerConditionImplMock;
import utils.GameServiceImpl;

public class GameTest {

	private static GameServiceImpl gameService;

	@Before
	public void setup() {
		gameService = new GameServiceImpl();
		gameService.setPlayerConditionsService(new PlayerConditionImplMock());
	}

	@Test
	public void testgetResponse() {
		String errorInput = "add 5";
		InputStream in = new ByteArrayInputStream(errorInput.getBytes());
		System.setIn(in);

		assertEquals(-1, gameService.getResponse());

		String workingInput = "5";
		in = new ByteArrayInputStream(workingInput.getBytes());
		System.setIn(in);

		assertEquals(5, gameService.getResponse());
	}

	@Test
	public void testAddQuestion() {
		assertEquals(1, gameService.addQuestion("test", TypeFilter.CHOOSEPLAYERS));
	}
}
