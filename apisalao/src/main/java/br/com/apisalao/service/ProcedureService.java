package br.com.apisalao.service;

import br.com.apisalao.entity.EmployeEntity;
import br.com.apisalao.entity.EmployeProcedureEntity;
import br.com.apisalao.entity.ProcedureEntity;
import br.com.apisalao.repository.EmployeRepository;
import br.com.apisalao.repository.EmployedProcedureRepository;
import br.com.apisalao.repository.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedureService {

    @Autowired
    private EmployedProcedureRepository employedProcedureRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private ProcedureRepository procedureRepository;

    public List<ProcedureEntity> getAllProcedure() {
        return procedureRepository.findAll();
    }

    public ProcedureEntity getProcedure(Long id) {
        return procedureRepository.findById(id).orElse(null);
    }

    public ProcedureEntity createProcedure(ProcedureEntity procedimento) {
        return procedureRepository.save(procedimento);
    }

    public ProcedureEntity updateProcedure(Long id, ProcedureEntity procedimentoDetails) {
        ProcedureEntity procedimento = procedureRepository.findById(id).orElse(null);
        if (procedimento != null) {
            procedimento.setNome(procedimentoDetails.getNome());
            procedimento.setDescricao(procedimentoDetails.getDescricao());
            procedimento.setDuracao(procedimentoDetails.getDuracao());
            procedimento.setPreco(procedimentoDetails.getPreco());
            procedureRepository.save(procedimento);
        }
        return procedimento;
    }

    public void deleteProcedure(Long id) {
        procedureRepository.deleteById(id);
    }

    public EmployeProcedureEntity procedureEmployeeAssociate(Long funcionarioId, Long procedimentoId) {
        EmployeEntity funcionario = employeRepository.findById(funcionarioId).orElse(null);
        ProcedureEntity procedimento = procedureRepository.findById(procedimentoId).orElse(null);

        if (funcionario != null && procedimento != null) {
            EmployeProcedureEntity funcionarioProcedimento = new EmployeProcedureEntity();
            funcionarioProcedimento.setFuncionario(funcionario);
            funcionarioProcedimento.setProcedimento(procedimento);

            return employedProcedureRepository.save(funcionarioProcedimento);
        }

        return null;
    }
}

