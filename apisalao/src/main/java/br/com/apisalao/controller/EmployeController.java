package br.com.apisalao.controller;

import br.com.apisalao.entity.ClientEntity;
import br.com.apisalao.entity.EmployeEntity;
import br.com.apisalao.service.ClientService;
import br.com.apisalao.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class EmployeController {

    @Autowired
    private EmployeService funcionarioService;

    @GetMapping
    public List<EmployeEntity> getAllFuncionarios() {
        return funcionarioService.getAllFuncioanrios();
    }

    @GetMapping("/{id}")
    public EmployeEntity getFuncionario(@PathVariable Long id) {
        return funcionarioService.getFuncioanrio(id);
    }

    @PostMapping
    public EmployeEntity createFuncionario(@RequestBody EmployeEntity func) {
        return funcionarioService.createFuncioanrio(func);
    }

    @PutMapping("/{id}")
    public EmployeEntity updateFuncionario(@PathVariable Long id, @RequestBody EmployeEntity funcionarioDetails) {
        return funcionarioService.updateFuncioanrio(id, funcionarioDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFuncionario(@PathVariable Long id) {
        funcionarioService.deleteFuncioanrio(id);
        return ResponseEntity.ok().build();
    }

}
