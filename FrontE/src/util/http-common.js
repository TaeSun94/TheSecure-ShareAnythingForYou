import axios from 'axios';

// axios 객체 생성
export default axios.create({
//   baseURL: 'http://localhost:9999/happyhouse/api',
  // baseURL: 'http://localhost:80/api',
  baseURL: 'http://j3c201.p.ssafy.io:8888/api',
  // baseURL: 'http://localhost:8888/api',
  headers: {
    'Content-type': 'application/json',
  },
});