package com.goeuro.io;

import com.goeuro.constants.MessageConstants;
import com.goeuro.exception.WrongFormatException;
import com.goeuro.model.Routes;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.NoSuchFileException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RoutesReaderTest {

    public static final String VALID_ROUTES_DATA_FILENAME = "src/test/resources/valid-routes.data";
    public static final String INVALID_ROUTES_DATA_FILENAME = "src/test/resources/invalid-routes.data";
    public static final String UNEXISTING_FILENAME = "idonotexist";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private RoutesReader unit = new RoutesReader();

    @Test
    public void testReadRoutesWillProduceRoutes() throws Exception {
        Routes actualResult = unit.readRoutes(VALID_ROUTES_DATA_FILENAME);

        assertNotNull(actualResult);
        assertTrue(actualResult.hasDirectRoute(1, 4));
        assertTrue(actualResult.hasDirectRoute(3, 6));
        assertTrue(actualResult.hasDirectRoute(0, 6));
        assertFalse(actualResult.hasDirectRoute(0, 10));
    }

    @Test
    public void testReadRoutesThrowsExceptionIfInvalidFileProvided() throws Exception {
        expectedException.expect(WrongFormatException.class);
        expectedException.expectMessage(MessageConstants.WRONG_FILE_FORMAT_MESSAGE);
        unit.readRoutes(INVALID_ROUTES_DATA_FILENAME);
    }

    @Test(expected = NoSuchFileException.class)
    public void testReadRoutesThrowsExceptionIfFileNotFound() throws Exception {
        unit.readRoutes(UNEXISTING_FILENAME);
    }

    @Test
    public void testReadRoutesThrowsExceptionIfNullPassed() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(MessageConstants.INVALID_ARGUMENT_MESSAGE);
        unit.readRoutes(null);
    }

    @Test
    public void testReadRoutesThrowsExceptionIfBlankFileNameIsPassed() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(MessageConstants.INVALID_ARGUMENT_MESSAGE);
        unit.readRoutes("   ");
    }
}
