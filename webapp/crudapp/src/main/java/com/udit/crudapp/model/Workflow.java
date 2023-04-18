package com.udit.crudapp.model;

import javax.persistence.*;

@Entity
@Table(name = "workflows")
public class Workflow {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description", columnDefinition = "text")
	private String description;

	@Column(name = "completed")
	private boolean completed;

	public Workflow() {

	}

	public Workflow(String name, String description, boolean completed) {
		this.name = name;
		this.description = description;
		this.completed = completed;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean isCompleted) {
		this.completed = isCompleted;
	}

	@Override
	public String toString() {
		return "Workflow [id=" + id + ", name=" + name + ", desc=" + description + ", completed=" + completed + "]";
	}
}
