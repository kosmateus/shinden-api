package com.github.kosmateus.shinden.http.rest;

import com.github.kosmateus.shinden.http.request.HttpRequest;
import com.github.kosmateus.shinden.http.response.ResponseHandler;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

/**
 * HTTP client for making RESTful API calls.
 * <p>
 * The {@code HttpRestClient} provides methods to perform HTTP GET, POST, and PUT requests
 * to RESTful APIs. It leverages the {@link HttpRestClientExecutor} to handle the execution
 * of these requests and process the responses.
 * </p>
 *
 * @version 1.0.0
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class HttpRestClient {

    private final HttpRestClientExecutor executor;

    /**
     * Executes an HTTP GET request.
     *
     * @param <T>         the type of the entity expected in the response.
     * @param httpRequest the {@link HttpRequest} containing the details of the request.
     * @param returnType  the {@link Class} of the expected return type.
     * @return a {@link ResponseHandler} containing the response data.
     */
    public <T> ResponseHandler<T> get(HttpRequest httpRequest, Class<T> returnType) {
        return executor.executeRequest(() -> executor.createHttpRequest(httpRequest, HttpGet.METHOD_NAME), returnType);
    }

    /**
     * Executes an HTTP POST request with a JSON body.
     *
     * @param <T>         the type of the entity expected in the response.
     * @param httpRequest the {@link HttpRequest} containing the details of the request.
     * @param body        the JSON body to be sent in the POST request.
     * @param returnType  the {@link Class} of the expected return type.
     * @return a {@link ResponseHandler} containing the response data.
     */
    public <T> ResponseHandler<T> post(HttpRequest httpRequest, String body, Class<T> returnType) {
        HttpPost httpPost = (HttpPost) executor.createHttpRequest(httpRequest, HttpPost.METHOD_NAME);
        httpPost.setEntity(new StringEntity(body, "UTF-8"));
        return executor.executeRequest(() -> httpPost, returnType);
    }

    /**
     * Executes an HTTP PUT request with a JSON body.
     *
     * @param <T>         the type of the entity expected in the response.
     * @param httpRequest the {@link HttpRequest} containing the details of the request.
     * @param body        the JSON body to be sent in the PUT request.
     * @param returnType  the {@link Class} of the expected return type.
     * @return a {@link ResponseHandler} containing the response data.
     */
    public <T> ResponseHandler<T> put(HttpRequest httpRequest, String body, Class<T> returnType) {
        HttpPut httpPut = (HttpPut) executor.createHttpRequest(httpRequest, HttpPut.METHOD_NAME);
        httpPut.setEntity(new StringEntity(body, "UTF-8"));
        return executor.executeRequest(() -> httpPut, returnType);
    }
}
