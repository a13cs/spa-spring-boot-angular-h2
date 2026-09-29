import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Task } from '../../task.model';
import { ProjectService } from '../../project-service';



@Component({
  selector: 'app-task-form',
  imports: [ReactiveFormsModule],
  templateUrl: './task-form.html',
  styleUrl: './task-form.css',
})
export class TaskForm {
  taskForm: FormGroup
  @Output() formSubmit: EventEmitter<any> = new EventEmitter<Task>()
  @Output() formCancel: EventEmitter<any> = new EventEmitter<'CANCEL'>()
  @Input() currentProjectId: string = '';

  projectService: ProjectService = inject(ProjectService)

  newTask : Task = {
    id: 0,
    name: '',
    description: '',
    dueDate: new Date(),
    completed: false
  }

  constructor(private fb: FormBuilder) {
      this.taskForm = this.fb.group({
        name: ['', Validators.required],
        description: [''],
        dueDate: ['', Validators.required],
        completed: false
      });
  }

  handleCancel() {
    this.formCancel.emit("CANCEL")
  }

  handleSubmit() {
    let task = this.taskForm.value;

    if (this.taskForm.valid) {
      this.projectService
        .saveTask(this.currentProjectId, { ...task })
        .subscribe((t) => {
          console.log('Saved ', t);
          this.formSubmit.emit(t)
        });
    }
  }

}
