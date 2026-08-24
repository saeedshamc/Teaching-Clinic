# Session 1: Introduction to the Web and Environment Setup

## Learning Objectives

By the end of this session, you will understand:
- How the World Wide Web works
- What HTML and CSS are and their roles in web development
- How web browsers process and display web pages
- How to set up a proper development environment
- How to organize your project folders effectively

## How the World Wide Web Works

The World Wide Web (WWW) is a system of interconnected documents and resources, linked by hyperlinks and URLs. Here's how it works at a basic level:

### The Client-Server Model

When you type a URL (like `https://www.example.com`) into your browser:

1. **Your browser (client)** sends a request to a server
2. **The server** processes the request and sends back files (HTML, CSS, images, etc.)
3. **Your browser** receives these files and renders them as a web page

### Key Components

- **URL (Uniform Resource Locator)**: The address of a resource on the internet
- **HTTP (Hypertext Transfer Protocol)**: The protocol for transferring data on the web
- **Server**: A computer that stores and serves web files
- **Client**: The device and browser you use to access the web

## What are HTML and CSS?

### HTML (HyperText Markup Language)

HTML is the **structure** of a web page. It defines:
- What content appears on the page (text, images, links, etc.)
- How that content is organized (headings, paragraphs, lists, etc.)
- The hierarchy and meaning of the content

Think of HTML as the **skeleton** or **framework** of a house.

### CSS (Cascading Style Sheets)

CSS is the **presentation** of a web page. It defines:
- How the HTML content looks (colors, fonts, sizes)
- How elements are positioned and laid out
- Visual effects and animations

Think of CSS as the **decoration** and **interior design** of a house.

### The Relationship

HTML and CSS work together:
- HTML provides the structure and content
- CSS provides the styling and layout
- Without HTML, CSS has nothing to style
- Without CSS, HTML appears plain and unstyled

## How Web Browsers Work

Web browsers (Chrome, Firefox, Safari, Edge) are software applications that:

1. **Fetch** web pages from servers using HTTP
2. **Parse** the HTML to understand the document structure
3. **Parse** the CSS to understand styling rules
4. **Render** the page by combining HTML structure with CSS styling
5. **Execute** JavaScript (if present) for interactivity

### The Browser Rendering Process

1. **HTML Parsing**: Browser reads HTML and builds the DOM (Document Object Model)
2. **CSS Parsing**: Browser reads CSS and builds the CSSOM (CSS Object Model)
3. **Render Tree**: Combines DOM and CSSOM to create the render tree
4. **Layout**: Calculates the position and size of each element
5. **Paint**: Draws pixels to the screen

## Setting Up Your Development Environment

### Choosing a Code Editor

A good code editor is essential for web development. Popular options include:

#### Visual Studio Code (Recommended)
- Free and open-source
- Excellent HTML/CSS support
- Extensions for enhanced functionality
- Built-in terminal
- Great community support

#### Other Options
- **Sublime Text**: Fast, lightweight, with a free evaluation period
- **Atom**: Free, open-source, highly customizable
- **Notepad++**: Windows-only, simple and lightweight

### Installing Visual Studio Code

