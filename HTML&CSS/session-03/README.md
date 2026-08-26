# Session 3: Text Elements

## Learning Objectives

By the end of this session, you will understand:
- How to use headings to create document structure
- How to create paragraphs for text content
- How to use line breaks and horizontal rules
- The semantic meaning and proper usage of each text element
- Best practices for organizing text content

## Headings

HTML provides six levels of headings, from `<h1>` (most important) to `<h6>` (least important):

```html
<h1>Main Heading</h1>
<h2>Subheading</h2>
<h3>Sub-subheading</h3>
<h4>Fourth-level heading</h4>
<h5>Fifth-level heading</h5>
<h6>Sixth-level heading</h6>
```

### Heading Hierarchy

Headings create a hierarchical structure for your content:

```html
<h1>Website Title</h1>
    <h2>Main Section 1</h2>
        <h3>Subsection 1.1</h3>
        <h3>Subsection 1.2</h3>
    <h2>Main Section 2</h2>
        <h3>Subsection 2.1</h3>
```

### When to Use Each Heading Level

- **`<h1>`**: The main title of the page (use only once per page)
- **`<h2>`**: Major sections
- **`<h3>`**: Subsections within `<h2>` sections
- **`<h4>` to `<h6>`**: Further subdivisions (rarely needed)

### Best Practices for Headings

1. **Use only one `<h1>` per page**: This is the main page title
2. **Don't skip levels**: Don't jump from `<h2>` to `<h4>`
3. **Use headings for structure, not styling**: Use CSS for font sizes
4. **Make headings descriptive**: They should describe the content that follows

❌ **Wrong**: Using headings just for big text
```html
<h3>This text is big but not a heading</h3>
```

✅ **Correct**: Using headings for structure
```html
<h1>About Our Company</h1>
<h2>Our History</h2>
<h2>Our Mission</h2>
```

## Paragraphs

The `<p>` element defines a paragraph of text:

```html
<p>This is a paragraph of text. Browsers automatically add space before and after paragraphs.</p>
<p>This is another paragraph. Notice the spacing between paragraphs.</p>
```

### How Paragraphs Work

- **Automatic spacing**: Browsers add margin above and below paragraphs
- **Block-level element**: Paragraphs take up the full width available
- **Text wrapping**: Text automatically wraps to the next line
- **Multiple spaces**: Multiple spaces are collapsed into one space

### Common Mistakes with Paragraphs

❌ **Wrong**: Using multiple `<br>` tags for spacing
```html
<p>First paragraph</p>
<br><br><br>
<p>Second paragraph</p>
```

✅ **Correct**: Let paragraphs handle spacing naturally
```html
<p>First paragraph</p>
<p>Second paragraph</p>
```

❌ **Wrong**: Using empty paragraphs for spacing
```html
<p>Content</p>
<p></p>
<p></p>
<p>More content</p>
```

✅ **Correct**: Use CSS margins for spacing
```html
<p>Content</p>
<p>More content</p>
```

## Line Breaks

The `<br>` element creates a line break within text:

```html
<p>This is the first line.<br>This is the second line.<br>This is the third line.</p>
```

### When to Use Line Breaks

- **Poetry or verse**: When preserving line breaks is important
- **Addresses**: Multiple lines in an address
- **Forms**: Creating compact layouts

**Example - Address**:
```html
<p>
    John Doe<br>
    123 Main Street<br>
    City, State 12345
</p>
```

### Line Break vs. Paragraph

Use `<br>` for line breaks within content, use `<p>` for separate paragraphs:

❌ **Wrong**: Using `<br>` instead of paragraphs
```html
First paragraph.<br><br>
Second paragraph.<br><br>
Third paragraph.
```

✅ **Correct**: Using paragraphs for separate thoughts
```html
<p>First paragraph.</p>
<p>Second paragraph.</p>
<p>Third paragraph.</p>
```

## Horizontal Rules

The `<hr>` element creates a thematic break (horizontal line):

```html
<h1>Chapter 1</h1>
<p>Content of chapter 1...</p>
<hr>
<h1>Chapter 2</h1>
<p>Content of chapter 2...</p>
```

### When to Use Horizontal Rules

- **Separating major sections**: Between chapters or topics
- **Visual breaks**: To indicate a change in topic
- **Thematic separation**: When content shifts to a different theme

### Best Practices

- **Use sparingly**: Don't overuse horizontal rules
- **Semantic meaning**: Use it to indicate a thematic break, not just for decoration
- **Style with CSS**: Change the appearance using CSS if needed

## Preformatted Text

The `<pre>` element displays text exactly as written in the HTML:

```html
<pre>
This text     preserves    spacing
And line breaks
    And indentation
</pre>
```

### When to Use Preformatted Text

- **Code snippets**: Displaying programming code
- **ASCII art**: Text-based graphics
- **Tabular data**: Simple text tables

**Example - Code Display**:
```html
<pre>
function hello() {
    console.log("Hello, World!");
}
</pre>
```

## Blockquotes

The `<blockquote>` element defines a section quoted from another source:

```html
<blockquote>
    <p>The only way to do great work is to love what you do.</p>
    <footer>— Steve Jobs</footer>
</blockquote>
```

### Blockquote Attributes

- **`cite`**: URL to the source of the quotation

```html
<blockquote cite="https://example.com/quote">
    <p>This is a quote from a website.</p>
</blockquote>
```

### Inline Quotes

For short inline quotes, use the `<q>` element:

