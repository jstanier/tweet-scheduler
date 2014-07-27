package org.jstanier.tweetscheduler.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.jstanier.tweetscheduler.main.Main;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {

	@Test(expected = IllegalArgumentException.class)
	public void whenMainIsCalledWithNoArguments_thenAnIllegalArgumentExceptionIsThrown() {
		Main.main(new String[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenMainIsCalledWithTooManyArguments_thenAnIllegalArgumentExceptionIsThrown() {
		Main.main(new String[2]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenMainIsCalledWithAnInvalidPath_thenAnIllegalArgumentExceptionIsThrown() {
		String path = "not a valid path";
		Main.main(new String[] { path });
	}
}
