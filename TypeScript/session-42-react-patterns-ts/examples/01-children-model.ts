type PanelProps = { title: string; children: string };
function render(p: PanelProps): string {
  return `${p.title}:${p.children}`;
}
console.log(render({ title: "A", children: "body" }));
