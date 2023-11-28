package com.fepi.Trabalho.Repositorios;

import com.fepi.Trabalho.Modelos.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PacienteRepositorio extends JpaRepository<Paciente,Long> {
}
