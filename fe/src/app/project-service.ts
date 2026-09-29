import { Injectable } from '@angular/core';
import { HttpClient, httpResource } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from './project.model';
import { Task } from './task.model';

const BASE_URL: String = 'http://localhost:8080/api';


@Injectable({
  providedIn: 'root',
})
export class ProjectService {

  constructor(private http: HttpClient) {

  }
  projectsResource = httpResource<Project[]>(
    () => BASE_URL + "/projects/all",
    {defaultValue : []}
  );

  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${BASE_URL}/projects/all`);
  }

  getProject(id: string) : Observable<Project> {
    return this.http.get<Project>(`${BASE_URL}/projects/` + id);
  }

  deleteTask(id: number) {
    this.http.delete(`${BASE_URL}/tasks/` + id).subscribe(() => console.log('Deleted task ' + id))
  }

  updateTask(projectId: string, task: Task) {
      this.http.put<Task>(`${BASE_URL}/tasks/` + projectId + '/' + task.id, task)
        .subscribe((t) => {
        console.log('Updated task ' + JSON.stringify(t))
      })
  }

  saveTask(projectId: string, task: Task) {
    return this.http
      .post<Task>(`${BASE_URL}/tasks/` + projectId, task)
  }

}
