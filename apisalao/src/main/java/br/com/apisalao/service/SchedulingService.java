package br.com.apisalao.service;

import br.com.apisalao.entity.ClientEntity;
import br.com.apisalao.entity.EmployeEntity;
import br.com.apisalao.entity.ProcedureEntity;
import br.com.apisalao.entity.SchedulingEntity;
import br.com.apisalao.repository.ClientRepository;
import br.com.apisalao.repository.EmployeRepository;
import br.com.apisalao.repository.ProcedureRepository;
import br.com.apisalao.repository.SchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private ProcedureRepository procedureRepository;

    public List<SchedulingEntity> getAllAgendamentos() {
        return schedulingRepository.findAll();
    }

    public SchedulingEntity getAgendamento(Long id) {
        return schedulingRepository.findById(id).orElse(null);
    }

    public SchedulingEntity createAgendamento(SchedulingEntity agendamento) {
        // Recupere as entidades persistentes do banco de dados
        ClientEntity cliente = clientRepository.findById(
                agendamento.getCliente().getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        EmployeEntity funcionario = employeRepository.findById(
                agendamento.getFuncionario().getFuncId())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        ProcedureEntity procedimento = procedureRepository.findById(
                agendamento.getProcedimento().getId())
                    .orElseThrow(() -> new RuntimeException("Procedimento não encontrado"));

        // Defina as entidades persistentes no agendamento
        agendamento.setCliente(cliente);
        agendamento.setFuncionario(funcionario);
        agendamento.setProcedimento(procedimento);

        // Verificar se o funcionário já tem um agendamento que se sobrepõe ao horário proposto
        List<SchedulingEntity> agendamentosExistentes = schedulingRepository.findByFuncionarioAndDataHoraInicioLessThanEqualAndDataHoraFimGreaterThanEqual(
                funcionario,
                agendamento.getDataHoraInicio(),
                agendamento.getDataHoraFim()
        );

        if (agendamentosExistentes.isEmpty()) {
            // Se não houver conflito, salve o novo agendamento
            return schedulingRepository.save(agendamento);
        } else {
            // Se houver conflito, retorne null ou lance uma exceção
            return null;
        }
    }

    public SchedulingEntity updateAgendamento(Long id, SchedulingEntity agendamentoDetails) {
        SchedulingEntity agendamento = schedulingRepository.findById(id).orElse(null);
        if (agendamento != null) {
            agendamento.setCliente(agendamentoDetails.getCliente());
            agendamento.setFuncionario(agendamentoDetails.getFuncionario());
            agendamento.setProcedimento(agendamentoDetails.getProcedimento());
            agendamento.setDataHoraInicio(agendamentoDetails.getDataHoraInicio());
            agendamento.setDataHoraFim(agendamentoDetails.getDataHoraFim());
            schedulingRepository.save(agendamento);
        }
        return agendamento;
    }

    public void deleteAgendamento(Long id) {
        schedulingRepository.deleteById(id);

    }
}