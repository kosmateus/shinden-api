package com.github.kosmateus.shinden.http.rest;

import com.github.kosmateus.shinden.auth.SessionManager;
import com.github.kosmateus.shinden.http.request.HttpRequest;
import com.github.kosmateus.shinden.http.response.EmptyReason;
import com.github.kosmateus.shinden.http.response.ErrorDetails;
import com.github.kosmateus.shinden.http.response.ResponseHandler;
import com.google.gson.Gson;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
class HttpRestClientExecutor {

    private final CloseableHttpClient client = HttpClients.createDefault();
    private final Gson gson = new Gson();
    private final SessionManager sessionManager;

    HttpUriRequest createHttpRequest(HttpRequest httpRequest, String method) {
        HttpUriRequest request = createRequest(method, URI.create(httpRequest.getURL()));
        httpRequest.getHeaders().forEach(request::addHeader);
        return request;
    }

    private HttpUriRequest createRequest(String method, URI uri) {
        switch (method) {
            case "GET":
                return new HttpGet(uri);
            case "POST":
                return new HttpPost(uri);
            case "PUT":
                return new HttpPut(uri);
            default:
                throw new IllegalArgumentException("Method not supported");
        }
    }

    <T> ResponseHandler<T> executeRequest(Supplier<HttpUriRequest> executor, Class<T> clazz) {
        HttpUriRequest request = executor.get();
        try (CloseableHttpResponse response = client.execute(request)) {
            return handleResponseResult(response, clazz);
        } catch (IOException e) {
            return handleIOException(e);
        } catch (Exception e) {
            return handleGenericException(e);
        }
    }

    private <T> ResponseHandler<T> handleResponseResult(HttpResponse response, Class<T> clazz)
            throws IOException {
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);

        int status = response.getStatusLine().getStatusCode();
        Map<String, String> headers = Arrays.stream(response.getAllHeaders())
                .collect(Collectors.toMap(Header::getName, Header::getValue));
        if (status < 400) {
            if (StringUtils.isNotBlank(body)) {
                return ResponseHandler.of(gson.fromJson(body, clazz), status, headers);
            }
            return ResponseHandler.emptyOk(status, headers);
        }

        return ResponseHandler.empty(status, headers,
                EmptyReason.fromHttpStatus(status, ErrorDetails.builder().build()));
    }

    private <T> ResponseHandler<T> handleIOException(IOException e) {
        return ResponseHandler.empty(800, null,
                EmptyReason.errorResponse(
                        ErrorDetails.builder()
                                .message(e.getMessage())
                                .errorName("IOException")
                                .build()
                )
        );
    }

    private <T> ResponseHandler<T> handleGenericException(Exception e) {
        return ResponseHandler.empty(801, null, EmptyReason.errorResponse(
                ErrorDetails.builder().message(e.getMessage())
                        .errorName("Unexpected exception during REST call").build()));
    }
}
