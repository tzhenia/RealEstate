package com.tzhenia.real.estate.company.web.controller;

import com.tzhenia.real.estate.company.entity.Agent;
import com.tzhenia.real.estate.company.service.AgentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/agent/")
@AllArgsConstructor
public class AgentController {
    private final AgentService agentService;

    @ApiOperation(value = "Load agents", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns available agents"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @GetMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Agent>> findAll() {
        return ResponseEntity.ok(agentService.findAll());
    }

    @ApiOperation(value = "Load agent", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns available agent by id"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Agent> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(agentService.findById(id));
    }

    @ApiOperation(value = "Create agent", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - saves agent"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void create(@RequestBody @Validated Agent agent) {
        agentService.save(agent);
    }

    @ApiOperation(value = "Update agent", httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - saves agent"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @PutMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void update(@RequestBody @Validated Agent agent) {
        agentService.save(agent);
    }

    @ApiOperation(value = "Delete agent", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - deletes agent by id"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteById(@PathVariable("id") Long id) {
        agentService.deleteById(id);
    }
}