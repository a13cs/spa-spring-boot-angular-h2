import { Component } from '@angular/core';
import { ProjectTitle } from './project-title/project-title';
import { ProjectTaskList } from './project-task-list/project-task-list';
import { ProgressBar } from './progress-bar/progress-bar';

@Component({
  selector: 'app-project',
  imports: [ProjectTitle, ProjectTaskList, ProgressBar],
  templateUrl: './project.html',
  styleUrl: './project.css',
})
export class Project {}
