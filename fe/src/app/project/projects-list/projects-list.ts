import { Component, inject, output, signal, WritableSignal } from '@angular/core';
import { Project } from '../../project.model';
import { ProjectService } from '../../project-service';
import { emptyProject } from '../project-task-list/project-task-list';

@Component({
  selector: 'app-projects-list',
  imports: [],
  templateUrl: './projects-list.html',
  styleUrl: './projects-list.css',
})
export class ProjectsList {
  project: WritableSignal<Project> = signal(emptyProject);
  selectedProject = output<Project>()

  projects: WritableSignal<Project[]> = signal([]);
  projectService: ProjectService = inject(ProjectService);

  constructor() {
    this.projectService.getProjects().subscribe((projects) => {
      this.projects.set(projects);
      // this.project.set(projects[0]);
    });
  }

  selectProject(p: Project) {
    // console.log(p);
    this.selectedProject.emit(p)
  }
}
