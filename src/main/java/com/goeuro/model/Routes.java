package com.goeuro.model;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * Model for bus route data.
 */
public class Routes {
    private final Map<Integer, List<Integer>> routes = new HashMap<>();

    /**
     * Registers a list of station ids by route id.
     *
     * @param routeId bus route id
     * @param stationIdList list of bus station ids
     */
    public void addRoute(Integer routeId, List<Integer> stationIdList) {
        for (int stationId : stationIdList) {
            List<Integer> routeIdList = getRoutesByStation(stationId);
            routeIdList.add(routeId);
        }
    }

    /**
     * Returns {@code true} if the is a direct route between two station ids, {@code false} otherwise.
     *
     * @param fromStationId station id from
     * @param toStationId station id to
     * @return {@code true} if direct route exists, {@code false} otherwise
     */
    public boolean hasDirectRoute(Integer fromStationId, Integer toStationId) {
        boolean answer = false;

        List<Integer> fromStationRouteIdList = routes.get(fromStationId);
        List<Integer> toStationRouteIdList = routes.get(toStationId);
        if (CollectionUtils.isNotEmpty(fromStationRouteIdList) && CollectionUtils.isNotEmpty(toStationRouteIdList)) {
            Set<Integer> fromStationIdSet = new HashSet<>(fromStationRouteIdList);
            Set<Integer> toStationIdSet = new HashSet<>(toStationRouteIdList);
            answer = CollectionUtils.containsAny(fromStationIdSet, toStationIdSet);
        }
        return answer;
    }

    private List<Integer> getRoutesByStation(Integer stationId) {
        List<Integer> routeIdList = routes.get(stationId);
        if (routeIdList == null) {
            routeIdList = new LinkedList<>();
            routes.put(stationId, routeIdList);
        }
        return routeIdList;
    }

}
