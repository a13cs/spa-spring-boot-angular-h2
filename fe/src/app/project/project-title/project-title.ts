import { Component, Input, OnChanges, signal, SimpleChanges } from '@angular/core';
import { Project } from '../../project.model';


@Component({
  selector: 'app-project-title',
  imports: [],
  templateUrl: './project-title.html',
  styleUrl: './project-title.css',
})
export class ProjectTitle implements OnChanges {
  title = signal('');
  description = signal('');

  @Input() selectedProject: Project | undefined;


  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedProject'] && changes['selectedProject'].currentValue){
      let p = this.selectedProject;

      console.log(p)
      if (p) {
        this.title.set(p.name);
        this.description.set(p.description);
      }
    }
  }
}
