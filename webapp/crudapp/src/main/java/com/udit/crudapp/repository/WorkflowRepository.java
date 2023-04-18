package com.udit.crudapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udit.crudapp.model.Workflow;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
  List<Workflow> findByCompleted(boolean completed);

  List<Workflow> findByNameContaining(String name);
}
