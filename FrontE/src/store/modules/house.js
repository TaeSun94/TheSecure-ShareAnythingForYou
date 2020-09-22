import http from '@/util/http-common.js'

export default {
    namespaced : true,

	state: {
        houseData : '',
    },
	mutations: {
        initHouseData(state){
            // state.houseData.address ='',
            // state.detail=''
            state.houseData = ''
        },
        setHouseData(state,payload){
            state.houseData = payload
            // state.houseData.address = payload.address,
            // state.houseData.detail = payload.address
        }
    },
	actions: {
        postHouseData(houseData){
            http
                .post('/api/host/regist', houseData)
                .then(() => {
                    console.log("집등록완료"),
                    router.push({path: '/home'})
                })
                .catch(err => console.log(err.response))
        }
    },
	getters: {

    },
}