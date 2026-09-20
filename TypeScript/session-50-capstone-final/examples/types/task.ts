export type Task = {
  id: string;
  title: string;
  done: boolean;
  createdAt: number;
};

export type Result<T> =
  | { ok: true; value: T }
  | { ok: false; error: string };
