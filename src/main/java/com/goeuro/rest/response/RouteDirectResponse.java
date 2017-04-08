package com.goeuro.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response object for direct rest endpoint.
 */
public class RouteDirectResponse {

    @JsonProperty(value = "dep_sid", required = true)
    private Integer fromStationId;

    @JsonProperty(value = "arr_sid", required = true)
    private Integer toStationId;

    @JsonProperty(value = "direct_bus_route", required = true)
    private boolean hasDirectRoute;

    public RouteDirectResponse(Integer fromStationId, Integer toStationId, boolean hasDirectRoute) {
        this.fromStationId = fromStationId;
        this.toStationId = toStationId;
        this.hasDirectRoute = hasDirectRoute;
    }

    public Integer getFromStationId() {
        return fromStationId;
    }

    public Integer getToStationId() {
        return toStationId;
    }

    public boolean isHasDirectRoute() {
        return hasDirectRoute;
    }
}
