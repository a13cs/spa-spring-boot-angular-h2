import { Component, inject, OnInit } from '@angular/core';
import { ProjectService } from '../../project-service';
import { Project } from '../../project.model';
import { AsyncPipe, DatePipe, JsonPipe } from '@angular/common';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-project-task-list',
  imports: [DatePipe, JsonPipe, AsyncPipe],
  templateUrl: './project-task-list.html',
  styleUrl: './project-task-list.css',
})
export class ProjectTaskList implements OnInit {
  project: Project = {
    id: '',
    name: '',
    description: '',
    tasks: [],
  };

  p$: Observable<Project[]>;
  projectService: ProjectService = inject(ProjectService);


  constructor() {
    this.p$ = this.projectService.getProjects();

    this.p$.subscribe((projects) => {
      var p = projects[0];

      // this.projectService.getProject(p.id).subscribe((p) => {
      this.project.id = p.id;
      this.project.name = p.name;
      this.project.description = p.description;
      this.project.tasks = p.tasks;
      // });
    });
  }

  ngOnInit(): void {

  }
}
