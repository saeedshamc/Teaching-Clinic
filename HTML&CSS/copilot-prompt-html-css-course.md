# Prompt for GitHub Copilot — Generate a Full HTML & CSS Course (50 Sessions)

I want you to build a complete, project-based HTML & CSS course for me, made up of 50 sessions, going from absolute zero to an advanced, professional level. All explanations must be written in **English**, must be precise, technically accurate, and error-free from the very first session to the last. The output must include real working code files for each session, plus a clear, well-organized README for every session.

## Overall Folder Structure

Create a project structured like this:

```
html-css-course/
├── README.md                     (course overview, prerequisites, how to use this repo)
├── session-01-intro/
│   ├── README.md
│   ├── examples/
│   │   ├── index.html
│   │   ├── style.css
│   │   └── ...
│   └── exercises/
│       └── exercise-01.html      (exercise, with a separate or collapsible solution)
├── session-02-.../
│   └── ... (same structure)
...
├── session-50-.../
│   └── ...
```

Each session must live in its own clearly named folder (e.g. `session-14-flexbox-basics`).

## Git Requirement

- Initialize a Git repository at the project root with `git init`, and add a proper `.gitignore` for a static HTML/CSS project.
- After each session is fully completed (README + `examples/` + `exercises/` written and verified), make **one separate commit** for that session.
- Commit message format, e.g.: `git commit -m "session 01: introduction to HTML and setting up the environment"`
- There must be exactly 50 separate commits (plus one initial commit for the project skeleton) in the history — never combine multiple sessions into a single commit.
- At the end, show the output of `git log --oneline` so the commit history can be verified.

## What Every Session Must Contain

1. **A dedicated README.md** for the session, including:
   - Session title and clear learning objectives
   - A from-zero conceptual explanation — write as if the reader has no prior knowledge of HTML/CSS or web development
   - A **line-by-line or block-by-block explanation** of every code example, placed right next to that part of the code — not just a generic paragraph at the top of the file
   - Common mistakes and best practices relevant to that specific topic
   - A short summary at the end of the session
   - Links to the corresponding files inside `examples/`
   - At least 1–2 hands-on exercises, with difficulty labeled (easy / intermediate)

2. **Real, working code files** (`.html` and `.css`) inside `examples/`:
   - Every example must actually open correctly in a browser with no console errors and valid markup (should pass W3C HTML/CSS validation logic — no unclosed tags, no invalid property values, etc.)
   - Include inline comments in the code itself (not only in the README) so the code is understandable on its own
   - Code must be clean, consistently formatted, and follow modern best practices (semantic HTML, no deprecated tags/attributes)
   - Actually build and mentally/logically verify every example so nothing is broken or half-finished

3. **Exercises** inside `exercises/`: the task description as a comment at the top of the file, and the solution kept separate or inside a collapsible section in the README so it isn't spoiled immediately.

## Suggested Curriculum — 50 Sessions (You may adjust slightly if something makes more technical sense, but keep the same total count and progressive structure)

1. Introduction to the web, how browsers work, setting up a code editor and folder structure
2. HTML document structure: doctype, html, head, body, basic tags
3. Text elements: headings, paragraphs, line breaks, horizontal rules
4. Text formatting: bold, italic, strong, em, and semantic text tags
5. Lists: ordered, unordered, and description lists
6. Links and anchors, relative vs absolute paths
7. Images: img tag, alt text, and basic responsive image attributes
8. Tables: structure, headers, rows, cells, spanning
9. Forms part 1: input types, labels, and basic form structure
10. Forms part 2: select, textarea, checkboxes, radio buttons, validation attributes
11. Semantic HTML5: header, nav, main, section, article, aside, footer
12. HTML metadata: meta tags, favicon, SEO-relevant basics
13. Introduction to CSS: what it is, how to link it, inline vs internal vs external
14. CSS selectors: element, class, id, attribute selectors
15. CSS selectors part 2: combinators, pseudo-classes, pseudo-elements
16. The box model: margin, border, padding, content
17. Colors, backgrounds, and units (px, %, em, rem)
18. Typography: font-family, font-size, line-height, web-safe fonts
19. CSS specificity, the cascade, and inheritance
20. Display property: block, inline, inline-block, none
21. Positioning: static, relative, absolute, fixed, sticky
22. Flexbox basics: container properties
23. Flexbox advanced: item properties, common layout patterns
24. CSS Grid basics: grid-template-columns/rows, gap
25. CSS Grid advanced: grid-template-areas, alignment, nested grids
26. Building a responsive navbar (project-based session)
27. Media queries and responsive design fundamentals
28. Mobile-first design workflow
29. CSS variables (custom properties)
30. Transitions and simple animations with @keyframes
31. Transforms: translate, rotate, scale, skew
32. Shadows and gradients (box-shadow, text-shadow, linear/radial-gradient)
33. CSS units deep dive: viewport units (vh, vw), calc()
34. Building a responsive image gallery (project-based session)
35. Building a pricing table with Flexbox/Grid (project-based session)
36. Forms styling: custom-styled inputs, buttons, focus states
37. CSS pseudo-classes for interactivity: hover, focus, active, nth-child
38. Accessibility basics (a11y): semantic markup, alt text, ARIA basics, color contrast
39. CSS methodology: BEM naming convention
40. Introduction to CSS resets and normalize.css
41. Multi-column layouts and CSS columns property
42. Building a landing page layout (project-based session)
43. Building a blog post layout (project-based session)
44. Responsive typography techniques (fluid type, clamp())
45. CSS best practices: file organization, avoiding !important, maintainability
46. Introduction to Sass/SCSS basics (variables, nesting) as an optional preprocessor topic
47. Debugging CSS with browser DevTools
48. Performance basics: minimizing CSS, avoiding layout thrashing, critical CSS concept
49. Cross-browser considerations and basic browser compatibility checks
50. Final project: build a complete, fully responsive multi-page website (Home, About, Services/Portfolio, Contact) using everything learned in the previous 49 sessions

## Root README Requirements

The root `README.md` must include:
- A short course introduction and target audience
- Prerequisites (a code editor, a modern browser, no prior programming knowledge required)
- How to open and run the examples (e.g. via Live Server or simply opening the HTML file)
- A complete list of all 50 sessions with links to each folder
- A suggested learning pace (e.g. sessions per week)
- A short explanation of the Git commit structure used in this repo

## Quality Requirements — Critical

- All explanations must be in clear, precise, professional English — no ambiguity, no filler, no vague statements
- Everything must be technically accurate: no outdated tags (e.g. `<center>`, `<font>`), no deprecated CSS properties, no incorrect claims about browser behavior
- Every single code example must actually work correctly with zero errors — validate the logic of each file as if it were opened in a real browser before considering the session complete
- Sessions must build progressively: later sessions must rely on and reference concepts from earlier sessions
- Do not skip steps or leave any session incomplete or as a stub — every one of the 50 sessions must be fully written out with the same level of depth and quality
- Exactly one Git commit per completed session, as described above
- At the end of the whole task, show the complete folder tree (via `tree` or equivalent) and the full `git log --oneline` output so the entire course structure can be verified end to end
