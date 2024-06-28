package br.com.apisalao.service;

import br.com.apisalao.entity.ClientEntity;
import br.com.apisalao.entity.EmployeEntity;
import br.com.apisalao.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeService {
    @Autowired
    private EmployeRepository employeRepository;

    public List<EmployeEntity> getAllFuncioanrios() {
        List<EmployeEntity> funcionarios = employeRepository.findAll();
        if (funcionarios.isEmpty()){
            throw new NoSuchElementException("Não há funcionários cadastrados.");
        }
        return funcionarios;
    }

    public EmployeEntity getFuncioanrio(Long id) {
        Optional<EmployeEntity> funcionario = employeRepository.findById(id);

        if (!funcionario.isPresent()){
            throw new NoSuchElementException("Funcionário não encontrado!");
        }
        return funcionario.get();
    }

    public EmployeEntity createFuncioanrio(EmployeEntity funcionario) {
        if (funcionario.getFuncId() != null && employeRepository.existsById(funcionario.getFuncId())) {
            throw new IllegalArgumentException("Um novo funcionário não pode ter um ID existente");
        }else if (employeRepository.existsByEmail(funcionario.getEmail())) {
            throw new RuntimeException("Email já existe");
        }
        funcionario.setInclusao(LocalDate.now());
        return employeRepository.save(funcionario);
    }

    public EmployeEntity updateFuncioanrio(Long id, EmployeEntity funcionarioDetalhes) {
        EmployeEntity funcionario = employeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionário não encontrado com o ID: " + id));

        funcionario.setNome(funcionarioDetalhes.getNome());
        funcionario.setEmail(funcionarioDetalhes.getEmail());
        funcionario.setTelefone(funcionarioDetalhes.getTelefone());
        funcionario.setManutencao(LocalDate.now());

        return employeRepository.save(funcionario);
    }

    public void deleteFuncioanrio(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new NoSuchElementException("Funcionário não encontrado com o ID: " + id);
        }
        employeRepository.deleteById(id);
    }
}
