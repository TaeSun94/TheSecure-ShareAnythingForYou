import http from '@/util/http-common.js'

export default {
    namespaced : true,

	state: {
        reservation : {},
        reservations : [],
        recentReservations : [],
    },
	mutations: {
        setReservation(state,payload){
            state.reservation = payload
        },
        setReservations(state,payload){
            state.reservations = payload
        },
        setRecentReservations(state,payload){
            state.recentReservations = payload
        }

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
        fetchRecentReservations(context,payload){//payload : user.email
            // http
            //     .get('/reservations',payload)
            //     .then(({data})=>{
            //         context.commit('setReservations',data)
            //     })
            //     .catch(err => console.log(err.response))
            var recentReservations = 
            [
                {
                    register_id : 1,
                    house_url :"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
                    house_address : "광산구 장덕동",
                    house_type : "아파트",
                    house_capacity : "2~3인용"
                },
                {
                    register_id : 2,
                    house_url :"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
                    house_address : "광산구 장덕동",
                    house_type : "아파트",
                    house_capacity : "2~3인용"
                },
                {
                    register_id : 3,
                    house_url :"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
                    house_address : "광산구 장덕동",
                    house_type : "아파트",
                    house_capacity : "2~3인용"
                },
                {
                    register_id : 4,
                    house_url :"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
                    house_address : "광산구 장덕동",
                    house_type : "아파트",
                    house_capacity : "2~3인용"
                },
            ]
            context.commit('setRecentReservations',recentReservations)
        },     
        
    
    },
	getters: {

    },
}