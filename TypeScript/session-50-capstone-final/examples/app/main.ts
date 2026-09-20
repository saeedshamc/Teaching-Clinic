import { addTask, completeTask, listOpen } from "../domain/task-service.js";
import type { Task } from "../types/task.js";

let tasks: Task[] = [];
const added = addTask(tasks, "مرور جلسه ۴۹");
if (!added.ok) throw new Error(added.error);
tasks = added.value;

const added2 = addTask(tasks, "ساخت Vite شخصی");
if (!added2.ok) throw new Error(added2.error);
tasks = added2.value;

const firstId = tasks[0]!.id;
const done = completeTask(tasks, firstId);
if (!done.ok) throw new Error(done.error);
tasks = done.value;

console.log("all:", tasks);
console.log("open:", listOpen(tasks));
console.log("capstone final OK — npm run typecheck");
