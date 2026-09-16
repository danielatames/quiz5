package com.logiexpress.controller;

import com.logiexpress.domain.Paquete;
import com.logiexpress.business.PaqueteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/paquetes")
public class PaqueteController {

    private final PaqueteService paqueteService;

    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo paquete")
    @ApiResponse(responseCode = "201", description = "Paquete creado exitosamente")
    public ResponseEntity<Paquete> registrar(@Valid @RequestBody PaqueteRequestDto requestDto) {
        Paquete paquete = new Paquete();
        paquete.setCodigoRastreo(requestDto.codigoRastreo());
        paquete.setDescripcion(requestDto.descripcion());
        paquete.setPesoKg(requestDto.pesoKg());
        
        com.logiexpress.domain.Cliente cliente = new com.logiexpress.domain.Cliente();
        cliente.setId(requestDto.clienteId());
        paquete.setCliente(cliente);

        Paquete nuevoPaquete = paqueteService.registrarPaquete(paquete);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaquete);
    }
}

record PaqueteRequestDto(
    @NotBlank String codigoRastreo,
    @NotBlank String descripcion,
    @NotNull @Positive Double pesoKg,
    @NotNull Long clienteId
) {}