package com.goeuro.model;

import com.goeuro.constants.MessageConstants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoutesTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Routes unit = new Routes();

    @Test
    public void testAddRouteWillAddNewRoute() throws Exception {
        unit.addRoute(1, Arrays.asList(2, 4, 5));

        assertTrue(unit.hasDirectRoute(2, 4));
        assertTrue(unit.hasDirectRoute(2, 5));
        assertTrue(unit.hasDirectRoute(4, 5));
        assertFalse(unit.hasDirectRoute(1, 6));
    }

    @Test
    public void testAddRouteNullRouteIdIsPassed() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(MessageConstants.INVALID_ROUTE_DATA_MESSAGE);
        unit.addRoute(null, Arrays.asList(2, 4, 5));
    }

    @Test
    public void testAddRouteNullStationListIsPassed() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(MessageConstants.INVALID_ROUTE_DATA_MESSAGE);
        unit.addRoute(1, null);
    }

    @Test
    public void testAddRouteEmptyListIsPassed() throws Exception {
        unit.addRoute(1, Collections.emptyList());
        assertFalse(unit.hasDirectRoute(2, 4));
    }

    @Test
    public void testHasDirectRouteWillTestIfDirectRouteIsAvailable() throws Exception {
        unit.addRoute(1, Arrays.asList(1, 2));
        unit.addRoute(2, Arrays.asList(1, 3));

        assertTrue(unit.hasDirectRoute(1, 2));
        assertTrue(unit.hasDirectRoute(1, 3));
        assertFalse(unit.hasDirectRoute(2, 3));
    }

    @Test
    public void testHasDirectRouteSameStationIdIsPassedTwice() throws Exception {
        unit.addRoute(1, Arrays.asList(1, 2));

        assertTrue(unit.hasDirectRoute(1, 1));
    }

    @Test
    public void testHasDirectRouteWillReturnSameResultIfArgumentsPassedInDifferentOrder() throws Exception {
        unit.addRoute(1, Arrays.asList(1, 2));

        assertTrue(unit.hasDirectRoute(1, 2));
        assertTrue(unit.hasDirectRoute(2, 1));
        assertFalse(unit.hasDirectRoute(0, 1));
        assertFalse(unit.hasDirectRoute(1, 0));
    }

    @Test
    public void testHasDirectRouteNullsArePassed() throws Exception {
        unit.addRoute(1, Arrays.asList(1, 2));

        assertFalse(unit.hasDirectRoute(null, null));
        assertFalse(unit.hasDirectRoute(1, null));
        assertFalse(unit.hasDirectRoute(null, 2));
    }
}
