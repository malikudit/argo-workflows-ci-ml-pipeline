import { Component, OnInit } from '@angular/core';

import { Workflow } from 'src/app/models/workflow.model';
import { WorkflowService } from 'src/app/services/workflow.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-workflow-details',
  templateUrl: './workflow-details.component.html',
  styleUrls: ['./workflow-details.component.css']
})

export class WorkflowDetailsComponent implements OnInit {

  currentWorkflow: Workflow = {
    name: '',
    description: '',
    completed: false
  };
  message = '';

  constructor(
    private workflowService: WorkflowService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.message = '';
    this.getWorkflow(this.route.snapshot.params.id);
  }

  getWorkflow(id: string): void {
    this.workflowService.get(id)
      .subscribe(
        data => {
          this.currentWorkflow = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  updateCompleted(status: boolean): void {
    const data = {
      name: this.currentWorkflow.name,
      description: this.currentWorkflow.description,
      completed: status
    };

    this.message = '';

    this.workflowService.update(this.currentWorkflow.id, data)
      .subscribe(
        response => {
          this.currentWorkflow.completed = status;
          console.log(response);
          this.message = response.message ? response.message : 'The status was updated successfully!';
        },
        error => {
          console.log(error);
        });
  }

  updateWorkflow(): void {
    this.message = '';

    this.workflowService.update(this.currentWorkflow.id, this.currentWorkflow)
      .subscribe(
        response => {
          console.log(response);
          this.message = response.message ? response.message : 'This workflow was updated successfully!';
        },
        error => {
          console.log(error);
        });
  }

  deleteWorkflow(): void {
    this.workflowService.delete(this.currentWorkflow.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/workflows']);
        },
        error => {
          console.log(error);
        });
  }
}
