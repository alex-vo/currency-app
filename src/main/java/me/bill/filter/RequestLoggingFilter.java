package me.bill.filter;

import lombok.extern.slf4j.Slf4j;
import me.bill.entity.Request;
import me.bill.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        try {
            ZonedDateTime now = ZonedDateTime.now();
            log.info("Request received from {} to {} at {}", req.getRemoteAddr(), req.getRequestURI(), now.format(DateTimeFormatter.ISO_DATE_TIME));
            Request request = new Request();
            request.setIp(req.getRemoteAddr());
            request.setDateTime(now);
            request.setUrl(req.getRequestURI());
            requestRepository.save(request);
        } finally {
            filterChain.doFilter(req, resp);
        }
    }
}
