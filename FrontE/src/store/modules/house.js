import http from '@/util/http-common.js'
import Vue from 'vue'
Vue.use(require('vue-cookies'))

export default {
    namespaced : true,

	state: {
        houseData : {},
        houses : [],
        recentHouses : [],
        houseDates : [],
        houseImage : '',
        houseFile : [],
        myHouses : [],
        
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
        setHouses(state,payload){
            state.houses = payload
        },
        setHouseDates(state,payload){
            state.houseDates = payload
        },
        setHouseImage(state,payload){
            state.houseImage = payload
        },
        setRecentHouses(state,payload){
            state.recentHouses = payload
        },
        setMyHouses(state,payload){
            state.myHouses = payload
        }
    },
	actions: {
        fetchRecentHouses(context){
            http
                .get('/host/rately')
                .then(({data})=>{
                    console.log(data)
                    context.commit('setRecentHouses',data)
                })
                .catch(err => console.log(err.response))
            
        },     
        fetchHousesByEmail(context,payload){//payload : user.email
            console.log(payload)
            http
                .get('/host/read/'+ payload +'/')
                .then(({data})=>{
                    console.log(data)
                    context.commit('setMyHouses',data)
                })
                .catch(err => console.log(err.response))
            // var myHouses = 
            // [
            //     {
            //         register_id : 1,
            //         host_images : ["https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"],
            //         host_address : "광산구 장덕동",
            //         host_type : "아파트",
            //         host_capacity : "2~3인용"
            //     },
            //     {
            //         register_id : 2,
            //         host_images :["https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"],
            //         host_address : "광산구 장덕동 1589",
            //         host_type : "아파트",
            //         host_capacity : "개인실"
            //     },
            // ]
            // context.commit('setMyHouses',myHouses)
        },
        imageUpload(context,payload){
            console.log(payload)
            const fd = new FormData()
            if(payload.img.length > 0){
                fd.append('file', payload.img.pop())
                http.post('/file/upload',fd,{
                    headers : {
                        'Accept' : 'application/json',
                        'Content-Type':"multipart/form-data"
                    }
                }).then(({data}) =>{
                    console.log(data)
                    console.log(payload)
                    var imgurl = [];
                    imgurl.push(data)
                    var hostData = 
                    {
                        host_address : payload.address + " " + payload.detail,
                        host_type : payload.select,
                        host_intro : '편히 쉬다가세요',
                        host_price : payload.price,
                        host_capactiy : payload.select_number,
                        host_provide_items : payload.options,
                        host_available_day : payload.dates,
                        member_email : $cookies.get('member').member_email,
                        host_images : imgurl,
                    }
                    console.log(hostData)
                    //집등록부분
                    http
                        .post('/host/regist', hostData)
                        .then(() => {
                            console.log("집등록완료"),
                            router.push({path: '/home'})
                        })
                        .catch(err => console.log(err.response))
                })
            }
        }
    },
	getters: {

    },
}