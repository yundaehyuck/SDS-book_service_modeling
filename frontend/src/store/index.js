import Vue from 'vue'
import Vuex from 'vuex'
import bookStore from '@/store/modules/bookStore.js'
import paintingStore from '@/store/modules/paintingStore.js'
import reviewStore from './modules/reviewStore'
import userStore from './modules/userStore'
import storyStore from './modules/storyStore'
import notificationStore from './modules/notificationStore'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    bookStore,
    paintingStore,
    reviewStore,
    userStore,
    storyStore,
    notificationStore
  }
})

export default store
