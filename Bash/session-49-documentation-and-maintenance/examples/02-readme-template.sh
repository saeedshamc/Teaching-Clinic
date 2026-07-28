#!/usr/bin/env bash

echo "=== README Template ==="

# تابع create README
create_readme() {
    local project_name=$1
    local description=$2
    local author=$3
    
    cat > README.md << EOF
# $project_name

## Description
$description

## Author
$author

## Installation

\`\`\`bash
# Clone the repository
git clone https://github.com/username/$project_name.git
cd $project_name

# Install dependencies
# Add installation commands here
\`\`\`

## Usage

\`\`\`bash
# Run the script
./script.sh

# With options
./script.sh --option value
\`\`\`

## Features

- Feature 1
- Feature 2
- Feature 3

## Configuration

Configuration options:

| Option | Description | Default |
|--------|-------------|---------|
| --config | Config file path | config.yml |
| --verbose | Verbose output | false |

## Examples

\`\`\`bash
# Example 1
./script.sh --input data.txt

# Example 2
./script.sh --input data.txt --output result.txt --verbose
\`\`\`

## Contributing

1. Fork the repository
2. Create your feature branch (\`git checkout -b feature/amazing-feature\`)
3. Commit your changes (\`git commit -m 'Add amazing feature'\`)
4. Push to the branch (\`git push origin feature/amazing-feature\`)
5. Open a Pull Request

## License

This project is licensed under the MIT License.

## Changelog

### [1.0.0] - 2024-01-28
#### Added
- Initial release
- Core functionality
EOF
    
    echo "✓ README.md created for $project_name"
}

# تابع create CHANGELOG
create_changelog() {
    cat > CHANGELOG.md << 'EOF'
# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- New features go here

### Changed
- Changes to existing functionality

### Deprecated
- Features that will be removed

### Removed
- Removed features

### Fixed
- Bug fixes

### Security
- Security fixes

## [1.0.0] - 2024-01-28

### Added
- Initial release
- Core functionality
EOF
    
    echo "✓ CHANGELOG.md created"
}

# تابع create CONTRIBUTING guide
create_contributing() {
    cat > CONTRIBUTING.md << 'EOF'
# Contributing

Thank you for considering contributing to this project!

## How to Contribute

1. **Fork the repository**
2. **Create a branch** for your feature or fix
3. **Make your changes**
4. **Test your changes**
5. **Submit a pull request**

## Code Style

- Follow existing code style
- Add comments for complex logic
- Write tests for new features

## Commit Messages

Use clear and descriptive commit messages:

- \`feat: add new feature\`
- \`fix: fix bug in feature\`
- \`docs: update documentation\`
- `test: add tests for feature\`

## Questions?

Feel free to open an issue for questions.
EOF
    
    echo "✓ CONTRIBUTING.md created"
}

# تابع create LICENSE
create_license() {
    cat > LICENSE << 'EOF'
MIT License

Copyright (c) 2024

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
EOF
    
    echo "✓ LICENSE created"
}

# تابع create docs structure
create_docs_structure() {
    mkdir -p docs/{api,guides,examples}
    
    echo "✓ Documentation structure created:"
    echo "  docs/"
    echo "    ├── api/"
    echo "    ├── guides/"
    echo "    └── examples/"
}

# استفاده
echo "Creating documentation files..."

create_readme "MyProject" "A sample project for demonstration" "Your Name"
create_changelog
create_contributing
create_license
create_docs_structure

echo -e "\nDocumentation files created successfully!"
