package com.onewingsoft.securityexample.controller;

import com.onewingsoft.securityexample.aop.AuditMethod;
import com.onewingsoft.securityexample.controller.handler.ExceptionsHandler;
import com.onewingsoft.securityexample.controller.response.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that contains test methods.
 *
 * @author iiglesias
 */
@RestController
@RequestMapping(value = "/api/test")
@Api(value = "Services that contains test methods")
public class TestController extends ExceptionsHandler {

    /**
     * Method to check if the service is available
     *
     * @return
     */
    @GetMapping
    @ApiOperation(value = "Service to check if the service is available")
    @AuditMethod(level = { AuditMethod.Level.NAME })
    public ResponseEntity<String> getAvailable() {
        return ResponseEntity.ok().body("Available API!");
    }

    /**
     * Method to check if exception handler is operational
     */
    @GetMapping(value = "/exception")
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiOperation(value = "Service to check if exception handler is operational")
    @ApiResponses(
            value = { @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
    @AuditMethod(level = { AuditMethod.Level.NAME, AuditMethod.Level.PARAMS })
    public void getException() {
        throw new RuntimeException("Throw runtime exception for test service");
    }
}
