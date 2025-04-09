import http from "k6/http";
import { check } from "k6";

const BASE_URL = __ENV.BASE_URL || "http://localhost:8080/api";

export default function () {
  // Create a new reservation with mealId passed as a query parameter
  let mealId = 4; // Adjust the mealId accordingly
  
  let reservation = {};
  
  let headers = {
    "Content-Type": "application/json",
    "Authorization": "token abcdef0123456789" // Sample token for authorization
  };
  
  // POST - Create a new reservation with mealId as query parameter
  let createRes = http.post(`${BASE_URL}/reservations?mealId=${mealId}`, JSON.stringify(reservation), {
    headers: headers
  });
  
  check(createRes, {
    'Create reservation status is 200': (r) => r.status === 200,
  });
  
  if (createRes.status === 200) {
    let reservationCode = createRes.json().token;
    
    
    
    // PUT - Confirm the reservation
    let confirmRes = http.put(`${BASE_URL}/reservations/use/${reservationCode}`, null, {
      headers: headers
    });
    
    check(confirmRes, {
      'Confirm reservation status is 200': (r) => r.status === 200,
    });
    
    // DELETE - Delete the reservation
    let cancelRes = http.del(`${BASE_URL}/reservations/token/${reservationCode}`, null, {
      headers: headers
    });
    
    check(cancelRes, {
      'Cancel reservation status is 200': (r) => r.status === 200,
    });
  }

  // GET - List all reservations
  let listRes = http.get(`${BASE_URL}/reservations`, {
    headers: headers
  });

  check(listRes, {
    'List reservations status is 200': (r) => r.status === 200,
    'Returns array of reservations': (r) => Array.isArray(r.json())
  });
}

export const options = {
  stages: [
    { duration: '30s', target:120},
    { duration: '30s', target: 120},
    { duration: '30s', target: 0 },
  ],
  thresholds: {
    http_req_failed: ['rate<0.01'], 
    http_req_duration: ['p(95)<1100'], 
    "checks": ["rate>0.98"]
  },
};
