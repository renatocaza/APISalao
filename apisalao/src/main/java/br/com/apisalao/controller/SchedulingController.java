package br.com.apisalao.controller;

import br.com.apisalao.entity.SchedulingEntity;
import br.com.apisalao.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @GetMapping
    public List<SchedulingEntity> getAllAgendamentos() {
        return schedulingService.getAllAgendamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingEntity> getAgendamento(@PathVariable Long id) {
        SchedulingEntity agendamento = schedulingService.getAgendamento(id);
        if (agendamento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping
    public ResponseEntity<SchedulingEntity> createAgendamento(@RequestBody SchedulingEntity agendamento) {
        SchedulingEntity novoAgendamento = schedulingService.createAgendamento(agendamento);
        if (novoAgendamento == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(novoAgendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchedulingEntity> updateAgendamento(@PathVariable Long id, @RequestBody SchedulingEntity agendamentoDetails) {
        SchedulingEntity agendamentoAtualizado = schedulingService.updateAgendamento(id, agendamentoDetails);
        if (agendamentoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agendamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAgendamento(@PathVariable Long id) {
        schedulingService.deleteAgendamento(id);
        return ResponseEntity.ok().build();
    }
}
