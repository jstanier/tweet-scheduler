package org.jstanier;

import static org.mockito.Mockito.when;

import java.io.StringReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.jstanier.InputParser;
import com.jstanier.InputReader;

@RunWith(MockitoJUnitRunner.class)
public class InputParserTest {

    private static final String ONE_LINE_VALID_CSV = "1,\"The content of your tweet\"";

    @Mock
    private InputReader inputReader;

    @InjectMocks
    private InputParser inputParser;

    @Test
    public void parseInput_givenGivenOneCsvLine_thenOneTweetToScheduleIsReturned() {
        when(inputReader.getInputReader(Mockito.anyString())).thenReturn(
                new StringReader(ONE_LINE_VALID_CSV));
        inputParser.parseInput(ONE_LINE_VALID_CSV);
    }
}
