// یادداشت نوعی — بدون وابستگی React در این پوشه.
// در Vite: import { useState, type ReactNode } from "react";

export type PanelPropsModel = {
  title: string;
  /** در React واقعی: React.ReactNode */
  children: string;
};

export type NullUserState = { id: number; name: string } | null;

export function describeState(user: NullUserState): string {
  if (user === null) return "مهمان";
  return user.name;
}

console.log(describeState(null));
console.log(describeState({ id: 1, name: "سارا" }));
