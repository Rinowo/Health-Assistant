package com.example.healthassistant.repository;

import com.example.healthassistant.model.MedicineDosages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineDosagesRepository extends JpaRepository<MedicineDosages, Long> {
}
