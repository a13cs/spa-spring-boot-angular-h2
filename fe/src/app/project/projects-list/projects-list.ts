import { Component, EventEmitter, inject, Output, signal, WritableSignal } from '@angular/core';
import { Project } from '../../project.model';
import { ProjectService } from '../../project-service';

@Component({
  selector: 'app-projects-list',
  imports: [],
  templateUrl: './projects-list.html',
  styleUrl: './projects-list.css',
})
export class ProjectsList {
  project: WritableSignal<Project> = signal({
    id: '',
    name: '',
    description: '',
    tasks: [],
  });
  @Output() selectedProject: EventEmitter<any> = new EventEmitter()

  projects: WritableSignal<Project[]> = signal([]);
  projectService: ProjectService = inject(ProjectService);

  constructor() {
    this.projectService.getProjects().subscribe((projects) => {
      this.projects.set(projects);
      this.project.set(projects[0]);
    });
  }

  log(p: Project) {
    // console.log(p);
    this.selectedProject.emit(p)
  }
}
