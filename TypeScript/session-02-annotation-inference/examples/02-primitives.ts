// جلسه ۲ — primitiveها
const title: string = "کلینیک";
const session: number = 2;
const strict: boolean = true;
const empty: null = null;
const missing: undefined = undefined;
const big: bigint = 2n;

console.log({ title, session, strict, empty, missing, big });

const maybeNaN: number = Number("abc");
console.log("از نظر نوع number است:", maybeNaN);
console.log("از نظر منطق مفید است؟", Number.isFinite(maybeNaN));

let label: string = "ok";
console.log("label فعلی:", label);
// label = null; // در strict رد می‌شود — null داخل string نیست
