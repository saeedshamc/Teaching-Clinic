# Session 2: HTML Document Structure

## Learning Objectives

By the end of this session, you will understand:
- The basic structure of an HTML document
- What the DOCTYPE declaration is and why it's important
- The purpose of the `<html>`, `<head>`, and `<body>` elements
- How to properly structure an HTML document
- Common attributes used in HTML elements

## The Basic HTML Document Structure

Every HTML document follows a specific structure. Here's the template:

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page Title</title>
</head>
<body>
    <!-- Page content goes here -->
</body>
</html>
```

Let's break down each part:

## DOCTYPE Declaration

```html
<!DOCTYPE html>
```

- **Purpose**: Tells the browser which version of HTML to use
- **Placement**: Must be the very first line in your HTML document
- **Modern HTML**: `<!DOCTYPE html>` specifies HTML5 (the current standard)
- **Why it matters**: Without it, browsers may use "quirks mode" which can cause inconsistent rendering

**Common Mistake**: Forgetting the DOCTYPE or placing it after other content
❌ **Wrong**: `<html>` before `<!DOCTYPE html>`
✅ **Correct**: `<!DOCTYPE html>` must be first

## The `<html>` Element

```html
<html lang="en">
```

- **Purpose**: The root element that contains all other HTML elements
- **Required attributes**: 
  - `lang`: Specifies the language of the document (important for accessibility and SEO)
- **Common language codes**:
  - `en` - English
  - `es` - Spanish
  - `fr` - French
  - `de` - German
  - `ar` - Arabic
  - `zh` - Chinese

**Why the lang attribute matters**:
- Helps screen readers pronounce content correctly
- Assists search engines in understanding the page language
- Enables browsers to offer translation

## The `<head>` Element

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page Title</title>
</head>
```

- **Purpose**: Contains metadata (information about the document) that isn't displayed on the page
- **Content**: Meta tags, title, links to CSS files, scripts, etc.
- **Placement**: Comes immediately after the opening `<html>` tag

### What Goes in the `<head>`?

1. **Character encoding**: `<meta charset="UTF-8">`
2. **Viewport settings**: `<meta name="viewport" ...>`
3. **Page title**: `<title>Page Title</title>`
4. **CSS links**: `<link rel="stylesheet" href="style.css">`
5. **Meta descriptions**: `<meta name="description" ...>`
6. **Favicons**: `<link rel="icon" href="favicon.ico">`

## Character Encoding

```html
<meta charset="UTF-8">
```

- **Purpose**: Tells the browser which character encoding to use
- **UTF-8**: The most common and recommended encoding (supports all characters and emojis)
- **Placement**: Should be within the first 1024 bytes of the document
- **Why it matters**: Without proper encoding, special characters may display incorrectly

**Example**: Without UTF-8, "café" might display as "cafÃ©"

## Viewport Meta Tag

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

- **Purpose**: Controls how the page displays on mobile devices
- **Components**:
  - `width=device-width`: Sets the width to match the device's screen width
  - `initial-scale=1.0`: Sets the initial zoom level to 100%
