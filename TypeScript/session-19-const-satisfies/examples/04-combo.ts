const routes = {
  home: "/",
  user: "/users/:id",
} as const satisfies Record<string, string>;
type RouteKey = keyof typeof routes;
console.log(routes.home satisfies string);
const key: RouteKey = "home";
console.log(key, routes[key]);
