import http from '@/util/http-common.js'

export default {
    namespaced : true,

	state: {
        reservation : {},
        reservations : [],
    },
	mutations: {
        

    },
	actions: {
       
    //    async setMemberPassword(context,member){
    //        var mem = member
    //        try{
    //            //이부분에 public key 만들서 계정 생성해줘야함 
    //            var key = 'asdf'
    //            //만들어와서 mem에 퍼블릭키 집어넣어주기 
    //            mem.public_key = key 
    //            const resp = await http.put('/user/update',mem)
    //            alert("설정완료"+ resp.data)
    //        }catch(e){
    //             // console.log(e.response)
    //             console.log("비밀번호 설정중 오류")
    //        }
    //    }
    },
	getters: {

    },
}