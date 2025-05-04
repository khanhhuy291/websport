#!/bin/bash

# Base URL
BASE_URL="http://localhost:8080"

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${GREEN}Testing API Pagination Endpoints${NC}"
echo "====================================="

# Test Bills API
echo -e "\n${GREEN}Testing Bills API:${NC}"
echo "GET /api/bills?page=0&size=5"
curl -s "$BASE_URL/api/bills?page=0&size=5" | jq .

echo -e "\nGET /api/bills/user/1?page=0&size=5"
curl -s "$BASE_URL/api/bills/user/1?page=0&size=5" | jq .

# Test Users API
echo -e "\n${GREEN}Testing Users API:${NC}"
echo "GET /api/users?page=0&size=5"
curl -s "$BASE_URL/api/users?page=0&size=5" | jq .

# Test Products API
echo -e "\n${GREEN}Testing Products API:${NC}"
echo "GET /api/products?page=0&size=5"
curl -s "$BASE_URL/api/products?page=0&size=5" | jq .

# Test Courts API
echo -e "\n${GREEN}Testing Courts API:${NC}"
echo "GET /api/courts?page=0&size=5"
curl -s "$BASE_URL/api/courts?page=0&size=5" | jq .

# Test ChildCourts API
echo -e "\n${GREEN}Testing ChildCourts API:${NC}"
echo "GET /api/childcourts?page=0&size=5"
curl -s "$BASE_URL/api/childcourts?page=0&size=5" | jq .

echo -e "\nGET /api/childcourts?courtsId=1&page=0&size=5"
curl -s "$BASE_URL/api/childcourts?courtsId=1&page=0&size=5" | jq .

# Test Bookings API
echo -e "\n${GREEN}Testing Bookings API:${NC}"
echo "GET /api/bookings?page=0&size=5"
curl -s "$BASE_URL/api/bookings?page=0&size=5" | jq .

echo -e "\nGET /api/bookings/user/1?page=0&size=5"
curl -s "$BASE_URL/api/bookings/user/1?page=0&size=5" | jq .

echo -e "\n${GREEN}All tests completed!${NC}" 