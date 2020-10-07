import http from '@/util/http-common.js'
import router from '@/router'
import Vue from 'vue'

Vue.use(require('vue-cookies'))

export default {

    namespaced : true,

	state: {
        isLogin : false,
        member : {},
    },
	mutations: {
        setisLogin(state,payload){
            state.isLogin = payload
        },
    },
	actions: {
       async getAccessToken(context,code){
           const resp =  await http.post('/login',code)
           console.log(resp);
           //테스트코드
           //사용할코드
           $cookies.set('member',resp.data,'1h')
       },
       async setMemberPassword(context,memberData){
           // 깊은복사
           var member = Object.assign({},memberData)
           try{
               //이부분에 public key 만들서 계정 생성해줘야함 
               var key = '0xA8566F6bC8FB2E46B14e9eEb282ecDc0e53AA37C'
               //만들어와서 mem에 퍼블릭키 집어넣어주기 
               member.public_key = key 
               $cookies.set('member',member,'1h')
            // router.go()
               console.log(member)
               const resp = await http.put('/user/password',member)
               console.log(resp.data)
               alert("response : "+ resp.data)
           }catch(e){
                console.log("비밀번호 설정중 오류")
           }
       }
    },
	getters: {

    },
}