package me.bill.interceptor;

import lombok.extern.slf4j.Slf4j;
import me.bill.entity.Request;
import me.bill.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object object) {
        try {
            ZonedDateTime now = ZonedDateTime.now();
            log.info("Request received from {} to {} at {}", req.getRemoteAddr(), req.getRequestURI(), now.format(DateTimeFormatter.ISO_DATE_TIME));
            Request request = new Request();
            request.setIp(req.getRemoteAddr());
            request.setDateTime(now);
            request.setUrl(req.getRequestURI());
            requestRepository.save(request);
        } catch (Exception e) {
            log.error("Failed to persist request", e);
        }

        return true;
    }
}