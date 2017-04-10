package com.goeuro.io;

import com.goeuro.constants.MessageConstants;
import com.goeuro.exception.WrongFormatException;
import com.goeuro.model.Routes;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * File reader for {@link Routes} instances.
 */
public class RoutesReader {

    /**
     * Creates a {@link Routes} instance based on the file path provided.
     *
     * @param filePath path to a file containing routes data
     * @return a {@link Routes} instance
     * @throws IOException          if an IO error occured
     * @throws WrongFormatException if file format is wrong
     */
    public Routes readRoutes(String filePath) throws IOException, WrongFormatException {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException(MessageConstants.INVALID_ARGUMENT_MESSAGE);
        }

        Path path = FileSystems.getDefault().getPath(filePath);
        Routes routes;
        try {
            routes = createRoutesFromFile(path);
        } catch (NumberFormatException e) {
            throw new WrongFormatException(MessageConstants.WRONG_FILE_FORMAT_MESSAGE, e);
        }
        return routes;
    };

    private static Routes createRoutesFromFile(Path path) throws IOException {
        Routes routes = new Routes();
        Files.lines(path)
                .skip(1)
                .map((l) -> l.trim().split("\\s+"))
                .map((sa)->Stream.of(sa).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()))
                .forEach(l -> addRoute(routes, l));
        return routes;
    }

    private static void addRoute(Routes routes, List<Integer> routeDetails) {
        Integer routeId = routeDetails.get(0);
        List<Integer> stationIdList = routeDetails.subList(1, routeDetails.size());

        routes.addRoute(routeId, stationIdList);
    }

}
