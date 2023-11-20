import { createStore } from 'vuex'

const MEMBER = 'MEMBER'

export default createStore({
  state: {
    member: JSON.parse(window.sessionStorage.getItem(MEMBER)) || {}
  },
  getters: {},
  mutations: {
    setMember(state, _member) {
      console.log('setMember')
      console.log(_member)
      console.log('setMember')
      state.member = _member
      window.sessionStorage.setItem(MEMBER, JSON.stringify(_member))
    }
  },
  actions: {},
  modules: {}
})
