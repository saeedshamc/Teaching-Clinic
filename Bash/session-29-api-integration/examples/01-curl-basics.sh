#!/usr/bin/env bash

echo "=== curl برای API ==="

# GET request
echo "1. GET Request:"
curl -X GET https://jsonplaceholder.typicode.com/posts/1

# POST request
echo -e "\n2. POST Request:"
curl -X POST https://jsonplaceholder.typicode.com/posts \
  -H "Content-Type: application/json" \
  -d '{"title":"Test","body":"Test body","userId":1}'

# PUT request
echo -e "\n3. PUT Request:"
curl -X PUT https://jsonplaceholder.typicode.com/posts/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated"}'

# DELETE request
echo -e "\n4. DELETE Request:"
curl -X DELETE https://jsonplaceholder.typicode.com/posts/1