```html
<p>As Steve Jobs once said, <q>The only way to do great work is to love what you do.</q></p>
```

## Putting It All Together

Here's an example using various text elements:

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Text Elements Example</title>
</head>
<body>
    <h1>The Art of Web Development</h1>
    
    <h2>Introduction</h2>
    <p>Web development is both an art and a science. It requires creativity and technical skill.</p>
    
    <h2>Key Principles</h2>
    <p>There are several important principles to follow:</p>
    
    <h3>Semantic HTML</h3>
    <p>Use HTML elements for their intended purpose. This makes your content accessible and meaningful.</p>
    
    <h3>CSS for Styling</h3>
    <p>Separate content from presentation using CSS. This makes your code maintainable.</p>
    
    <hr>
    
    <h2>Famous Quote</h2>
    <blockquote>
        <p>Simplicity is the ultimate sophistication.</p>
        <footer>— Leonardo da Vinci</footer>
    </blockquote>
    
    <h2>Contact Information</h2>
    <p>
        Email: info@example.com<br>
        Phone: (555) 123-4567<br>
        Address: 123 Web Dev Lane
    </p>
</body>
</html>
```

## Common Mistakes to Avoid

### Mistake 1: Using Headings for Styling

❌ **Wrong**:
```html
<h3>I want this text to be small but it's a heading</h3>
```

✅ **Correct**:
```html
<p style="font-size: 14px;">I want this text to be small</p>
```

### Mistake 2: Skipping Heading Levels

❌ **Wrong**:
```html
<h1>Main Title</h1>
<h3>Skipping h2</h3>
```

✅ **Correct**:
```html
<h1>Main Title</h1>
<h2>Section Title</h2>
<h3>Subsection Title</h3>
```

### Mistake 3: Multiple H1 Tags

❌ **Wrong**:
```html
<h1>Page Title</h1>
<h1>Another Title</h1>
```

✅ **Correct**:
```html
<h1>Page Title</h1>
<h2>Section Title</h2>
```

### Mistake 4: Using BR for Paragraphs

❌ **Wrong**:
```html
<p>First paragraph</p>
<br>
<p>Second paragraph</p>
```

✅ **Correct**:
```html
<p>First paragraph</p>
<p>Second paragraph</p>
```

## Best Practices

1. **Use semantic headings**: Create a logical hierarchy
2. **One h1 per page**: Reserve `<h1>` for the main title
3. **Don't skip levels**: Follow the heading hierarchy
4. **Use paragraphs for text**: Group related sentences
5. **Use line breaks sparingly**: Only when the meaning requires it
6. **Use horizontal rules for thematic breaks**: Not just for decoration
7. **Keep content meaningful**: Every element should serve a purpose
8. **Use CSS for styling**: Don't use HTML elements just for their default appearance

## Summary

In this session, you learned:
- How to use headings (`<h1>` to `<h6>`) to create document structure
- How to create paragraphs with the `<p>` element
- When to use line breaks (`<br>`) vs. paragraphs
- How to use horizontal rules (`<hr>`) for thematic breaks
- How to display preformatted text with `<pre>`
- How to create blockquotes with `<blockquote>`
- Common mistakes and best practices for text elements

## Next Steps

In the next session, you'll learn about text formatting elements like bold, italic, and semantic text tags.

## Exercises

### Exercise 1: Create a Document Structure (Easy)

Create an HTML document with the following structure:
- One `<h1>` heading: "My Biography"
- Two `<h2>` headings: "Early Life" and "Career"
- At least one `<h3>` under each `<h2>`
- Paragraphs of text under each heading
- A horizontal rule between the two main sections

### Exercise 2: Fix the Heading Hierarchy (Intermediate)

The following document has heading hierarchy issues. Fix them:

```html
<h1>My Website</h1>
<h3>About</h3>
<p>Information about the website.</p>
<h4>History</h4>
<p>Website history.</p>
<h1>Services</h1>
<p>Our services.</p>
```

<details>
<summary>Click to see solution</summary>

```html
<h1>My Website</h1>
<h2>About</h2>
<p>Information about the website.</p>
<h3>History</h3>
<p>Website history.</p>
<h2>Services</h2>
<p>Our services.</p>
```

**Issues fixed**:
1. Changed `<h3>` to `<h2>` (main section)
2. Changed `<h4>` to `<h3>` (subsection)
3. Changed second `<h1>` to `<h2>` (shouldn't have multiple h1s)

</details>

### Exercise 3: Format an Address (Easy)

Create a contact section with an address using proper line breaks:

```
John Smith
456 Tech Boulevard
San Francisco, CA 94105
```

<details>
<summary>Click to see solution</summary>

```html
<h2>Contact Information</h2>
<p>
    John Smith<br>
    456 Tech Boulevard<br>
    San Francisco, CA 94105
</p>
```

</details>

## Examples

Check the `examples/` folder for:
- A complete document with proper heading hierarchy
- Examples of all text elements in context
- Correct vs. incorrect usage examples

## Additional Resources

- [MDN: Headings and Paragraphs](https://developer.mozilla.org/en-US/docs/Learn/HTML/Introduction_to_HTML/HTML_text_fundamentals#headings_and_paragraphs)
- [MDN: Advanced Text Formatting](https://developer.mozilla.org/en-US/docs/Learn/HTML/Introduction_to_HTML/Advanced_text_formatting)
- [WebAIM: Heading Structure](https://webaim.org/techniques/semanticstructure/)