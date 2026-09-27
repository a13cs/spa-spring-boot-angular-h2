import { Component } from '@angular/core';
import { ProjectTitle } from './project-title/project-title';
import { ProjectTaskList } from './project-task-list/project-task-list';
import { ProgressBar } from './progress-bar/progress-bar';
import { ProjectsList } from './projects-list/projects-list';
import { Project } from '../project.model';

@Component({
  selector: 'app-projects',
  imports: [ProjectsList, ProjectTitle, ProjectTaskList, ProgressBar],
  templateUrl: './projects.html',
  styleUrl: './projects.css',
})
export class Projects {
  p : Project | undefined

}
