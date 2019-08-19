package org.springframework.http.client;

import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;

import java.net.URI;

public class PreEmptiveAuthHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    private AuthCache authCache;

    public PreEmptiveAuthHttpRequestFactory(HttpClient client, AuthCache authCache) {

        super(client);

        this.authCache = authCache;
    }

    @Override
    protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {

        HttpClientContext context = HttpClientContext.create();
        context.setAuthCache(this.authCache);

        return context;
    }
}
