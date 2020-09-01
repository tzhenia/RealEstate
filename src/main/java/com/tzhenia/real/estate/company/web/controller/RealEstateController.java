package com.tzhenia.real.estate.company.web.controller;

import com.tzhenia.real.estate.company.entity.RealEstate;
import com.tzhenia.real.estate.company.entity.Status;
import com.tzhenia.real.estate.company.service.RealEstateService;
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
@RequestMapping("/api/realEstate/")
@AllArgsConstructor
public class RealEstateController {
    private final RealEstateService realEstateService;

    @ApiOperation(value = "Load real estates", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns available real estates"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @GetMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<RealEstate>> findAll() {
        return ResponseEntity.ok(realEstateService.findAll());
    }

    @ApiOperation(value = "Load real estates by status", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns available real"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @GetMapping(path = "/status/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<RealEstate>> findByStatus(@PathVariable("status") Status status) {
        return ResponseEntity.ok(realEstateService.findByStatus(status));
    }

    @ApiOperation(value = "Load real estate", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns available real estate by id"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RealEstate> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(realEstateService.findById(id));
    }

    @ApiOperation(value = "Create real estate", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - saves real estate"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void create(@RequestBody @Validated RealEstate realEstate) {
        realEstateService.save(realEstate);
    }

    @ApiOperation(value = "Update real estate", httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - saves real estate"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @PutMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void update(@RequestBody @Validated RealEstate realEstate) {
        realEstateService.save(realEstate);
    }

    @ApiOperation(value = "Delete real estate", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - deletes real estate by id"),
            @ApiResponse(code = 500, message = "Internal error while loading data")})
    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteById(@PathVariable("id") Long id) {
        realEstateService.deleteById(id);
    }
}