package com.example.CompraGamer.iservice;

import com.example.CompraGamer.entity.Orden;
import java.util.List;
import java.util.Optional;

public interface i_Orden_service {
    List<Orden> listar();
    Optional<Orden> buscarPorId(Long id);
    Orden guardar(Orden orden);
    void eliminar(Long id);
}
