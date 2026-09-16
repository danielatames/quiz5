package com.logiexpress.business;

import com.logiexpress.data.PaqueteRepository;
import com.logiexpress.data.ClienteRepository;
import com.logiexpress.domain.Paquete;
import com.logiexpress.domain.Cliente;
import com.logiexpress.exception.PesoExcedidoException;
import com.logiexpress.exception.ClienteNoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;
    private final ClienteRepository clienteRepository; 

    public PaqueteService(PaqueteRepository paqueteRepository, ClienteRepository clienteRepository) {
        this.paqueteRepository = paqueteRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Paquete registrarPaquete(Paquete paquete) {
        if (paquete.getPesoKg() > 30.0) {
            throw new PesoExcedidoException("El peso del paquete supera el límite permitido de 30.0 kg.");
        }
        
        Cliente cliente = clienteRepository.findById(paquete.getCliente().getId()).orElseThrow(() -> new ClienteNoEncontradoException("El cliente especificado no existe en la base de datos."));
        
        paquete.setCliente(cliente);
        return paqueteRepository.save(paquete);
    }
}