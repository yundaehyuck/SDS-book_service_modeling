import { savePainting, getPaintingList, getPaintingDetail, deletePainting, updatePaintingTitle, downloadPainting, convertSketch, getNewPainting } from '@/api/painting'

const paintingStore = {
  namespaced: true,
  state: {
    painting: null,
    paintingList: [],
    sketch: '',
    mainPaintingList: [],
    paintingPageSetting: null
  },
  getters: {
    getPainting: state => {
      return state.painting
    },
    getPaintingList: state => {
      return state.paintingList
    }
  },
  mutations: {
    SET_PAINTING: (state, data) => {
      state.painting = data
    },
    SET_PAINTING_LIST: (state, data) => {
      state.paintingList = data
    },
    RESET_PAINTING_LIST (state) {
      state.paintingList = []
    },
    RESET_SKETCH (state) {
      state.sketch = ''
    },
    SET_SKETCH: (state, data) => {
      if (data.type === 'painting') {
        state.sketch = data.url.fileUrl + '?timestamp=' + (new Date().getTime())
      } else {
        state.sketch = 'data:image/jpeg;base64,' + data.url
      }
    },
    SET_SKETCH_PAINTING: (state, data) => {
      state.sketch = data
    },
    SET_MAIN_PAINTING_LIST: (state, data) => {
      state.mainPaintingList = data
    },
    SET_PAGE_SETTING: (state, data) => {
      const { pageable, last, first, totalPages, size, totalElements, numberOfElements, empty } = data
      state.paintingPageSetting = { pageable, last, first, totalPages, size, totalElements, numberOfElements, empty }
    },
    ADD_PAINTING: (state, data) => {
      state.paintingList.unshift(data)
      state.paintingList.pop()
    },
    DELETE_PAINTING: (state, data) => {
      const index = state.paintingList.findIndex(item => item.id === data)
      state.paintingList.splice(index, 1)
    },
    UPDATE_PAINTING: (state, data) => {
      const index = state.paintingList.findIndex(item => item.id === data.id)
      state.paintingList[index].title = data.title
    }
  },
  actions: {
    async savePainting ({ dispatch }, formdata) {
      await savePainting(formdata)
        .then(({ data }) => {
          dispatch('getPaintingList', {type: 'COLOR'})
        })
    },
    async getPaintingList ({ commit }, request) {
      await getPaintingList(request)
        .then(({ data }) => {
          commit('SET_PAINTING_LIST', data.result.content)
          commit('SET_PAGE_SETTING', data.result)
        })
    },
    async convertSketch ({ commit }, request) {
      await convertSketch(request)
        .then(({ data }) => {
          commit('SET_SKETCH', {url: data, type: 'sketch'})
        })
    },
    async getPaintingDetail ({ commit }, request) {
      await getPaintingDetail(request)
        .then(({ data }) => {
          commit('SET_PAINTING', data.result)
        })
    },
    async downloadPainting ({ state }, paintingId) {
      await downloadPainting(paintingId)
        .then(({ data }) => {
          // byte 배열을 Blob 객체로 변환
          const blob = new Blob([data], { type: 'image/png' })

          // 다운로드 링크 생성
          const downloadLink = document.createElement('a')
          downloadLink.href = window.URL.createObjectURL(blob)

          // 파일 이름 설정
          const fileName = state.painting.title
          downloadLink.download = fileName

          // 링크 클릭하여 다운로드 시작
          downloadLink.click()

          // 메모리 누수 방지
          URL.revokeObjectURL(downloadLink.href)
        })
    },
    async deletePainting ({ dispatch }, request) {
      await deletePainting(request.paintingId)
        .then(({ data }) => {
          alert('그림을 성공적으로 삭제했습니다.')
          dispatch('getPaintingList', request)
        })
    },
    async updatePaintingTitle ({ commit }, request) {
      await updatePaintingTitle(request)
        .then(({ data }) => {
          alert('그림 제목을 성공적으로 수정했습니다.')
          commit('SET_PAINTING', data.result)
          commit('UPDATE_PAINTING', data.result)
        })
    },
    async getNewPainting ({ commit }, request) {
      await getNewPainting(request)
        .then(({ data }) => {
          commit('SET_MAIN_PAINTING_LIST', data.result)
        })
    }
  }
}

export default paintingStore
