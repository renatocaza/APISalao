package br.com.apisalao.controller;

import br.com.apisalao.entity.EmployeProcedureEntity;
import br.com.apisalao.service.EmployeProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarioProcedimento")
public class EmployeProcedureController {

    @Autowired
    private EmployeProcedureService employeProcedureService;

    @GetMapping
    public List<EmployeProcedureEntity> getAll() {
        return employeProcedureService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeProcedureEntity getById(@PathVariable Long id) {
        return employeProcedureService.getById(id);
    }

    @PostMapping
    public ResponseEntity<EmployeProcedureEntity> create(@RequestBody EmployeProcedureEntity employeProcedure) {
        EmployeProcedureEntity novoEmployeProcedure = employeProcedureService.create(employeProcedure);
        if (novoEmployeProcedure == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(novoEmployeProcedure);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeProcedureEntity> update(@PathVariable Long id, @RequestBody EmployeProcedureEntity employeProcedureDetails) {
        EmployeProcedureEntity updatedEmployeProcedure = employeProcedureService.update(id, employeProcedureDetails);
        if (updatedEmployeProcedure == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployeProcedure);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeProcedureService.delete(id);
        return ResponseEntity.ok().build();
    }
}

