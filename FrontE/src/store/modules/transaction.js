import http from '@/util/http-common.js'

export default {
    namespaced : true,

	state: {
        transactions : [],
    },
	mutations: {
        setTransactions(state,payload){
            state.transactions = payload
        }

    },
	actions: {
        fetchTransactionsByEmail(context,payload){//payload : user.email
            // http
            //     .get('/transactions',payload)
            //     .then(({data})=>{
            //         context.commit('setReservations',data)
            //     })
            //     .catch(err => console.log(err.response))
            var transactions = 
            [
                {
                    t_id : 1,
                    t_contents : "광산구 장덕동의 숙소를 예약 하셨습니다.",
                    t_type : "예약",
                },
                {
                    t_id : 2,
                    t_contents : "광산구 장덕동 1589의 숙소를 등록 하셨습니다.",
                    t_type : "등록",
                },
            ]
            context.commit('setTransactions',transactions)
        },
         
        
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