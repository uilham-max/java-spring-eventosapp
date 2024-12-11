package com.eventosapp.repository;

import com.eventosapp.models.Evento;
import org.springframework.data.repository.CrudRepository;

public interface IEventoRepository extends CrudRepository<Evento, String> {
    Evento findByCodigo(long codigo);
}
