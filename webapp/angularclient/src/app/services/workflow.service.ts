import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Workflow } from '../models/workflow.model';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8080/api/workflows';

@Injectable({
  providedIn: 'root'
})

export class WorkflowService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Workflow[]> {
    return this.http.get<Workflow[]>(baseUrl);
  }

  get(id: any): Observable<Workflow> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByName(name: any): Observable<Workflow[]> {
    return this.http.get<Workflow[]>(`${baseUrl}?name=${name}`);
  }
}
