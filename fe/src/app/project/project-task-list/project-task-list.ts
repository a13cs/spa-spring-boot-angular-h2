import { Component, inject, Input, signal, SimpleChanges, WritableSignal } from '@angular/core';
import { ProjectService } from '../../project-service';
import { Project } from '../../project.model';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-project-task-list',
  imports: [DatePipe],
  templateUrl: './project-task-list.html',
  styleUrl: './project-task-list.css',
})
export class ProjectTaskList {
  @Input() selectedProject: Project | undefined;

  project: WritableSignal<Project> = signal({
    id: '',
    name: '',
    description: '',
    tasks: [],
  });

  projectService: ProjectService = inject(ProjectService);

  constructor() {
    // this.projectService.getProjects().subscribe((projects) => {
    //   this.project.set(projects[0]);
    // this.projectService.getProject(this.project().id).subscribe((p) => {
    //   console.log(p)
    // });
    // });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedProject'] && changes['selectedProject'].currentValue) {
      let p = this.selectedProject;

      // console.log(p);
      if (p) {
        this.project.set(p);
      }
    }
  }

  handleCheckbox(id: number) {
    let tasks = this.project().tasks;
    let index = tasks.findIndex((task) => task.id === id);
    tasks[index].completed = !tasks[index].completed;

    // this.projectService.updateTask(t);
  }

  deleteTask(id: number) {
    let tasks = this.project().tasks;
    let index = tasks.findIndex((task) => task.id === id);
    tasks.splice(index,1)
    this.projectService.deleteTask(id)
  }

}
