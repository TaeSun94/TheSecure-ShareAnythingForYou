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
           var codedata = {
               code : code
           }
        //    const resp =  await http.post('/login',codedata)
        //    console.log(resp);
           var test = {
            "member_email": "test@gmail.com",
            "member_nickname": "testnickname",
            "member_imgurl" : "http://k.kakaocdn.net/dn/bpLTxG/btqJRmspqjR/390YKJMfPaukWYDUbbMcz1/img_640x640.jpg",
            // "public_key": "0xA8566F6bC8FB2E46B14e9eEb282ecDc0e53AA37C"
            "public_key": ""
            }
           //테스트코드
           $cookies.set('member',test,'1h')
           //사용할코드
        //    $cookies.get('member',resp.data,'1h')
        //    $cookies.set('token',resp.data,'1h')
       },
       async setMemberPassword(context,member){
           // 깊은복사
           var mem = Object.assign({},member)
           try{
               //이부분에 public key 만들서 계정 생성해줘야함 
               var key = '"0xA8566F6bC8FB2E46B14e9eEb282ecDc0e53AA37C'
               //만들어와서 mem에 퍼블릭키 집어넣어주기 
               mem.public_key = key 
               $cookies.set('member',mem,'1h')
               router.go()
               const resp = await http.put('/user/update',mem)
               alert("설정완료"+ resp.data)
           }catch(e){
                console.log("비밀번호 설정중 오류")
           }
       }
    },
	getters: {

    },
}