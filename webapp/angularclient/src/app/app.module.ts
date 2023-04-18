import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddWorkflowComponent } from './components/add-workflow/add-workflow.component';
import { WorkflowDetailsComponent } from './components/workflow-details/workflow-details.component';
import { WorkflowsListComponent } from './components/workflows-list/workflows-list.component';

import  { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    AddWorkflowComponent,
    WorkflowDetailsComponent,
    WorkflowsListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
