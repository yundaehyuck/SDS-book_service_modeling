import api from '@/api/auth'

const saveNotification = async (data) => {
  try {
    // console.log('알림 신청')
    const response = await api.post(`/api/notifications`, data)
    return response
  } catch (error) {
    console.log(error)
    throw new Error('알림 신청에 실패하였습니다.')
  }
}
const getNotificationList = async (request) => {
  try {
    // console.log('알림 신청한 책 목록')
    const response = await api.get(`/api/notifications?page=${request.page}&size=${request.size}`)
    return response
  } catch (error) {
    console.log(error)
    throw new Error('책 목록을 가져오는 것에 실패하였습니다.')
  }
}
const getNotificationDetail = async (notificationId) => {
  try {
    // console.log('알림 상세 정보')
    const response = await api.get(`/api/notifications/${notificationId}`)
    return response
  } catch (error) {
    console.log(error)
    throw new Error('알림 상세 정보를 불러오는 것에 실패하였습니다.')
  }
}
const deleteNotification = async (notificationId) => {
  try {
    console.log('알림 삭제(취소)')
    const response = await api.delete(`/api/notifications/${notificationId}`)
    return response
  } catch (error) {
    console.log(error)
    throw new Error('알림 삭제에 실패하였습니다.')
  }
}
const updateNotification = async (payload) => {
  try {
    // console.log('알림 수정')
    const response = await api.patch(`/api/notifications/${payload.notificationId}`, payload.content)
    return response
  } catch (error) {
    console.log(error)
    throw new Error('알림 수정에 실패하였습니다.')
  }
}

export { saveNotification, getNotificationList, getNotificationDetail, deleteNotification, updateNotification }
