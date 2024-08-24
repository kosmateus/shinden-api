package com.github.kosmateus.shinden.utils;

import com.github.kosmateus.shinden.http.request.FormParam;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Utility class for handling and manipulating {@link FormParam} instances and their mappings.
 * <p>
 * The {@code FormParamUtils} class provides static utility methods to merge form parameters
 * with control over null handling, as well as to create type mappings for {@code FormParam} values.
 * This class is not meant to be instantiated and all methods are statically accessible.
 * </p>
 *
 * @version 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FormParamUtils {

    /**
     * Merges the current value with a new value, with an option to accept or reject null fields.
     *
     * @param <T>              the type of the values being merged
     * @param currentValue     the current value that may be retained if the new value is {@code null}
     * @param newValue         the new value to be merged
     * @param acceptNullFields if {@code true}, the {@code newValue} will replace {@code currentValue}
     *                         even if it is {@code null}; if {@code false}, {@code currentValue} is
     *                         retained when {@code newValue} is {@code null}
     * @return the merged value, which may be either {@code currentValue} or {@code newValue}
     */
    public static <T> T merge(T currentValue, T newValue, boolean acceptNullFields) {
        return acceptNullFields ? newValue : newValue != null ? newValue : currentValue;
    }

    /**
     * Merges the current value with a new value, rejecting null fields by default.
     * <p>
     * This method is a convenience overload of {@link #merge(Object, Object, boolean)}
     * with {@code acceptNullFields} set to {@code false}.
     * </p>
     *
     * @param <T>          the type of the values being merged
     * @param currentValue the current value that may be retained if the new value is {@code null}
     * @param newValue     the new value to be merged
     * @return the merged value, which may be either {@code currentValue} or {@code newValue}
     */
    public static <T> T merge(T currentValue, T newValue) {
        return merge(currentValue, newValue, false);
    }

    /**
     * Creates a type mapping for a specific {@link FormParam} subclass.
     * <p>
     * This method generates a {@link Map.Entry} containing the class type of the {@link FormParam}
     * and a {@link Function} that maps a {@link String} value to the corresponding {@link FormParam}
     * instance from the provided array of possible values. If the input string does not match any
     * known value, an {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param <T>    the type of {@link FormParam} subclass
     * @param clazz  the {@link Class} of the {@link FormParam} subclass
     * @param values an array of possible {@link FormParam} values to map from
     * @return a {@link Map.Entry} containing the class type and the mapping function
     * @throws IllegalArgumentException if the input value does not match any known {@link FormParam} value
     */
    public static <T extends FormParam> Map.Entry<Class<T>, Function<String, T>> createTypeMapping(Class<T> clazz, T[] values) {
        return new AbstractMap.SimpleEntry<>(
                clazz,
                (value) -> Stream.of(values)
                        .filter(v -> v.getValue().equals(value))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value))
        );
    }
}
