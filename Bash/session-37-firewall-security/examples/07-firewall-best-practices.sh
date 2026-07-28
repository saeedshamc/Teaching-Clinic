#!/usr/bin/env bash

echo "=== Firewall Best Practices ==="

# 1. Default Deny
echo -e "\n1. Default Deny:"
echo "✓ Default policy: DROP"
echo "✓ Explicitly allow needed traffic"
echo "✓ Least privilege principle"

# 2. Logging
echo -e "\n2. Logging:"
echo "✓ Log dropped packets"
echo "✓ Monitor firewall logs"
echo "✓ Alert on suspicious activity"

# 3. Regular Updates
echo -e "\n3. Regular Updates:"
echo "✓ Review rules regularly"
echo "✓ Remove unused rules"
echo "✓ Update firewall software"

# 4. Testing
echo -e "\n4. Testing:"
echo "✓ Test rules before applying"
echo "✓ Verify connectivity"
echo "✓ Document changes"

# 5. Backup
echo -e "\n5. Backup:"
echo "✓ Backup firewall rules"
echo "✓ Version control configs"
echo "✓ Document restore procedure"
