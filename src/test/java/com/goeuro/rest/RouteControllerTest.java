package com.goeuro.rest;

import com.goeuro.model.Routes;
import com.goeuro.rest.response.RouteDirectResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RouteControllerTest {

    @Mock
    private Routes routes;

    @Spy
    @InjectMocks
    private RouteController unit;

    @Test
    public void testDirectWillReturnDirectResponse() throws Exception {
        doReturn(true).when(routes).hasDirectRoute(1, 2);

        RouteDirectResponse actualResponse = unit.direct(1, 2);
        assertNotNull(actualResponse);
        assertEquals(Integer.valueOf(1), actualResponse.getFromStationId());
        assertEquals(Integer.valueOf(2), actualResponse.getToStationId());
        assertTrue(actualResponse.isHasDirectRoute());

        verify(routes).hasDirectRoute(1, 2);
        verifyNoMoreInteractions(routes);
    }

    @Test
    public void testDirectWillReturnFalseIfNoDirectRouteFound() throws Exception {
        RouteDirectResponse actualResponse = unit.direct(1, 2);
        assertEquals(Integer.valueOf(1), actualResponse.getFromStationId());
        assertEquals(Integer.valueOf(2), actualResponse.getToStationId());
        assertFalse(actualResponse.isHasDirectRoute());

        verify(routes).hasDirectRoute(1, 2);
        verifyNoMoreInteractions(routes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDirectRoutesThrowsException() throws Exception {
        doThrow(new IllegalArgumentException()).when(routes).hasDirectRoute(1, 2);

        unit.direct(1, 2);
    }
}
