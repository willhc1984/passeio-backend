package com.passeios_app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passeios_app.backend.model.Lugar;

public interface LugarRepository extends JpaRepository<Lugar, Long>{

}
