#!/usr/bin/env bash

echo "=== API Authentication ==="

# Basic Auth
echo "1. Basic Authentication:"
echo "curl -u username:password https://api.example.com/data"

# Bearer Token
echo -e "\n2. Bearer Token:"
TOKEN="your_token_here"
echo "curl -H \"Authorization: Bearer \$TOKEN\" https://api.example.com/data"

# API Key
echo -e "\n3. API Key:"
API_KEY="your_api_key"
echo "curl -H \"X-API-Key: \$API_KEY\" https://api.example.com/data"

# OAuth2
echo -e "\n4. OAuth2:"
echo "1. دریافت access token"
echo "2. استفاده از token در requests"
echo "curl -H \"Authorization: Bearer \$ACCESS_TOKEN\" https://api.example.com/data"
