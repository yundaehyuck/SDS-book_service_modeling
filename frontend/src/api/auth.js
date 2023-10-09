import axios from 'axios'

/*
 * 나도 뭐가 뭔지 모름 ...]
 * 암튼 로컬 스토리지에 accessToken, refreshToken 둘 다 있어야함 !!
 * 토큰 만료시 재발행
 * axios 인스턴스 생성
 */
const api = axios.create({
  baseURL: `${process.env.VUE_APP_BASE_URL}`
})

// 요청 인터셉터 설정
api.interceptors.request.use(
  (config) => {
    // 요청이 전달되기 전에 작업 수행
    const accessToken = sessionStorage.getItem('accessToken')
    const refreshToken = sessionStorage.getItem('refreshToken')

    if (accessToken && refreshToken) {
      config.headers['Authorization'] = accessToken
      config.headers['refresh'] = refreshToken
    }
    return config
    // 요청 오류가 있는 작업 수행
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 응답 인터셉터 추가하기
api.interceptors.response.use(
  (response) => {
    // 2XX 내의 범위에 있는 상태코드 (성공)
    return response
  },
  (error) => {
    // 2XX 외의 범위에 있는 상태코드 (오류)
    const originalRequest = error.config // 원래 요청상태 저장

    // 401 에러(권한 없음) && 원래 요청이 다시 요청된 것이 아니면
    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true
      // 토큰 재발행 api 요청
      return api({
        url: '/auth/access-token',
        method: 'POST',
        headers: {
          Authorization: sessionStorage.getItem('accessToken'),
          refresh: sessionStorage.getItem('refreshToken')
        }
      })
        .then((res) => {
          // 정상 요청되면 새로 받은 토큰 저장
          if (res.status === 200) {
            sessionStorage.removeItem('accessToken')
            sessionStorage.removeItem('refreshToken')
            sessionStorage.setItem('accessToken', res.headers.authorization)
            sessionStorage.setItem('refreshToken', res.headers.refresh)
            // 원래 요청 재시도
            return api(originalRequest)
          } else {
            console.log(res)
          }
        })
        .catch((err) => {
          console.log(err)
        })
    }
    return Promise.reject(error)
  }
)

export default api
