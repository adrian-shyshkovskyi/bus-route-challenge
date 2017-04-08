package com.goeuro.rest;

import com.goeuro.model.Routes;
import com.goeuro.rest.response.RouteDirectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Bus routes REST controller.
 */
@RestController
public class RouteController {

    @Autowired
    private Routes routes;

    /**
     * Tests if there is a direct connection between two bus stations.
     *
     * @param fromStationId station id from
     * @param toStationId   station id to
     * @return an instance of {@link RouteDirectResponse} containing operation result
     */
    @RequestMapping("/api/direct")
    public RouteDirectResponse direct(@RequestParam(value = "dep_sid") Integer fromStationId,
                                      @RequestParam(value = "arr_sid") Integer toStationId) {
        return new RouteDirectResponse(fromStationId, toStationId, routes.hasDirectRoute(fromStationId, toStationId));
    }
}
