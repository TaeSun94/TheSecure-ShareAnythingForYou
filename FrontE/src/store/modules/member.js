import http from '@/util/http-common.js'
import router from '@/router'
import Vue from 'vue'
import membershipEthereum from '../../ethereum/membershipEthereum';

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
           //테스트코드
           //사용할코드
           $cookies.set('member',resp.data,'1h')
       },
       async setMemberPassword(context,memberData){
           // 깊은복사
           var member = Object.assign({},memberData)
           try{
            //이부분에 public key 만들서 계정 생성해줘야함
            membershipEthereum.createAccount(member.password).then(value=>{
                var account = value;
                member.public_key = account;
                $cookies.set('member',member,'1h');
                http.put('/user/password',member);
                router.go()
                })
            }catch(e){
            }
       }
    },
	getters: {

    },
}