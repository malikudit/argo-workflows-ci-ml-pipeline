package com.udit.crudapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udit.crudapp.model.Workflow;
import com.udit.crudapp.repository.WorkflowRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class WorkflowController {

	@Autowired
	WorkflowRepository workflowRepository;

	@GetMapping("/workflows")
	public ResponseEntity<List<Workflow>> getAllWorkflows(@RequestParam(required = false) String name) {
		try {
			List<Workflow> workflows = new ArrayList<Workflow>();

			if (name == null) {
				workflowRepository.findAll().forEach(workflows::add);
			} else {
				workflowRepository.findByNameContaining(name).forEach(workflows::add);
			}
			if (workflows.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(workflows, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/workflows/{id}")
	public ResponseEntity<Workflow> getWorkflowById(@PathVariable("id") long id) {
		Optional<Workflow> workflowData = workflowRepository.findById(id);

		if (workflowData.isPresent()) {
			return new ResponseEntity<>(workflowData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/workflows")
	public ResponseEntity<Workflow> createWorkflow(@RequestBody Workflow workflow) {
		try {
			Workflow _workflow = workflowRepository
					.save(new Workflow(workflow.getName(), workflow.getDescription(), false));

			// file uploader

			// workflow manager

			String filename = "workflows/" + workflow.getName() + ".yml";
			PrintWriter out = new PrintWriter(filename);
			out.println(workflow.getDescription());
			out.close();
			// call function

			return new ResponseEntity<>(_workflow, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/workflows/{id}")
	public ResponseEntity<Workflow> updateWorkflow(@PathVariable("id") long id, @RequestBody Workflow workflow) {
		Optional<Workflow> workflowData = workflowRepository.findById(id);

		if (workflowData.isPresent()) {
			Workflow _workflow = workflowData.get();
			_workflow.setName(workflow.getName());
			_workflow.setDescription(workflow.getDescription());
			_workflow.setCompleted(workflow.isCompleted());
			return new ResponseEntity<>(workflowRepository.save(_workflow), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/workflows/{id}")
	public ResponseEntity<HttpStatus> deleteWorkflow(@PathVariable("id") long id) {
		try {
			workflowRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/workflows")
	public ResponseEntity<HttpStatus> deleteAllWorkflows() {
		try {
			workflowRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/workflows/completed")
	public ResponseEntity<List<Workflow>> findByCompleted() {
		try {
			List<Workflow> workflows = workflowRepository.findByCompleted(true);
			if (workflows.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(workflows, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
