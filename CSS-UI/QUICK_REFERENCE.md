# راهنمای سریع CSS-UI

جدول کامل جلسات: [SYLLABUS.md](./SYLLABUS.md)

## Flexbox
```css
.container { display: flex; gap: 1rem; justify-content: space-between; align-items: center; }
.item { flex: 1 1 0; }
```

## Grid
```css
.grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(16rem, 1fr)); gap: 1rem; }
```

## CSS Variables
```css
:root { --color-brand: #1d4ed8; }
.btn { background: var(--color-brand); }
```

## Sass مدرن
```scss
@use "variables" as *;
@mixin mq($bp) { @media (min-width: $bp) { @content; } }
```

## Bootstrap (نمونه)
```html
<div class="container">
  <div class="row g-3">
    <div class="col-md-6"><div class="card p-3">A</div></div>
    <div class="col-md-6"><div class="card p-3">B</div></div>
  </div>
</div>
```

## Tailwind (نمونه)
```html
<section class="mx-auto max-w-5xl px-4 py-16">
  <h1 class="text-3xl font-bold text-slate-900 md:text-5xl">عنوان</h1>
  <button class="mt-6 rounded-full bg-sky-500 px-5 py-2 text-white hover:bg-sky-600">شروع</button>
</section>
```

## Bulma (نمونه)
```html
<section class="hero is-primary">
  <div class="hero-body">
    <p class="title">عنوان</p>
    <p class="subtitle">زیرعنوان</p>
  </div>
</section>
```
