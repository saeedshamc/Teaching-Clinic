import type { Result, Task } from "../types/task.js";

export function addTask(tasks: Task[], title: string): Result<Task[]> {
  const trimmed = title.trim();
  if (!trimmed) return { ok: false, error: "empty title" };
  const task: Task = {
    id: `t-${Date.now()}-${Math.random().toString(16).slice(2)}`,
    title: trimmed,
    done: false,
    createdAt: Date.now(),
  };
  return { ok: true, value: [...tasks, task] };
}

export function completeTask(tasks: Task[], id: string): Result<Task[]> {
  if (!tasks.some((t) => t.id === id)) return { ok: false, error: "not found" };
  return {
    ok: true,
    value: tasks.map((t) => (t.id === id ? { ...t, done: true } : t)),
  };
}

export function listOpen(tasks: Task[]): Task[] {
  return tasks.filter((t) => !t.done);
}
