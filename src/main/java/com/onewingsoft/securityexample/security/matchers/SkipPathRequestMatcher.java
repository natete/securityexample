package com.onewingsoft.securityexample.security.matchers;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@See RequestMatcher} A request skipMatcher to allow to skip some urls.
 */
public class SkipPathRequestMatcher implements RequestMatcher {

    private OrRequestMatcher skipMatcher;
    private RequestMatcher processingMatcher;

    /**
     * Constructor that builds the class matchers according to the received parameters.
     *
     * @param pathsToSkip the list of paths to be skipped.
     * @param processingPath the path to match.
     */
    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {
        if (pathsToSkip == null) {
            throw new IllegalArgumentException("Missing paths to skip");
        }

        List<RequestMatcher> matchers = pathsToSkip.stream().map(AntPathRequestMatcher::new)
                .collect(Collectors.toList());

        this.skipMatcher = new OrRequestMatcher(matchers);
        processingMatcher = new AntPathRequestMatcher(processingPath);
    }

    /**
     * {@see RequestMatcher#matches}
     *
     * @param request the request to be matched.
     * @return true if the request matches the processingMatcher and does not match the skipMatcher.
     */
    @Override
    public boolean matches(HttpServletRequest request) {
        if (skipMatcher.matches(request)) {
            return false;
        }

        return processingMatcher.matches(request);
    }
}
