import http from '@/util/http-common.js'

export default {
    namespaced : true,

	state: {
        reservation : {},
        reservations : [],
    },
	mutations: {
        setReservation(state,payload){
            state.reservation = payload
        },
        setReservations(state,payload){
            state.reservations = payload
        },
    },
	actions: {
        fetchReservationsByEmail(context,payload){//payload : user.email
            // http
            //     .get('/reservations',payload)
            //     .then(({data})=>{
            //         context.commit('setReservations',data)
            //     })
            //     .catch(err => console.log(err.response))
            var reservations = 
            [
                {
                    r_id : 1,
                    r_address : "광산구 장덕동",
                    r_dates : ['2020-09-25','2020-09-26','2020-09-27'],
                    r_pay : 100000,
                },
                {
                    r_id : 2,
                    r_address : "광산구 장덕동 1589",
                    r_dates : ['2020-09-27','2020-09-28','2020-09-29'],
                    r_pay : 150000,
                },
            ]
            context.commit('setReservations',reservations)
        },
        
        
    
    },
	getters: {

    },
}