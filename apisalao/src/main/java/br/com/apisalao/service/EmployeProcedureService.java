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
public class EmployeProcedureService {

    @Autowired
    private EmployedProcedureRepository employeProcedureRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private ProcedureRepository procedureRepository;

    public List<EmployeProcedureEntity> getAll() {
        return employeProcedureRepository.findAll();
    }

    public EmployeProcedureEntity getById(Long id) {
        return employeProcedureRepository.findById(id).orElse(null);
    }

    public EmployeProcedureEntity create(EmployeProcedureEntity employeProcedure) {
        // Recupere as entidades persistentes do banco de dados
        EmployeEntity funcionario = employeRepository.findById(employeProcedure.getFuncionario().getFuncId()).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        ProcedureEntity procedimento = procedureRepository.findById(employeProcedure.getProcedimento().getId()).orElseThrow(() -> new RuntimeException("Procedimento não encontrado"));

        // Defina as entidades persistentes no employeProcedure
        employeProcedure.setFuncionario(funcionario);
        employeProcedure.setProcedimento(procedimento);

        return employeProcedureRepository.save(employeProcedure);
    }

    public EmployeProcedureEntity update(Long id, EmployeProcedureEntity employeProcedureDetails) {
        EmployeProcedureEntity employeProcedure = employeProcedureRepository.findById(id).orElse(null);
        if (employeProcedure != null) {
            employeProcedure.setFuncionario(employeProcedureDetails.getFuncionario());
            employeProcedure.setProcedimento(employeProcedureDetails.getProcedimento());
            employeProcedureRepository.save(employeProcedure);
        }
        return employeProcedure;
    }

    public void delete(Long id) {
        employeProcedureRepository.deleteById(id);
    }
}