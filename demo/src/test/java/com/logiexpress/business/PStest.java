package com.logiexpress.business;

import com.logiexpress.data.PaqueteRepository;
import com.logiexpress.data.ClienteRepository;
import com.logiexpress.domain.Paquete;
import com.logiexpress.domain.Cliente;
import com.logiexpress.exception.PesoExcedidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PSTest {

    @Mock
    private PaqueteRepository paqueteRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private PaqueteService paqueteService;

    @Test
    void registrarPaquete_Exitoso() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Paquete paquete = new Paquete();
        paquete.setPesoKg(13.0);
        paquete.setCliente(cliente);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(paqueteRepository.save(any(Paquete.class))).thenReturn(paquete);

        Paquete resultado = paqueteService.registrarPaquete(paquete);

        assertNotNull(resultado);
        verify(paqueteRepository, times(1)).save(paquete);
    }

    @Test
    void registrarPaquete_PesoExcedido_LanzaExcepcion() {
        Paquete paquete = new Paquete();
        paquete.setPesoKg(30.0);

        assertThrows(PesoExcedidoException.class, () -> {
            paqueteService.registrarPaquete(paquete);
        });

        verify(paqueteRepository, never()).save(any());
    }
}