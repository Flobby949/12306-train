SESSION_ORDER = 'SESSION_ORDER'
SESSION_TICKET_PARAMS = 'SESSION_TICKET_PARAMS'

SessionStorage = {
  get: function (key) {
    const v = sessionStorage.getItem(key)
    if (v && typeof v !== 'undefined' && v !== 'undefined') {
      return v
    }
  },
  set: function (key, data) {
    console.log(data)
    sessionStorage.setItem(key, data)
  },
  remove: function (key) {
    sessionStorage.removeItem(key)
  },
  clearAll: function () {
    sessionStorage.clear()
  }
}
