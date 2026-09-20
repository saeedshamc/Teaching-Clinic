type User = { id: number; name: string; email?: string };

type FeatureFlags = Record<string, boolean>;
type RoleLabel = Record<"admin" | "user", string>;
type FullUser = Required<User>;

const flags: FeatureFlags = { darkMode: true, beta: false };
const roles: RoleLabel = { admin: "مدیر", user: "کاربر" };
const full: FullUser = { id: 1, name: "سارا", email: "s@ex.com" };

console.log(flags, roles, full);
