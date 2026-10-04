import { Component, EventEmitter, inject, input, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Task } from '../../task.model';
import { ProjectService } from '../../project-service';

export const emptyTask: Task = {
  id: 0,
  name: '',
  description: '',
  dueDate: new Date(),
  completed: false,
};

@Component({
  selector: 'app-task-form',
  imports: [ReactiveFormsModule],
  templateUrl: './task-form.html',
  styleUrl: './task-form.css',
})
export class TaskForm implements OnInit {
  taskForm: FormGroup;
  @Output() formSubmit: EventEmitter<any> = new EventEmitter<Task>();
  @Output() formCancel: EventEmitter<any> = new EventEmitter<'CANCEL'>();
  @Input() currentProjectId: string = '';
  formType = input<string>();
  currentTask = input<Task>();

  projectService: ProjectService = inject(ProjectService);

  constructor(private fb: FormBuilder) {
    this.taskForm = this.fb.group({
      id:[0],
      name: ['', Validators.required],
      description: [''],
      dueDate: ['', Validators.required],
      completed: false,
    });
  }
  ngOnInit(): void {
    if (this.formType() === 'UPDATE') {
      this.taskForm.patchValue({ ...this.currentTask() });
    }
    if (this.formType() === 'ADD') {
      this.taskForm.patchValue({ ...emptyTask, id: null});
    }
  }

  handleCancel() {
    this.formCancel.emit('CANCEL');
  }

  handleSubmit() {
    let task = this.taskForm.value;

    if (this.taskForm.valid) {
      if (this.formType() === 'ADD') {
        this.projectService.saveTask(this.currentProjectId, { ...task }).subscribe((t) => {
          console.log('Saved ', t);
          this.formSubmit.emit(t);
        });
      }
      if (this.formType() === 'UPDATE') {
        this.projectService.updateTask(this.currentProjectId, { ... task}).subscribe(t => {
          console.log('Updated', task)
          this.formSubmit.emit(t)
        })
      }
    }
  }
}
