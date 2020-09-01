package com.tzhenia.real.estate.company.web.controller;

import com.tzhenia.real.estate.company.entity.Deal;
import com.tzhenia.real.estate.company.facade.DealFacade;
import com.tzhenia.real.estate.company.model.Statistic;
import com.tzhenia.real.estate.company.service.DealService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deal/")
@AllArgsConstructor
public class DealController {
    private final DealService dealService;
    private final DealFacade dealFacade;

    @ApiOperation(value = "Load deals", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns available deals"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @GetMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Deal>> findAll() {
        return ResponseEntity.ok(dealService.findAll());
    }

    @ApiOperation(value = "Load deals by agent id", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns available deals by agent id"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @GetMapping(path = "/agent/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Deal>> findByAgentId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(dealService.findByAgentId(id));
    }

    @ApiOperation(value = "Load top sellers by limit", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns available deals by agent id"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @GetMapping(path = "/agent/top/{limit}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Statistic>> findTopSellers(@PathVariable("limit") int limit) {
        return ResponseEntity.ok(dealService.findTopSellers(limit));
    }

    @ApiOperation(value = "Load deal", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns available deal by id"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Deal> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(dealService.findById(id));
    }

    @ApiOperation(value = "Create deal", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - saves deal"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void create(@RequestBody @Validated Deal deal) {
        dealFacade.sellRealEstate(deal);
    }
}