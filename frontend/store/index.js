import { createStore } from 'vuex'
import bookStore from './bookStore'
import createPersistedState from 'vuex-persistedstate'

export default createStore({
  modules: {
    bookStore
  },
  plugins: [
    createPersistedState({
      paths: ['bookStore']
    })
  ]
})
