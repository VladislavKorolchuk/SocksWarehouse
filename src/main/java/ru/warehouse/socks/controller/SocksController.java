package ru.warehouse.socks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.warehouse.socks.dto.SocksDto;
import ru.warehouse.socks.entity.ComparisonEnumeration;
import ru.warehouse.socks.entity.Socks;
import ru.warehouse.socks.service.SocksService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/socks")
@Tag(name = "Warehouse of socks", description = "Warehouse Methods")
public class SocksController {

    private final SocksService socksService;

    @Operation(summary = "Arrival of socks to the warehouse")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Socks added successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid field in DTO"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping("/income")
    public ResponseEntity<Socks> create(@RequestBody @Valid SocksDto socksDto) {
        return ResponseEntity.ok(socksService.incomeSocks(socksDto));
    }

    @Operation(summary = "Release of socks from the warehouse")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Socks released successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid field in DTO"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping("/outcome")
    public ResponseEntity<Socks> outcomeSocks(@RequestBody @Valid SocksDto socksDto) {
        return ResponseEntity.ok(socksService.outcomeSocks(socksDto));
    }

    @Operation(summary = "Number of socks in stock")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Number of socks received"),
                    @ApiResponse(responseCode = "400", description = "Invalid field in DTO"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping
    public ResponseEntity<Integer> getSocks(@RequestParam @Valid @NotBlank @Size(max = 32) String color,
                                            @RequestParam @Valid @NotBlank ComparisonEnumeration comparisonEnumeration,
                                            @RequestParam @Valid @Positive @Max(100) int cottonPart) {
        return ResponseEntity.ok(socksService.getSocks(color, comparisonEnumeration, cottonPart));
    }

}
