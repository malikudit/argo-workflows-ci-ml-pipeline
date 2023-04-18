import { Component, OnInit } from '@angular/core';

import { Workflow } from 'src/app/models/workflow.model';
import { WorkflowService } from 'src/app/services/workflow.service';

@Component({
  selector: 'app-workflows-list',
  templateUrl: './workflows-list.component.html',
  styleUrls: ['./workflows-list.component.css']
})

export class WorkflowsListComponent implements OnInit {

  workflows?: Workflow[];
  currentWorkflow: Workflow = {};
  currentIndex = -1;
  name = '';

  constructor(private workflowService: WorkflowService) { }

  ngOnInit(): void {
    this.retrieveWorkflows();
  }

  retrieveWorkflows(): void {
    this.workflowService.getAll()
      .subscribe(
        data => {
          this.workflows = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveWorkflows();
    this.currentWorkflow = {};
    this.currentIndex = -1;
  }

  setActiveWorkflow(workflow: Workflow, index: number): void {
    this.currentWorkflow = workflow;
    this.currentIndex = index;
  }

  removeAllWorkflows(): void {
    this.workflowService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }

  searchName(): void {
    this.currentWorkflow = {};
    this.currentIndex = -1;

    this.workflowService.findByName(this.name)
      .subscribe(
        data => {
          this.workflows = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
}
