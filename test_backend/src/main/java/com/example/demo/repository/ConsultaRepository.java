package com.example.demo.repository;

import com.example.demo.entity.Consulta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends CrudRepository<Consulta, Long> {
}
