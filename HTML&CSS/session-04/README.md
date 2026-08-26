# Session 4: Text Formatting

## Learning Objectives

By the end of this session, you will understand:
- The difference between visual and semantic text formatting
- How to use bold and italic text correctly
- Semantic text elements and their importance
- When to use `<b>` vs `<strong>` and `<i>` vs `<em>`
- Best practices for text formatting

## Visual vs. Semantic Formatting

HTML provides two types of text formatting:

### Visual Formatting
- **Purpose**: Changes how text looks
- **Examples**: `<b>`, `<i>`
- **Meaning**: No semantic meaning, just visual

### Semantic Formatting
- **Purpose**: Conveys meaning or importance
- **Examples**: `<strong>`, `<em>`
- **Meaning**: Has semantic meaning beyond appearance

**Why semantic formatting matters**:
- Accessibility: Screen readers announce semantic elements differently
- SEO: Search engines understand the importance of semantic text
- Maintainability: Clear intent in your code

## Bold Text

### `<b>` Element (Visual Bold)

```html
<p>This text is <b>bold</b> for visual emphasis only.</p>
```

- **Purpose**: Purely visual bold text
- **Semantic meaning**: None
- **Use when**: The bold text doesn't have special importance

**Example - Product Name**:
```html
<p>I bought a <b>Samsung</b> television yesterday.</p>
```

### `<strong>` Element (Semantic Bold)

```html
<p>This text is <strong>important</strong> and has semantic meaning.</p>
```

- **Purpose**: Indicates text with strong importance
- **Semantic meaning**: Indicates importance or urgency
- **Use when**: The text is genuinely important

**Example - Warning**:
```html
<p><strong>Warning:</strong> Do not touch the hot surface.</p>
```

### `<b>` vs `<strong>`

Use `<b>` when:
- Highlighting product names
- Drawing attention to text without implying importance
- Styling that doesn't convey meaning

Use `<strong>` when:
- Warning messages
- Important notices
- Text that's genuinely significant

❌ **Wrong**: Using `<strong>` just for visual effect
```html
<p>I like <strong>pizza</strong> and <strong>pasta</strong>.</p>
```

✅ **Correct**: Using `<b>` for visual highlighting
```html
<p>I like <b>pizza</b> and <b>pasta</b>.</p>
```

## Italic Text

### `<i>` Element (Visual Italic)

```html
<p>This text is <i>italic</i> for visual purposes.</p>
```

- **Purpose**: Purely visual italic text
- **Semantic meaning**: None
- **Use when**: Italic text doesn't have special meaning

**Example - Technical Term**:
```html
<p>The term <i>HTML</i> stands for HyperText Markup Language.</p>
```

### `<em>` Element (Semantic Italic)

```html
<p>This text has <em>emphasis</em> and semantic meaning.</p>
```

- **Purpose**: Indicates emphasized text
- **Semantic meaning**: Indicates stress emphasis
- **Use when**: The text should be emphasized when read

**Example - Emphasized Word**:
```html
<p>I <em>really</em> enjoyed the movie.</p>
```

### `<i>` vs `<em>`

Use `<i>` when:
- Technical terms
- Foreign words
- Thoughts or ship names
- Visual styling without emphasis

Use `<em>` when:
- Emphasizing a word in speech
- Showing stress or importance
- Text that should be read with emphasis

❌ **Wrong**: Using `<em>` just for visual effect
```html
<p>The book <em>Moby Dick</em> is a classic.</p>
```

✅ **Correct**: Using `<i>` for book titles
```html
<p>The book <i>Moby Dick</i> is a classic.</p>
```

## Other Semantic Text Elements

### `<mark>` (Highlighted Text)

```html
<p>The most important part is <mark>highlighted</mark> for reference.</p>
```

- **Purpose**: Marks or highlights text for reference
- **Use case**: Search results, important passages

### `<small>` (Smaller Text)

```html
<p><small>This text is smaller than normal text.</small></p>
```

- **Purpose**: Represents side comments or fine print
- **Use case**: Copyright notices, legal text

### `<del>` (Deleted Text)

