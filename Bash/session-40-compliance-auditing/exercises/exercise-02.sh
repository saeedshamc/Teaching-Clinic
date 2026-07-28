#!/usr/bin/env bash

# تمرین ۲: Risk Assessment
# هدف: یک اسکریپت بنویسید که:
# 1. risks را identify کند
# 2. risk score را calculate کند
# 3. mitigation plan را create کند

# راه‌حل:
echo "=== Risk Assessment Script ==="

# تابع identify security risks
identify_security_risks() {
    echo "1. Identifying security risks:"
    
    local risks=()
    
    # Check for outdated packages
    if command -v apt &> /dev/null; then
        local outdated=$(apt list --upgradable 2>/dev/null | wc -l)
        if [ $outdated -gt 10 ]; then
            risks+=("Outdated packages: $outdated packages need update")
        fi
    fi
    
    # Check for weak SSH configuration
    if ! grep -q "PermitRootLogin no" /etc/ssh/sshd_config 2>/dev/null; then
        risks+=("SSH root login enabled")
    fi
    
    # Check for missing firewall
    if ! iptables -L -n | grep -q "DROP" 2>/dev/null; then
        risks+=("No firewall rules configured")
    fi
    
    # Check for world-writable sensitive files
    local ww_files=$(find /etc -perm -0002 -type f 2>/dev/null)
    if [ -n "$ww_files" ]; then
        risks+=("World-writable files in /etc")
    fi
    
    # Check for users with excessive privileges
    local sudo_users=$(grep -E '^sudo' /etc/group 2>/dev/null | cut -d: -f4)
    if [ $(echo $sudo_users | wc -w) -gt 5 ]; then
        risks+=("Excessive sudo users: $(echo $sudo_users | wc -w)")
    fi
    
    echo "Identified risks:"
    for i in "${!risks[@]}"; do
        echo "  $((i+1)). ${risks[$i]}"
    done
    
    # Save risks to array
    echo "${risks[@]}" > /tmp/identified_risks.txt
}

# تابع assess risk impact
assess_risk_impact() {
    local risk=$1
    
    echo -e "\n2. Assessing impact for: $risk"
    
    # Define impact levels based on risk type
    case "$risk" in
        *"root login"*)
            echo "  Confidentiality: CRITICAL"
            echo "  Integrity: HIGH"
            echo "  Availability: HIGH"
            echo "  Overall Impact: CRITICAL"
            echo "CRITICAL"
            ;;
        *"firewall"*)
            echo "  Confidentiality: HIGH"
            echo "  Integrity: MEDIUM"
            echo "  Availability: HIGH"
            echo "  Overall Impact: HIGH"
            echo "HIGH"
            ;;
        *"packages"*)
            echo "  Confidentiality: MEDIUM"
            echo "  Integrity: HIGH"
            echo "  Availability: MEDIUM"
            echo "  Overall Impact: MEDIUM"
            echo "MEDIUM"
            ;;
        *"writable"*)
            echo "  Confidentiality: HIGH"
            echo "  Integrity: HIGH"
            echo "  Availability: MEDIUM"
            echo "  Overall Impact: HIGH"
            echo "HIGH"
            ;;
        *"sudo"*)
            echo "  Confidentiality: HIGH"
            echo "  Integrity: CRITICAL"
            echo "  Availability: HIGH"
            echo "  Overall Impact: HIGH"
            echo "HIGH"
            ;;
        *)
            echo "  Confidentiality: MEDIUM"
            echo "  Integrity: MEDIUM"
            echo "  Availability: MEDIUM"
            echo "  Overall Impact: MEDIUM"
            echo "MEDIUM"
            ;;
    esac
}

# تابع assess risk likelihood
assess_risk_likelihood() {
    local risk=$1
    
    echo -e "\n3. Assessing likelihood for: $risk"
    
    # Define likelihood based on risk type
    case "$risk" in
        *"root login"*)
            echo "  Likelihood: MEDIUM (3/5)"
            echo "3"
            ;;
        *"firewall"*)
            echo "  Likelihood: HIGH (4/5)"
            echo "4"
            ;;
        *"packages"*)
            echo "  Likelihood: HIGH (4/5)"
            echo "4"
            ;;
        *"writable"*)
            echo "  Likelihood: MEDIUM (3/5)"
            echo "3"
            ;;
        *"sudo"*)
            echo "  Likelihood: MEDIUM (3/5)"
            echo "3"
            ;;
        *)
            echo "  Likelihood: MEDIUM (3/5)"
            echo "3"
            ;;
    esac
}

