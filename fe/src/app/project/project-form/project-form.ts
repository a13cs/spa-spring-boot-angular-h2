import { Component, inject, input, OnInit, output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Project } from '../../project.model';
import { emptyProject } from '../project-task-list/project-task-list';
import { ProjectService } from '../../project-service';

@Component({
  selector: 'app-project-form',
  imports: [ReactiveFormsModule],
  templateUrl: './project-form.html',
  styleUrl: './project-form.css',
})
export class ProjectForm implements OnInit {
  formType = input<string>();
  currentProject = input<Project>(emptyProject);

  formCancel = output<'CANCEL'>();
  formSubmit = output<Project>();

  projectForm: FormGroup;
  projectService = inject(ProjectService)

  constructor(private fb: FormBuilder) {
    this.projectForm = this.fb.group({
      id: [null],
      name: ['', Validators.required],
      description: [''],
      progress: [0],
      tasks:[[]]
    });
  }

  ngOnInit(): void {
    if (this.formType() === 'UPDATE') {
      this.projectForm.patchValue({... this.currentProject()})
    }
  }

  handleCancel() {
    this.formCancel.emit('CANCEL');
  }

  handleSubmit() {
    if (this.projectForm.valid) {
      let value = this.projectForm.value;
      if (this.formType() === 'ADD') {
        this.projectService.saveProject({...value}).subscribe(p => {
          console.log('ADD', p);
          this.formSubmit.emit(p)
        })
      }
      if (this.formType() === 'UPDATE') {
        this.projectService.updateProject(this.currentProject().id, {...value}).subscribe(p => {
          console.log('UPDATE', p);
          this.formSubmit.emit(p)
        })
      }
    }
  }
}
