import http from '@/util/http-common.js'
import Vue from 'vue'
import hostEthereum from '../../ethereum/hostEthereum';
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
        initHouses(state){
            state.houses = []
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
                    context.commit('setRecentHouses',data)
                })
                .catch()
            
        },
        fetchHouseByKeyword(context,payload){
            http
                .get('/host/search/'+payload + '/')
                .then(({data})=>{
                    context.commit('setHouses',data)
                }).catch()
        },     
        fetchHousesByEmail(context,payload){//payload : user.email
            http
                .get('/host/read/'+ payload +'/')
                .then(({data})=>{
                    context.commit('setMyHouses',data)
                })
                .catch()
        },
        imageUpload(context,payload){
            const fd = new FormData()
            if(payload.img.length > 0){
                fd.append('file', payload.img.pop())
                http.post('/file/upload',fd,{
                    headers : {
                        'Accept' : 'application/json',
                        'Content-Type':"multipart/form-data"
                    }
                }).then(({data}) =>{
                    var imgurl = [];
                    imgurl.push(data)
                    var hostData = 
                    {
                        host_address : payload.address + " " + payload.detail,
                        host_type : payload.select,
                        host_intro : '편히 쉬다가세요',
                        host_price : payload.price,
                        host_capacity : payload.select_number,
                        host_provide_items : payload.options,
                        host_available_day : payload.dates,
                        member_email : $cookies.get('member').member_email,
                        host_images : imgurl,
                    }
                    //집등록부분
                    http
                        .post('/host/regist', hostData)
                        .then((data) => {
                            hostEthereum.registerHost($cookies.get('member'),data.data).then(value=>{
                                var transaction = {
                                    member_email : $cookies.get('member').member_email,
                                    tx_hash: value
                                }
                                http.post('/transaction/insert',transaction);
                            })
                            router.push({path: '/home'})
                        })
                        .catch()
                })
            }
        }
    },
	getters: {

    },
}