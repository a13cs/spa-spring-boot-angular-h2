import { Component, inject } from '@angular/core';
import { ProjectService } from '../../project-service';
import { Observable } from 'rxjs';
import { Project } from '../../project.model';

@Component({
  selector: 'app-project-title',
  imports: [],
  templateUrl: './project-title.html',
  styleUrl: './project-title.css',
})
export class ProjectTitle {

  title: string = ''
  description: string = ''
  project$: Observable<Project[]>


  projectService: ProjectService = inject(ProjectService)

  constructor() {
    this.project$ = this.projectService.getProjects();

    this.project$.subscribe((projects) => {
      let p = projects[0];
      this.title = p.name;
      this.description = p.description;
    });

  }

}
