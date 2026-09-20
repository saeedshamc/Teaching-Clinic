/** مرجع متنی — typecheck دوره این فایل را به‌عنوان TS معمولی چک می‌کند */
export type UserCardProps = {
  name: string;
  age?: number;
  onSelect: (id: number) => void;
};

export function describeCard(props: UserCardProps): string {
  return `${props.name}#${props.age ?? 0}`;
}

console.log(describeCard({ name: "سارا", age: 20, onSelect: () => undefined }));
