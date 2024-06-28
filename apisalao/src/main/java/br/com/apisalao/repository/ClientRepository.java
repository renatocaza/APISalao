package br.com.apisalao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apisalao.entity.ClientEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    boolean existsByEmail(String email);

}
