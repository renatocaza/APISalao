package br.com.apisalao.controller;

import br.com.apisalao.entity.ClientEntity;
import br.com.apisalao.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private ClientService clienteService;

    @GetMapping
    public List<ClientEntity> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ClientEntity getCliente(@PathVariable Long id) {

        return clienteService.getCliente(id);
    }

    @PostMapping
    public ClientEntity createCliente(@RequestBody ClientEntity cliente) {
        return clienteService.createCliente(cliente);
    }

    @PutMapping("/{id}")
    public ClientEntity updateCliente(@PathVariable Long id, @RequestBody ClientEntity clienteDetails) {
        return clienteService.updateCliente(id, clienteDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.ok().build();
    }

}