1. Visit [https://code.visualstudio.com/](https://code.visualstudio.com/)
2. Download the version for your operating system
3. Run the installer and follow the prompts
4. Launch VS Code when installation completes

### Recommended VS Code Extensions

- **Live Server**: Automatically reloads your browser when you save changes
- **HTML CSS Support**: Enhanced HTML/CSS IntelliSense
- **Auto Rename Tag**: Automatically renames paired HTML tags
- **Bracket Pair Colorizer**: Colors matching brackets for easier reading

### Installing Extensions

1. Open VS Code
2. Click the Extensions icon in the sidebar (or press `Ctrl+Shift+X`)
3. Search for the extension name
4. Click "Install"

## Project Folder Organization

Good organization is crucial for web development. Here's a recommended structure:

### Basic Structure

```
my-website/
├── index.html          # Main HTML file
├── css/
│   └── style.css       # CSS file(s)
├── images/             # Image files
├── js/                 # JavaScript files (if needed)
└── assets/             # Other assets (fonts, icons, etc.)
```

### Why This Structure?

- **Separation of concerns**: HTML, CSS, and assets are organized separately
- **Scalability**: Easy to add more files as your project grows
- **Maintainability**: Easy to find and update specific files
- **Best practices**: Follows industry standards

## Creating Your First Project

### Step 1: Create a Project Folder

1. Create a new folder on your computer (e.g., `my-first-website`)
2. Open VS Code
3. Go to `File > Open Folder` and select your new folder

### Step 2: Create Subfolders

1. In VS Code, right-click in the file explorer
2. Select "New Folder"
3. Create folders: `css`, `images`, `js` (optional)

### Step 3: Create Your First HTML File

1. Right-click in the file explorer
2. Select "New File"
3. Name it `index.html`

**Note**: `index.html` is the default filename that web servers look for when someone visits your website's root directory.

## Using Live Server

Live Server is a VS Code extension that automatically refreshes your browser when you save changes:

1. Install the "Live Server" extension (see above)
2. Open your HTML file in VS Code
3. Right-click anywhere in the HTML file
4. Select "Open with Live Server"
5. Your browser will open automatically with your page loaded

Now, whenever you save changes to your files, the browser will automatically refresh!

## Common Mistakes to Avoid

### Mistake 1: Poor File Organization

❌ **Bad**: All files in one folder
```
my-website/
├── index.html
├── style.css
├── logo.png
├── script.js
└── background.jpg
```

✅ **Good**: Organized folders
```
my-website/
├── index.html
├── css/
│   └── style.css
├── images/
│   ├── logo.png
│   └── background.jpg
└── js/
    └── script.js
```

### Mistake 2: Inconsistent Naming

❌ **Bad**: Mixed naming conventions
```
My-Page.html
style_sheet.CSS
Images/Logo.png
```

✅ **Good**: Consistent lowercase with hyphens
```
my-page.html
style.css
images/logo.png
```

### Mistake 3: Spaces in Filenames

❌ **Bad**: Spaces can cause issues
```
my website.html
my style.css
```

✅ **Good**: Use hyphens or underscores
```
my-website.html
my-style.css
```

## Best Practices

1. **Use lowercase filenames**: Avoid case-sensitivity issues on different servers
2. **Use hyphens to separate words**: `my-page.html` not `myPage.html`
3. **Keep folder structure shallow**: Don't nest folders too deeply
4. **Use descriptive filenames**: `about.html` not `page2.html`
5. **Keep your editor updated**: New versions often include bug fixes and new features
6. **Use version control**: Consider using Git for larger projects

## Summary

In this session, you learned:
- The web works on a client-server model
- HTML provides structure, CSS provides styling
- Browsers fetch, parse, and render web pages
- How to set up VS Code with helpful extensions
- How to organize your project folders effectively
- How to use Live Server for automatic browser refreshing

## Next Steps

In the next session, you'll create your first HTML document and learn about the basic structure of an HTML file.

## Exercises

### Exercise 1: Set Up Your Environment (Easy)

1. Install Visual Studio Code if you haven't already
2. Install the Live Server extension
3. Create a new folder called `practice-website`
4. Inside that folder, create the recommended subfolder structure (`css`, `images`, `js`)
5. Create an empty `index.html` file
6. Open `index.html` with Live Server to verify everything works

### Exercise 2: Folder Organization Practice (Easy)

Given the following files, organize them into the proper folder structure:
- `home.html`
- `style.css`
- `logo.png`
- `background.jpg`
- `script.js`
- `icon.svg`
- `about.html`
- `contact.css`

Create the folder structure and place each file in the appropriate location.

<details>
<summary>Click to see solution</summary>

```
practice-website/
├── home.html
├── about.html
├── css/
│   ├── style.css
│   └── contact.css
├── images/
│   ├── logo.png
│   ├── background.jpg
│   └── icon.svg
└── js/
    └── script.js
```

</details>

## Examples

Check the `examples/` folder for a demonstration of:
- A properly organized project structure
- A basic HTML file (we'll explore this in detail in Session 2)

## Additional Resources

- [MDN: How the Web Works](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/How_the_Web_works)
- [VS Code Documentation](https://code.visualstudio.com/docs)
- [Live Server Extension](https://marketplace.visualstudio.com/items?itemName=ritwickdey.liveserver)