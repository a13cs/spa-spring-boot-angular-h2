import { Injectable } from '@angular/core';
import { HttpClient, httpResource } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from './project.model';

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


}
