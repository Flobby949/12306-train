import { createStore } from 'vuex'

const MEMBER = 'MEMBER'

export default createStore({
  state: {
    member: window.sessionStorage.getItem(MEMBER) || {}
  },
  getters: {},
  mutations: {
    setMember(state, _member) {
      state.member = _member
      window.sessionStorage.setItem(MEMBER, _member)
    }
  },
  actions: {},
  modules: {}
})
