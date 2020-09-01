package com.tzhenia.real.estate.company.web.interceptors;

import com.tzhenia.real.estate.company.exception.AgentNotFoundException;
import com.tzhenia.real.estate.company.exception.DealNotFoundException;
import com.tzhenia.real.estate.company.exception.RealEstateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final String REAL_ESTATE_NOT_FOUND_EXCEPTION_MESSAGE = "Real estate not found";
    private static final String AGENT_NOT_FOUND_EXCEPTION_MESSAGE = "Agent not found";
    private static final String DEAL_NOT_FOUND_EXCEPTION_MESSAGE = "Deal not found";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RealEstateNotFoundException.class)
    public void handleRealEstateNotFoundException(RealEstateNotFoundException e) {
        log.warn(REAL_ESTATE_NOT_FOUND_EXCEPTION_MESSAGE, e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AgentNotFoundException.class)
    public void handleAgentNotFoundException(AgentNotFoundException e) {
        log.warn(AGENT_NOT_FOUND_EXCEPTION_MESSAGE, e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DealNotFoundException.class)
    public void handleDealNotFoundException(DealNotFoundException e) {
        log.warn(DEAL_NOT_FOUND_EXCEPTION_MESSAGE, e);
    }
}