package com.github.kosmateus.shinden.utils;

import com.github.kosmateus.shinden.http.request.QueryParam;
import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The {@code QueryParamsBuilder} class is a utility for constructing query parameter maps.
 *
 * <p>This class provides a static method to create a map of query parameters from an array
 * of {@link QueryParam} objects, ensuring that only non-null parameters are included in the final map.</p>
 *
 * <p>The resulting map is immutable, making it safe to use in contexts where immutability is required.</p>
 *
 * @version 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryParamsBuilder {

    /**
     * Constructs a map of query parameters from the provided {@link QueryParam} objects.
     *
     * <p>This method filters out any {@code null} {@link QueryParam} objects to prevent
     * {@code NullPointerException} during map construction. The resulting map is immutable.</p>
     *
     * @param queryParams an array of {@link QueryParam} objects
     * @return an immutable map of query parameters
     */
    public static Map<String, String> build(QueryParam... queryParams) {
        return Arrays.stream(queryParams)
                .filter(Objects::nonNull)
                .collect(
                        ImmutableMap.Builder<String, String>::new,
                        (builder, queryParam) -> builder.put(queryParam.getQueryParameter(), queryParam.getQueryValue()),
                        (builder1, builder2) -> builder1.putAll(builder2.build())
                )
                .build();
    }

    /**
     * Constructs a map of query parameters by combining values with specified separators for each class of {@link QueryParam}.
     *
     * <p>This method allows for the combination of multiple query parameter values into a single entry
     * using a specified separator for each {@link QueryParam} class. It filters out any {@code null}
     * parameters to prevent {@code NullPointerException} during map construction.</p>
     *
     * @param separatorMap a map that associates each {@link QueryParam} class with its separator
     * @param queryParams  an array of {@link QueryParam} objects
     * @return an immutable map of combined query parameters
     */
    public static Map<String, String> buildWithSeparators(Map<Class<? extends QueryParam>, String> separatorMap, QueryParam... queryParams) {
        ImmutableMap.Builder<String, String> builder = new ImmutableMap.Builder<>();
        Map<String, String> tempMap = new java.util.HashMap<>();
        Arrays.stream(queryParams)
                .filter(Objects::nonNull)
                .forEach(queryParam -> {
                    String key = queryParam.getQueryParameter();
                    String value = queryParam.getQueryValue();
                    String separator = separatorMap.getOrDefault(queryParam.getClass(), ",");

                    if (tempMap.containsKey(key)) {
                        String combinedValue = tempMap.get(key) + separator + value;
                        tempMap.put(key, combinedValue);
                    } else {
                        tempMap.put(key, value);
                    }
                });
        tempMap.forEach(builder::put);
        return builder.build();
    }

    public static Map<String, String> buildWithSeparators(Map<Class<? extends QueryParam>, String> separatorMap, Set<QueryParam> queryParams) {
        return buildWithSeparators(separatorMap, queryParams.toArray(new QueryParam[0]));
    }

}