```html
<p>The price is <del>$100</del> $50.</p>
```

- **Purpose**: Represents text that has been deleted
- **Use case**: Showing price changes, document revisions

### `<ins>` (Inserted Text)

```html
<p>The new feature is <ins>now available</ins>.</p>
```

- **Purpose**: Represents text that has been added
- **Use case**: Document updates, changelogs

### `<sub>` (Subscript)

```html
<p>The chemical formula for water is H<sub>2</sub>O.</p>
```

- **Purpose**: Subscript text
- **Use case**: Chemical formulas, footnotes

### `<sup>` (Superscript)

```html
<p>E = mc<sup>2</sup></p>
```

- **Purpose**: Superscript text
- **Use case**: Exponents, ordinal numbers

### `<abbr>` (Abbreviation)

```html
<p>The <abbr title="World Wide Web">WWW</abbr> is amazing.</p>
```

- **Purpose**: Abbreviation or acronym
- **Attributes**: `title` provides the full expansion
- **Use case**: Technical terms, acronyms

### `<address>` (Contact Information)

```html>
<address>
    Email: <a href="mailto:info@example.com">info@example.com</a><br>
    Phone: (555) 123-4567
</address>
```

- **Purpose**: Contact information for the author/owner
- **Use case**: Footer contact sections, author information

### `<cite>` (Citation)

```html>
<p>As stated in <cite>The HTML Guide</cite>, semantic HTML is important.</p>
```

- **Purpose**: Title of a creative work
- **Use case**: Book titles, movie names, article titles

### `<code>` (Code)

```html
<p>Use the <code>console.log()</code> function for debugging.</p>
```

- **Purpose**: Fragment of computer code
- **Use case**: Inline code references

### `<kbd>` (Keyboard Input)

```html>
<p>Press <kbd>Ctrl</kbd> + <kbd>C</kbd> to copy.</p>
```

- **Purpose**: User input (keyboard)
- **Use case**: Keyboard shortcuts, user instructions

### `<samp>` (Sample Output)

```html>
<p>The program returned: <samp>Error: File not found</samp></p>
```

- **Purpose**: Sample output from programs
- **Use case**: Error messages, program output

## Putting It All Together

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Text Formatting Example</title>
</head>
<body>
    <h1>Web Development Guide</h1>
    
    <h2>Important Concepts</h2>
    <p><strong>HTML</strong> provides the structure of web pages.</p>
    <p><em>CSS</em> handles the styling and presentation.</p>
    
    <h2>Technical Terms</h2>
    <p>The <abbr title="Document Object Model">DOM</abbr> is a programming interface.</p>
    <p>Use the <code>getElementById()</code> method to select elements.</p>
    
    <h2>Keyboard Shortcuts</h2>
    <p>To save your work, press <kbd>Ctrl</kbd> + <kbd>S</kbd>.</p>
    
    <h2>Pricing Information</h2>
    <p>Regular price: <del>$99.99</del> <ins>$49.99</ins></p>
    
    <h2>Chemical Formulas</h2>
    <p>Water: H<sub>2</sub>O</p>
    <p>Energy: E = mc<sup>2</sup></p>
    
    <h2>References</h2>
    <p>As mentioned in <cite>The Web Development Handbook</cite>, semantic HTML is crucial.</p>
    
    <h2>Important Notice</h2>
    <p><mark>Please note: This feature is experimental.</mark></p>
    
    <h2>Legal Information</h2>
    <p><small>© 2024 Example Company. All rights reserved.</small></p>
