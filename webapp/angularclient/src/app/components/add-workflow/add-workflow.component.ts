import { Component, OnInit } from '@angular/core';

import { Workflow } from 'src/app/models/workflow.model';
import { WorkflowService } from 'src/app/services/workflow.service';

import { HttpClient } from '@angular/common/http';

import { saveAs } from 'file-saver';
import * as yaml from 'js-yaml';

@Component({
  selector: 'app-add-workflow',
  templateUrl: './add-workflow.component.html',
  styleUrls: ['./add-workflow.component.css']
})
export class AddWorkflowComponent implements OnInit {

  workflow: Workflow = {
    name: '',
    description: '',
    completed: false
  };
  submitted = false;

  constructor(
    private workflowService: WorkflowService,
    private http: HttpClient
    ) { }

  ngOnInit(): void {
  }

  saveWorkflow(): void {
    const data = {
      name: this.workflow.name,
      description: this.workflow.description
    };

    this.workflowService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
          if (this.workflow.name && this.workflow.description) {
            this.saveYamlFile(this.workflow.name, this.workflow.description);
          }
        },
        error => {
          console.log(error);
        });
  }

  saveYamlFile(name: string, description: string): void {
    const yamlString = yaml.dump({ description });
    const blob = new Blob([yamlString], { type: 'text/yaml;charset=utf-8' });
    saveAs(blob, `${name}.yaml`);
  }

  newWorkflow(): void {
    this.submitted = false;
    this.workflow = {
      name: '',
      description: '',
      completed: false
    };
  }
}