- **Why it matters**: Essential for responsive design (we'll cover this in later sessions)

**Without this tag**: Mobile browsers may zoom out to show the entire desktop version, making text hard to read.

## The `<title>` Element

```html
<title>Page Title</title>
```

- **Purpose**: Sets the title displayed in the browser tab
- **SEO importance**: Search engines use the title in search results
- **User experience**: Helps users identify tabs in their browser
- **Length**: Keep it under 60 characters for best display
- **Placement**: Must be within the `<head>` element

**Best practices**:
- Be descriptive and concise
- Include important keywords for SEO
- Make it unique for each page
- Example: `"About Us - My Company"` instead of just `"About"`

## The `<body>` Element

```html>
<body>
    <!-- Page content goes here -->
</body>
```

- **Purpose**: Contains all the content that displays on the web page
- **Content**: Text, images, links, forms, etc. - everything visible to users
- **Placement**: Comes after the closing `</head>` tag
- **Required**: Every HTML document must have exactly one `<body>` element

## Putting It All Together

Here's a complete, properly structured HTML document:

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My First Web Page</title>
</head>
<body>
    <h1>Welcome to My Website</h1>
    <p>This is my first properly structured HTML document.</p>
</body>
</html>
```

## HTML Elements and Tags

### What is an HTML Element?

An HTML element is defined by a start tag, some content, and an end tag:

```html
<tagname>Content goes here</tagname>
```

**Example**:
```html
<p>This is a paragraph element.</p>
```

- `<p>`: Start tag (opening tag)
- `This is a paragraph element.`: Content
- `</p>`: End tag (closing tag)

### Nested Elements

HTML elements can be nested (contain other elements):

```html
<body>
    <div>
        <p>This paragraph is inside a div, which is inside the body.</p>
    </div>
</body>
```

**Rules for nesting**:
- Always close tags in the reverse order they were opened
- Proper nesting ensures the document is valid

❌ **Wrong nesting**:
```html
<div><p>Content</div></p>
```

✅ **Correct nesting**:
```html
<div><p>Content</p></div>
```

### Empty Elements

Some elements don't have content and don't need closing tags:

```html
<img src="image.jpg" alt="Description">
<br>
<input type="text">
```

These are called "self-closing" or "void" elements.

## HTML Attributes

Attributes provide additional information about elements:

```html
<tagname attribute="value">Content</tagname>
```

**Example**:
```html
<a href="https://example.com">Click here</a>
```

- `href`: Attribute name
- `https://example.com`: Attribute value

### Common Attributes

- `id`: Unique identifier for an element
- `class`: Specifies one or more class names
- `style`: Inline CSS styling
- `title`: Additional information (displays as tooltip)
- `lang`: Language of the element

**Example with multiple attributes**:
```html
<p id="intro" class="highlight" style="color: blue;" title="Introduction paragraph">
    This paragraph has multiple attributes.
</p>
```

## Common Mistakes to Avoid

### Mistake 1: Missing DOCTYPE

❌ **Wrong**:
```html
<html>
<head>...</head>
<body>...</body>
</html>
```

✅ **Correct**:
```html
<!DOCTYPE html>
<html>
<head>...</head>
<body>...</body>
</html>
```

### Mistake 2: Unclosed Tags

❌ **Wrong**:
```html
<p>This paragraph is not closed
<p>This one neither
```

✅ **Correct**:
```html
<p>This paragraph is properly closed</p>
<p>This one is also closed</p>
```

### Mistake 3: Improper Nesting

❌ **Wrong**:
```html
<strong><em>Text</strong></em>
```

✅ **Correct**:
```html
<strong><em>Text</em></strong>
```

### Mistake 4: Missing Language Attribute

❌ **Wrong**:
```html
<html>
```

✅ **Correct**:
```html
<html lang="en">
```

### Mistake 5: Title in Body

❌ **Wrong**:
```html
<body>
    <title>My Page</title>
</body>
```

✅ **Correct**:
```html
<head>
    <title>My Page</title>
</head>
```

## Best Practices

1. **Always include DOCTYPE**: It should be the first line
2. **Specify the language**: Use the `lang` attribute on the `<html>` tag
3. **Use UTF-8 encoding**: Include `<meta charset="UTF-8">` in the head
4. **Set the viewport**: Include the viewport meta tag for mobile responsiveness
5. **Use meaningful titles**: Make your page titles descriptive and unique
6. **Properly nest elements**: Close tags in the correct order
7. **Use lowercase for tags**: `<html>` not `<HTML>` (though both work)
8. **Quote attribute values**: Use double quotes around attribute values

## Summary

In this session, you learned:
- The basic structure of an HTML document
- The purpose and placement of DOCTYPE, html, head, and body elements
- Important meta tags for character encoding and viewport
- How to structure the page title
- HTML elements, tags, and attributes
- Proper nesting of elements
- Common mistakes and best practices

## Next Steps

In the next session, you'll learn about text elements like headings, paragraphs, and line breaks.

## Exercises

### Exercise 1: Create a Basic HTML Document (Easy)

Create a new HTML file with the following requirements:
- Proper DOCTYPE declaration
- HTML element with language attribute set to your preferred language
- Head section with charset, viewport, and title
- Body section with a heading and paragraph
- The title should be "My First Page"
- The heading should say "Hello, World!"
- The paragraph should say "This is my first HTML document."

### Exercise 2: Fix the Broken HTML (Intermediate)

The following HTML has several errors. Identify and fix them:

```html
<HTML lang="en">
<head>
    <meta charset="utf-8">
    <title>Broken Page
</head>
<body>
    <h1>Welcome</h1>
    <p>This page has errors<p>
    <div>
        <span>Nested incorrectly</div></span>
    </div>
</body>
```

<details>
<summary>Click to see solution</summary>

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Broken Page</title>
</head>
<body>
    <h1>Welcome</h1>
    <p>This page has errors</p>
    <div>
        <span>Nested incorrectly</span>
    </div>
</body>
</html>
```

**Errors fixed**:
1. Added `<!DOCTYPE html>` at the beginning
2. Changed `<HTML>` to lowercase `<html>`
3. Changed `utf-8` to `UTF-8` (recommended)
4. Added closing `</title>` tag
5. Changed unclosed `<p>` to properly closed `<p>`
6. Fixed nesting: `</span></div>` changed to `</span></div>`

</details>

### Exercise 3: Add Attributes (Easy)

Add the following attributes to the elements:
1. Add `id="main-heading"` to the `<h1>` element
2. Add `class="text-content"` to the `<p>` element
3. Add `title="Click for more info"` to a link (create the link)

## Examples

Check the `examples/` folder for:
- A properly structured HTML document
- Examples of correct and incorrect nesting
- Demonstration of various attributes

## Additional Resources

- [MDN: Getting started with HTML](https://developer.mozilla.org/en-US/docs/Learn/HTML/Introduction_to_HTML/Getting_started)
- [W3C HTML5 Specification](https://html.spec.whatwg.org/)
- [HTML Element Reference](https://developer.mozilla.org/en-US/docs/Web/HTML/Element)