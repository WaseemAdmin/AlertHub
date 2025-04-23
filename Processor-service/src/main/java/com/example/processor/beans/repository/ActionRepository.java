package com.example.processor.beans.repository;

import com.example.processor.beans.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ActionRepository extends JpaRepository<ActionEntity, Long> {

    List<ActionEntity> findByIsEnabledTrueAndIsDeletedFalse();

}
