import http from '@/util/http-common.js'

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
       async getAccessToken(context,code){
           var codedata = {
               code : code
           }
           const resp =  await http.post('/login',codedata)
           console.log(resp);
           // 유저객체 반환시에
        //    {
        //     "member_email": "string",
        //     "member_nickname": "string",
        //     "public_key": "string"
        //   }
           this.$cookies.get('member',resp.data,'1h')
        //    this.$cookies.set('token',resp.data,'1h')
       }
    },
	getters: {

    },
}