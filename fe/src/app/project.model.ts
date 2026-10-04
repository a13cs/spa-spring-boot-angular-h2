import { Task } from './task.model';

export interface Project {
  id: string;
  name: string;
  description: string;
  progress: number,
  tasks: Task[];
}
