import http from '@/util/http-common.js'
import router from '@/router'
import reservationEthereum from '../../ethereum/reservationEthereum';

export default {
    namespaced : true,

	state: {
        reservation : {},
        reservations : [],
        myReservations : [],
    },
	mutations: {
        setReservation(state,payload){
            state.reservation = payload
        },
        setReservations(state,payload){
            state.reservations = payload
        },
        setMyResrvations(state,payload){
            state.myReservations = payload
        }
    },
	actions: {
        fetchReservationsByEmail(context,payload){//payload : user.email
            http
                .get('/reserve/read/' +payload+ '/')
                .then(({data})=>{
                    context.commit('setMyResrvations',data)
                })
                .catch()
            // var reservations = 
            // [
            //     {
            //         r_id : 1,
            //         r_address : "광산구 장덕동",
            //         r_dates : ['2020-09-25','2020-09-26','2020-09-27'],
            //         r_pay : 100000,
            //     },
            //     {
            //         r_id : 2,
            //         r_address : "광산구 장덕동 1589",
            //         r_dates : ['2020-09-27','2020-09-28','2020-09-29'],
            //         r_pay : 150000,
            //     },
            // ]
            // context.commit('setReservations',reservations)
        },
        reserveHouses(context,payload){
            http
                .post('/reserve/done',payload)
                .then(({data})=>{
                    reservationEthereum.registerReservation($cookies.get('member'),data).then(value=>{
                            var transaction = {
                                member_email : $cookies.get('member').member_email,
                                tx_hash: value
                            }
                            http.post('/transaction/insert',transaction);
                    })

                    swal({
                        title : "예약 완료!",
                        text : "여행을 준비하세요",
                        icon: "success",
                        button: "Go!"
                    })
                    router.push({ path: '/home' })
                }).catch()
        }
    },
	getters: {

    },
}