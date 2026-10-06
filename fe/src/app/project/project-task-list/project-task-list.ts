import {
  Component,
  inject,
  input,
} from '@angular/core';
import { ProjectService } from '../../project-service';
import { Project } from '../../project.model';
import { DatePipe } from '@angular/common';
import { Task } from '../../task.model';
import { TaskForm } from '../task-form/task-form';


export const emptyProject: Project = {
  id: '',
  name: '',
  description: '',
  progress: 0,
  tasks: [],
};

@Component({
  selector: 'app-project-task-list',
  imports: [DatePipe, TaskForm],
  templateUrl: './project-task-list.html',
  styleUrl: './project-task-list.css',
})
export class ProjectTaskList {
  project = input<Project>(emptyProject)

  showModal = false;

  projectService: ProjectService = inject(ProjectService);

  currentTask: Task | undefined = undefined
  formType: string = ''

  handleCheckbox(id: number) {
    let tasks = this.project().tasks;
    let index = tasks.findIndex((task) => task.id === id);
    let t = tasks[index];
    t.completed = !t.completed;

    this.projectService.updateTask(this.project().id, t).subscribe(t => {
      console.log('Updated', t)
    });
  }

  deleteTask(id: number) {
    let tasks = this.project().tasks;
    let index = tasks.findIndex((task) => task.id === id);
    tasks.splice(index,1)
    this.projectService.deleteTask(id)
  }

  handleSubmit(task : Task) {
    let tasks = this.project().tasks;
    if(this.formType === 'ADD') {
      tasks.push(task);
    } else if (this.formType === 'UPDATE'){
      let i = tasks.findIndex(t => t.id === task.id);
      tasks[i] = task
    }

  }

}
