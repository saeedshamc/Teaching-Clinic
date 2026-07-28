#!/usr/bin/env bash

echo "=== Documentation Best Practices ==="

# 1. Code Documentation
echo -e "\n1. Code Documentation:"
echo "✓ Document functions with purpose, args, returns"
echo "✓ Explain complex logic with comments"
echo "✓ Add usage examples in comments"
echo "✓ Keep documentation up-to-date"

# Example of good documentation
code_doc_example() {
    echo -e "\nCode Documentation Example:"
    cat << 'EOF'
#
# Calculate the factorial of a number
#
# Args:
#   $1 - The number to calculate factorial for (non-negative integer)
# Returns:
#   The factorial of the input number
# Example:
#   factorial 5  # returns 120
#
factorial() {
    local n=$1
    
    # Validate input
    if [ $n -lt 0 ]; then
        echo "Error: Negative numbers not allowed" >&2
        return 1
    fi
    
    # Calculate factorial
    local result=1
    for ((i=2; i<=n; i++)); do
        result=$((result * i))
    done
    
    echo $result
}
EOF
}

# 2. README Standards
echo -e "\n2. README Standards:"
echo "✓ Include project description"
echo "✓ Add installation instructions"
echo "✓ Provide usage examples"
echo "✓ List features and requirements"
echo "✓ Include contributing guidelines"

# Example of README structure
readme_structure() {
    echo -e "\nREADME Structure:"
    cat << 'EOF'
# Project Name

## Description
Brief description of the project

## Table of Contents
- Installation
- Usage
- Features
- Configuration
- Contributing
- License

## Installation
Step-by-step installation guide

## Usage
Usage examples with code blocks

## Features
List of features with descriptions

## Contributing
Guidelines for contributors
EOF
}

# 3. Maintenance Practices
echo -e "\n3. Maintenance Practices:"
echo "✓ Regular dependency updates"
echo "✓ Scheduled security scans"
echo "✓ Periodic code reviews"
echo "✓ Documentation updates with code"
echo "✓ Backup and recovery plans"

# Example of maintenance checklist
maintenance_checklist() {
    echo -e "\nMaintenance Checklist:"
    cat << 'EOF'
Daily:
  [ ] Review logs
  [ ] Check system health
  [ ] Monitor performance

Weekly:
  [ ] Security scan
  [ ] Backup verification
  [ ] Dependency check

Monthly:
  [ ] Dependency updates
  [ ] Documentation review
  [ ] Performance analysis

Quarterly:
  [ ] Full system audit
  [ ] Disaster recovery test
  [ ] Capacity planning
EOF
}

# 4. Documentation Tools
echo -e "\n4. Documentation Tools:"
echo "✓ Markdown for general docs"
echo "✓ Doxygen for API docs"
echo "✓ Sphinx for Python docs"
echo "✓ Javadoc for Java docs"

# Example of documentation generation
doc_generation() {
    echo -e "\nDocumentation Generation:"
    echo "  Tools to consider:"
    echo "    - Markdown: README.md, docs/*.md"
    echo "    - ShellDoc: Generate docs from shell scripts"
    echo "    - MkDocs: Static site generator"
    echo "    - Hugo: Another static site generator"
}

# 5. Version Control Documentation
echo -e "\n5. Version Control Documentation:"
echo "✓ Use semantic versioning"
echo "✓ Maintain CHANGELOG.md"
echo "✓ Document breaking changes"
echo "✓ Tag releases properly"

# Example of changelog format
changelog_format() {
    echo -e "\nChangelog Format:"
    cat << 'EOF'
# Changelog

## [1.2.0] - 2024-01-28
### Added
- New feature X
- New feature Y

### Changed
- Updated dependency A to version 2.0

### Deprecated
- Feature Z (will be removed in 2.0)

### Fixed
- Fixed bug in feature B

### Security
- Fixed security vulnerability CVE-XXXX
EOF
}

# Execute examples
code_doc_example
readme_structure
maintenance_checklist
doc_generation
changelog_format
