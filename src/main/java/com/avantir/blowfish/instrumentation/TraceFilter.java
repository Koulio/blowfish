package com.avantir.blowfish.instrumentation;

import org.springframework.boot.actuate.trace.TraceProperties;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.boot.actuate.trace.WebRequestTraceFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by lekanomotayo on 21/10/2017.
 */
@Component
public class TraceFilter extends WebRequestTraceFilter {

    private final String[] excludedEndpoints = new String[]{"/css/**", "/*.htm", "/*.html", "/js/**", "/trace"};

    TraceFilter(TraceRepository repository, TraceProperties properties) {
        super(repository, properties);
    }

    @Override
    protected boolean shouldNotFilter(final HttpServletRequest request) throws ServletException {
        return Arrays.stream(excludedEndpoints)
                .anyMatch(e -> new AntPathMatcher().match(e, request.getServletPath()));
    }
}