# تابع calculate risk score
calculate_risk_score() {
    local likelihood=$1
    local impact=$2
    
    local score=$((likelihood * impact))
    
    # Map impact to numeric value
    local impact_value
    case "$impact" in
        "CRITICAL") impact_value=5 ;;
        "HIGH") impact_value=4 ;;
        "MEDIUM") impact_value=3 ;;
        "LOW") impact_value=2 ;;
        *) impact_value=3 ;;
    esac
    
    local final_score=$((likelihood * impact_value))
    
    echo -e "\n4. Risk Score Calculation:"
    echo "  Likelihood: $likelihood (1-5)"
    echo "  Impact: $impact ($impact_value)"
    echo "  Risk Score: $final_score"
    
    # Determine risk level
    local risk_level
    if [ $final_score -ge 15 ]; then
        risk_level="CRITICAL"
    elif [ $final_score -ge 10 ]; then
        risk_level="HIGH"
    elif [ $final_score -ge 5 ]; then
        risk_level="MEDIUM"
    else
        risk_level="LOW"
    fi
    
    echo "  Risk Level: $risk_level"
    echo "$risk_level"
}

# تابع create mitigation plan
create_mitigation_plan() {
    local risk=$1
    local level=$2
    
    echo -e "\n5. Mitigation Plan for: $risk ($level)"
    
    case "$risk" in
        *"root login"*)
            echo "  Control: Disable root login"
            echo "  Action: Edit /etc/ssh/sshd_config"
            echo "  Owner: System Administrator"
            echo "  Timeline: 1 week"
            echo "  Priority: HIGH"
            ;;
        *"firewall"*)
            echo "  Control: Configure firewall rules"
            echo "  Action: Set default policy to DROP"
            echo "  Owner: Network Administrator"
            echo "  Timeline: 3 days"
            echo "  Priority: CRITICAL"
            ;;
        *"packages"*)
            echo "  Control: Update system packages"
            echo "  Action: Run apt upgrade"
            echo "  Owner: System Administrator"
            echo "  Timeline: 1 week"
            echo "  Priority: MEDIUM"
            ;;
        *"writable"*)
            echo "  Control: Fix file permissions"
            echo "  Action: Remove world-writable permissions"
            echo "  Owner: System Administrator"
            echo "  Timeline: 3 days"
            echo "  Priority: HIGH"
            ;;
        *"sudo"*)
            echo "  Control: Review sudo access"
            echo "  Action: Remove unnecessary sudo users"
            echo "  Owner: Security Administrator"
            echo "  Timeline: 1 week"
            echo "  Priority: HIGH"
            ;;
        *)
            echo "  Control: Implement appropriate controls"
            echo "  Action: Review and remediate"
            echo "  Owner: Security Team"
            echo "  Timeline: 2 weeks"
            echo "  Priority: MEDIUM"
            ;;
    esac
}

# تابع generate risk register
generate_risk_register() {
    local register_file="/tmp/risk-register-$(date +%Y%m%d).txt"
    
    echo "6. Generating Risk Register:"
    
    cat > $register_file << EOF
Risk Register
=============
Date: $(date +%Y-%m-%d)
Generated by: Risk Assessment Script

Risk Summary
------------
Total Risks: 5
Critical: 0
High: 3
Medium: 2
Low: 0

Detailed Risks
--------------
1. SSH root login enabled
   Impact: CRITICAL
   Likelihood: MEDIUM
   Score: 15
   Level: HIGH
   Status: OPEN

2. No firewall rules configured
   Impact: HIGH
   Likelihood: HIGH
   Score: 16
   Level: HIGH
   Status: OPEN

3. World-writable files in /etc
   Impact: HIGH
   Likelihood: MEDIUM
   Score: 12
   Level: HIGH
   Status: OPEN

4. Outdated packages
   Impact: MEDIUM
   Likelihood: HIGH
   Score: 12
   Level: MEDIUM
   Status: OPEN

5. Excessive sudo users
   Impact: HIGH
   Likelihood: MEDIUM
   Score: 12
   Level: MEDIUM
   Status: OPEN

Mitigation Summary
------------------
Total Mitigations: 5
Completed: 0
In Progress: 0
Pending: 5

Next Steps
----------
1. Implement firewall rules (Priority: CRITICAL)
2. Fix world-writable files (Priority: HIGH)
3. Disable SSH root login (Priority: HIGH)
4. Review sudo access (Priority: HIGH)
5. Update system packages (Priority: MEDIUM)
EOF
    
    echo "✓ Risk register generated: $register_file"
    cat $register_file
}

# اجرا
identify_security_risks

# Process each identified risk
risks=("SSH root login enabled" "No firewall rules configured" "World-writable files in /etc" "Outdated packages" "Excessive sudo users")

for risk in "${risks[@]}"; do
    impact=$(assess_risk_impact "$risk" | tail -1)
    likelihood=$(assess_risk_likelihood "$risk" | tail -1)
    level=$(calculate_risk_score "$likelihood" "$impact" | tail -1)
    create_mitigation_plan "$risk" "$level"
done

generate_risk_register
