import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import { Projects } from './project/projects';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Projects],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = 'Taskmaster';

}
