//import http from '@/util/http-common.js'

export default {
    namespaced : true,

	state: {
        isLogin : false
    },
	mutations: {
        setisLogin(state,payload){
            state.isLogin = payload
        },
    },
	actions: {

    },
	getters: {

    },
}