import { check } from 'k6';
import http from 'k6/http';

export default function () {
  const res = http.get('http://localhost:8080/api/v1/hotel');

  check(res, {
                 'Status is 200': (res) => res.status === 200
             });
}