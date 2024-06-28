package br.com.apisalao.controller;
import br.com.apisalao.entity.EmployeProcedureEntity;
import br.com.apisalao.entity.ProcedureEntity;
import br.com.apisalao.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procedimentos")
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;

    @GetMapping
    public List<ProcedureEntity> getAllProcedure() {
        return procedureService.getAllProcedure();
    }

    @GetMapping("/{id}")
    public ProcedureEntity getProcedure(@PathVariable Long id) {
        return procedureService.getProcedure(id);
    }

    @PostMapping
    public ProcedureEntity createProcedure(@RequestBody ProcedureEntity procedimento) {
        return procedureService.createProcedure(procedimento);
    }

    @PutMapping("/{id}")
    public ProcedureEntity updateProcedure(@PathVariable Long id, @RequestBody ProcedureEntity procedimentoDetails) {
        return procedureService.updateProcedure(id, procedimentoDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProcedure(@PathVariable Long id) {
        procedureService.deleteProcedure(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/funcionario_procedimento")
    public ResponseEntity<EmployeProcedureEntity> associarProcedimentoFuncionario(@RequestBody EmployeProcedureEntity funcionarioProcedimento) {
        EmployeProcedureEntity associacao =
                procedureService.procedureEmployeeAssociate(funcionarioProcedimento.getFuncionario().getFuncId(), funcionarioProcedimento.getProcedimento().getId());
        if (associacao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(associacao);
    }
}
