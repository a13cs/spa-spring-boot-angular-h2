import { Task } from './task.model';

export interface Project {
  id: string;
  name: string;
  description: string;
  tasks: Task[]; // Array<Task>
}
