package com.github.kosmateus.shinden.http.request;

/**
 * Interface representing a query parameter in an HTTP request.
 * <p>
 * The {@code QueryParam} interface provides methods to retrieve the name and value
 * of a query parameter. Implementations of this interface can be used to represent
 * key-value pairs that are appended to the URL as part of the query string in an HTTP request.
 * </p>
 *
 * @version 1.0.0
 */
public interface QueryParam {

    /**
     * Returns the name of the query parameter.
     *
     * @return the parameter name as a {@link String}.
     */
    String getQueryParameter();

    /**
     * Returns the value of the query parameter.
     *
     * @return the parameter value as a {@link String}.
     */
    String getQueryValue();
}
