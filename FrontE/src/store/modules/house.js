import http from '@/util/http-common.js'

export default {
    namespaced : true,

	state: {
        houseData : {},
        houseDates : [],
        houseImage : ''
    },
	mutations: {
        initHouseData(state){
            // state.houseData.address ='',
            // state.detail=''
            state.houseData = {}
        },
        setHouseData(state,payload){
            state.houseData = payload
            // state.houseData.address = payload.address,
            // state.houseData.detail = payload.address
        },
        setHouseDates(state,payload){
            state.houseDates = payload
        },
        setHouseImage(state,payload){
            state.houseImage = payload
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
        },
        async imageUpload(payload){
            const fd = new FormData()
            if(payload.length > 0){
                fd.append('file', payload.pop())
                const resp = await http.post('/file/upload',fd,{
                    headers : {
                        'Accept' : 'application/json',
                        'Content-Type':"multipart/form-data"
                    }
                })
                if(resp.data.result === 'successs'){
                    this.state.house.houseData.host_images = resp.data.string
                    console.log(this.state.houseData)
                    this.postHouseData(this.state.houseData)
                }
            }
        }
    },
	getters: {

    },
}