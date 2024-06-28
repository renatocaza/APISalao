package br.com.apisalao.service;

import br.com.apisalao.entity.ClientEntity;
import br.com.apisalao.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<ClientEntity> getAllClientes() {
        List<ClientEntity> clientes = clientRepository.findAll();
        if (clientes.isEmpty()){
            throw new NoSuchElementException("Não há clientes cadastrados.");
        }
        return clientes;
    }

    public ClientEntity getCliente(Long id) {
        Optional<ClientEntity> clientes = clientRepository.findById(id);

        if (clientes.isPresent()){
            return clientes.get();
        }
        throw new NoSuchElementException("Cliente não encontrado!");
    }

    public ClientEntity createCliente(ClientEntity cliente) {
        if (cliente.getClienteId() != null && clientRepository.existsById(cliente.getClienteId())) {
            throw new IllegalArgumentException("Um novo cliente não pode ter um ID existente");
        }else if (clientRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Email já existe");
        }
        cliente.setInclusao(LocalDate.now());
        return clientRepository.save(cliente);
    }

    public ClientEntity updateCliente(Long id, ClientEntity clienteDetalhes) {
        ClientEntity cliente = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado com o ID: " + id));

        cliente.setNome(clienteDetalhes.getNome());
        cliente.setEmail(clienteDetalhes.getEmail());
        cliente.setTelefone(clienteDetalhes.getTelefone());
        cliente.setManutencao(LocalDate.now());

        return clientRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente não encontrado com o ID: " + id);
        }
        clientRepository.deleteById(id);
    }
}