</body>
</html>
```

## Common Mistakes to Avoid

### Mistake 1: Using Semantic Elements for Styling Only

❌ **Wrong**:
```html
<p>I <strong>really</strong> like this <strong>product</strong>.</p>
```

✅ **Correct**:
```html
<p>I <em>really</em> like this <b>product</b>.</p>
```

### Mistake 2: Ignoring Semantic Meaning

❌ **Wrong**:
```html
<p><b>Warning:</b> This is dangerous.</p>
```

✅ **Correct**:
```html
<p><strong>Warning:</strong> This is dangerous.</p>
```

### Mistake 3: Using Wrong Elements for Content

❌ **Wrong**:
```html
<p>Read the book "Moby Dick".</p>
```

✅ **Correct**:
```html
<p>Read the book <cite>Moby Dick</cite>.</p>
```

### Mistake 4: Not Using Abbreviation Titles

❌ **Wrong**:
```html
<p>The CEO arrived.</p>
```

✅ **Correct**:
```html
<p>The <abbr title="Chief Executive Officer">CEO</abbr> arrived.</p>
```

## Best Practices

1. **Prefer semantic elements**: Use `<strong>` and `<em>` over `<b>` and `<i>` when meaning matters
2. **Use semantic elements correctly**: Don't use them just for visual effects
3. **Provide context**: Use `<abbr>` with `title` attributes for abbreviations
4. **Use appropriate elements**: Choose the right element for the content type
5. **Think about accessibility**: Screen readers handle semantic elements differently
6. **Consider CSS for styling**: Use CSS for complex visual formatting
7. **Be consistent**: Use similar formatting for similar content throughout your site

## Summary

In this session, you learned:
- The difference between visual and semantic text formatting
- When to use `<b>` vs `<strong>` for bold text
- When to use `<i>` vs `<em>` for italic text
- Various semantic text elements (`<mark>`, `<small>`, `<del>`, `<ins>`, etc.)
- Specialized elements for technical content (`<code>`, `<kbd>`, `<samp>`)
- Common mistakes and best practices for text formatting

## Next Steps

In the next session, you'll learn about creating lists in HTML.

## Exercises

### Exercise 1: Format a Product Description (Easy)

Create a product description using appropriate text formatting:
- Product name in bold (visual)
- Important warning in strong (semantic)
- Technical term with abbreviation
- Price showing discount (del/ins)
- Keyboard shortcut for purchase

### Exercise 2: Fix Incorrect Formatting (Intermediate)

Fix the semantic formatting issues in this text:

```html
<p><b>Warning:</b> Do not touch.</p>
<p>I <strong>love</strong> pizza.</p>
<p>The book "1984" is great.</p>
<p>The CEO said yes.</p>
<p>Press Ctrl + C to copy.</p>
```

<details>
<summary>Click to see solution</summary>

```html
<p><strong>Warning:</strong> Do not touch.</p>
<p>I <em>love</em> pizza.</p>
<p>The book <cite>1984</cite> is great.</p>
<p>The <abbr title="Chief Executive Officer">CEO</abbr> said yes.</p>
<p>Press <kbd>Ctrl</kbd> + <kbd>C</kbd> to copy.</p>
```

**Issues fixed**:
1. Changed `<b>` to `<strong>` for warning (semantic importance)
2. Changed `<strong>` to `<em>` for emphasis (not importance)
3. Changed quotes to `<cite>` for book title
4. Added `<abbr>` with title for CEO
5. Changed text to `<kbd>` elements for keyboard input

</details>

### Exercise 3: Create a Scientific Text (Easy)

Create a paragraph about a scientific concept using:
- Subscript for chemical formula
- Superscript for equation
- Technical term with abbreviation
- Code element for function name

<details>
<summary>Click to see solution</summary>

```html
<p>
    In chemistry, water is represented as H<sub>2</sub>O. 
    The energy equation is E = mc<sup>2</sup>. 
    The <abbr title="Unified Modeling Language">UML</abbr> 
    is used in software design. Use the <code>calculate()</code> 
    function for computations.
</p>
```

</details>

## Examples

Check the `examples/` folder for:
- Comprehensive text formatting examples
- Semantic vs. visual formatting comparison
- Technical content examples

## Additional Resources

- [MDN: Text Formatting](https://developer.mozilla.org/en-US/docs/Learn/HTML/Introduction_to_HTML/HTML_text_fundamentals#text_formatting)
- [MDN: Advanced Text Formatting](https://developer.mozilla.org/en-US/docs/Learn/HTML/Introduction_to_HTML/Advanced_text_formatting)
- [WebAIM: Semantic Structure](https://webaim.org/techniques/semanticstructure/)