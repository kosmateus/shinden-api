package com.github.kosmateus.shinden.http.rest;

import com.github.kosmateus.shinden.http.request.HttpRequest;
import com.github.kosmateus.shinden.http.response.ResponseHandler;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

/**
 * HTTP client for making API calls.
 * <p>
 * The {@code HttpClient} class provides methods to perform HTTP GET, POST, and PUT requests
 * to RESTful APIs. It leverages the {@link HttpRestClientExecutor} to handle the execution
 * of these requests and process the responses. This class supports the inclusion of various
 * data types in requests, such as JSON bodies, form fields, and file uploads.
 * </p>
 *
 * @version 1.0.0
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class HttpClient {

    private final HttpRestClientExecutor executor;

    /**
     * Executes an HTTP GET request.
     * <p>
     * This method sends an HTTP GET request to the specified target, allowing the retrieval
     * of resources from the server. The response is expected to contain data of the specified
     * return type.
     * </p>
     *
     * @param <T>         the type of the entity expected in the response.
     * @param httpRequest the {@link HttpRequest} containing the details of the request, such as the target URL and headers.
     * @param returnType  the {@link Class} of the expected return type. This is used to deserialize the response body
     *                    into an instance of the specified type.
     * @return a {@link ResponseHandler} containing the response data.
     */
    public <T> ResponseHandler<T> get(HttpRequest httpRequest, Class<T> returnType) {
        return executor.executeRequest(() -> Pair.of(executor.createHttpRequest(httpRequest, HttpGet.METHOD_NAME), httpRequest), returnType);
    }

    /**
     * Executes an HTTP POST request with a combination of JSON body, form fields, and multiple file uploads.
     * <p>
     * This method sends an HTTP POST request to the specified target, allowing the inclusion of a JSON body,
     * form fields, and multiple files as part of a multipart form-data request. The method will prioritize
     * sending the JSON body if it is provided, and it will also handle any form fields and files provided in the request.
     * </p>
     *
     * @param <T>         the type of the entity expected in the response.
     * @param httpRequest the {@link HttpRequest} containing the details of the request, such as the target URL,
     *                    headers, form fields, JSON body, and multiple file resources.
     * @param returnType  the {@link Class} of the expected return type. This is used to deserialize the response body
     *                    into an instance of the specified type.
     * @return a {@link ResponseHandler} containing the response data.
     */
    public <T> ResponseHandler<T> post(HttpRequest httpRequest, Class<T> returnType) {
        return executor.executeRequest(() -> Pair.of(executor.createHttpRequest(httpRequest, HttpPost.METHOD_NAME), httpRequest), returnType);
    }

    /**
     * Executes an HTTP PUT request with a JSON body.
     * <p>
     * This method sends an HTTP PUT request to the specified target, allowing the inclusion of a JSON body
     * to update existing resources on the server. The response is expected to contain data of the specified
     * return type.
     * </p>
     *
     * @param <T>         the type of the entity expected in the response.
     * @param httpRequest the {@link HttpRequest} containing the details of the request, such as the target URL and headers.
     * @param returnType  the {@link Class} of the expected return type. This is used to deserialize the response body
     *                    into an instance of the specified type.
     * @return a {@link ResponseHandler} containing the response data.
     */
    public <T> ResponseHandler<T> put(HttpRequest httpRequest, Class<T> returnType) {
        return executor.executeRequest(() -> Pair.of(executor.createHttpRequest(httpRequest, HttpPut.METHOD_NAME), httpRequest), returnType);
    }
}
