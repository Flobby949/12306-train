SESSION_ALL_TRAIN = 'SESSION_ALL_TRAIN'

SessionStorage = {
  get: function (key) {
    const v = JSON.parse(sessionStorage.getItem(key))
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
