package com.fepi.Trabalho.Repositorios;

import com.fepi.Trabalho.Modelos.Exame;
import com.fepi.Trabalho.Modelos.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExameRepositorio extends JpaRepository<Exame,Long> {
    void deleteByPaciente(Paciente paciente);
}
