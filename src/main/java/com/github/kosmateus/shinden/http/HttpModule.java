package com.github.kosmateus.shinden.http;

import com.github.kosmateus.shinden.auth.SessionManager;
import com.github.kosmateus.shinden.http.jsoup.JsoupModule;
import com.github.kosmateus.shinden.http.rest.RestModule;
import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HttpModule extends AbstractModule {

    private final SessionManager sessionManager;

    @Override
    protected void configure() {
        install(new JsoupModule(sessionManager));
        install(new RestModule(sessionManager));
    }

}
