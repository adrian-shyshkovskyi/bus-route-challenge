package com.goeuro.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response object for direct rest endpoint.
 */
@lombok.Getter
@lombok.RequiredArgsConstructor
public class RouteDirectResponse {

    @JsonProperty(value = "dep_sid", required = true)
    private final Integer fromStationId;

    @JsonProperty(value = "arr_sid", required = true)
    private final Integer toStationId;

    @JsonProperty(value = "direct_bus_route", required = true)
    private final boolean hasDirectRoute;
}
