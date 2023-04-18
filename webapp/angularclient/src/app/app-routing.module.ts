import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { WorkflowsListComponent } from './components/workflows-list/workflows-list.component';
import { WorkflowDetailsComponent } from './components/workflow-details/workflow-details.component';
import { AddWorkflowComponent } from './components/add-workflow/add-workflow.component';

const routes: Routes = [
  { path: '', redirectTo: 'workflows', pathMatch: 'full' },
  { path: 'workflows', component: WorkflowsListComponent },
  { path: 'workflows/:id', component: WorkflowDetailsComponent },
  { path: 'add', component: AddWorkflowComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
