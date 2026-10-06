import { Component, inject, output, signal, WritableSignal } from '@angular/core';
import { Project } from '../../project.model';
import { ProjectService } from '../../project-service';
import { emptyProject } from '../project-task-list/project-task-list';
import { ProjectForm } from '../project-form/project-form';

@Component({
  selector: 'app-projects-list',
  imports: [ProjectForm],
  templateUrl: './projects-list.html',
  styleUrl: './projects-list.css',
})
export class ProjectsList {
  project: WritableSignal<Project> = signal(emptyProject);
  selectedProject = output<Project | undefined>();
  showModal: boolean = false;

  formType: string = '';
  currentProject = emptyProject;

  projects: WritableSignal<Project[]> = signal([]);
  projectService: ProjectService = inject(ProjectService);

  constructor() {
    this.projectService.getProjects().subscribe((projects) => {
      this.projects.set(projects);
    });
  }

  selectProject(p: Project) {
    this.selectedProject.emit(p);
    this.currentProject = p;
  }

  handleSubmit(p: Project) {
    if (this.formType === 'ADD') {
      this.projects().push(p)
    }
    if (this.formType === 'UPDATE') {
      let i = this.projects().findIndex(project => project.id === p.id);
      this.projects()[i] = p
    }

    this.currentProject = p
    this.selectedProject.emit(p)
  }

  deleteProject(p: Project) {
    this.projectService.deleteProject(p.id);

    let i = this.projects().findIndex((project) => project.id === p.id);
    let all = this.projects();
    all.splice(i, 1);
    this.projects.set(all);

    if (this.currentProject && this.currentProject.id === p.id) {
      this.selectedProject.emit(undefined);
    }
  }
}
