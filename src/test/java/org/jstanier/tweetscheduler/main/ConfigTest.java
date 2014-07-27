package org.jstanier.tweetscheduler.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.jstanier.tweetscheduler.main.Config;

@RunWith(MockitoJUnitRunner.class)
public class ConfigTest {

	@Test
	public void whenConfigIsCreated_thenNoExceptionIsThrown() {
		new Config();
	}
}
