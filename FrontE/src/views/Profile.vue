<template>
    <div class="profile">
        <v-container>
            <!-- <v-container class="Title">
                <p>프로필</p>
            </v-container> -->
            <v-container>
                <v-sheet elevation="3" shaped>
                    <v-row style="padding : 3vh; margin-left: 4vh;">
                        <v-col cols="4" >
                            <v-avatar size="30vh">
                                <img
                                    :src="member.member_img"
                                >
                            </v-avatar>
                        </v-col>
                        <v-col cols="8">
                            <p style="font-size : 5vh;">{{member.member_nickname}} 님 환영합니다!</p>
                            <v-list-item>
                                <v-list-item-icon>
                                    <v-icon>mdi-mail</v-icon>
                                </v-list-item-icon>
                                <v-list-item-subtitle>{{member.member_email}}</v-list-item-subtitle>
                            </v-list-item>
                            <v-list-item>
                                <v-list-item-icon>
                                    <v-icon>mdi-mail</v-icon>
                                </v-list-item-icon>
                                <v-list-item-subtitle v-if="member.public_key !== null">{{member.public_key}}</v-list-item-subtitle>
                                <v-list-item-subtitle v-else><a @click.prevent="setPassword">지갑 생성하기</a></v-list-item-subtitle>
                            </v-list-item>
                        </v-col>
                    </v-row>
                </v-sheet>
                <v-container>
                    <v-divider style="margin : 3vh;"></v-divider>
                    <v-sheet elevation="3" shaped>
                        <v-container style="padding : 3vh;">
                            <v-subheader>Sharing - 숙소</v-subheader>

                            <p v-if="this.houses.length == 0" style="text-align :center;">현재 등록하신 숙소가 없어요!</p>
                            <v-row dense v-else>
                                <v-col
                                v-for="(house, i) in this.houses"
                                :key="i"
                                cols="12"
                                >
                                <v-card
                                    color="grey lighten-5"
                                >
                                    <div class="d-flex flex-no-wrap justify-space-between">
                                    <div>
                                        <v-card-title
                                        class="headline"
                                        v-text="house.host_address"
                                        ></v-card-title>

                                        <v-card-subtitle v-text="house.host_type"></v-card-subtitle>
                                        <v-card-text style="padding : 2.5vh;" v-text="house.host_capacity"></v-card-text>
                                    </div>
                                    <v-avatar
                                        class="ma-4"
                                        size="125"
                                        tile
                                    >
                                        <v-img :src="house.host_images[0]"></v-img>
                                    </v-avatar>
                                    </div>
                                </v-card>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-sheet>
                </v-container>
                <v-container>
                    <v-divider style="margin : 3vh;"></v-divider>
                    <v-sheet elevation="3" shaped>
                        <v-container style="padding : 3vh;">
                            <v-subheader>Reservation - 예약</v-subheader>
                            <p style="text-align :center;" v-if="this.reservations.length == 0">현재 예약하신 숙소가 없어요!</p>
                            <v-row dense v-else>
                                <v-col
                                v-for="(reservation, i) in this.reservations"
                                :key="i"
                                cols="12"
                                >
                                <v-card
                                    color="deep-orange lighten-5"
                                >
                                    <div class="d-flex flex-no-wrap justify-space-between">
                                    <div>
                                        <v-card-title
                                        class="headline"
                                        v-text="reservation.host.host_address"
                                        ></v-card-title>

                                        <v-card-subtitle > 체크인 날짜와 체크아웃 날짜를 확인하세요 </v-card-subtitle>
                                        <v-card-text style="padding : 2.5vh;">
                                            <v-chip
                                                class="ma-2"
                                                color="orange"
                                                text-color="white"
                                                >
                                                <v-avatar left>
                                                    <v-icon>mdi-checkbox-marked-circle</v-icon>
                                                </v-avatar>
                                                체크인 : {{reservation.reserve_day[0]}}
                                            </v-chip>
                                            <v-chip
                                                class="ma-2"
                                                color="orange"
                                                text-color="white"
                                                >
                                                ~
                                            </v-chip>
                                            <v-chip
                                                class="ma-2"
                                                color="orange"
                                                text-color="white"
                                                >
                                                <v-avatar left>
                                                    <v-icon>mdi-checkbox-marked-circle</v-icon>
                                                </v-avatar>
                                                체크아웃 : {{reservation.reserve_day[reservation.reserve_day.length-1]}}
                                            </v-chip>
                                        </v-card-text>
                                    </div>
                                    <div>
                                        <v-subheader style=" margin-right : 10vh; margin-top: 4vh;">결제 금액</v-subheader>
                                        <p style="font-size: 4vh;" >{{reservation.price}}원</p>
                                    </div>
                                    <!-- <v-avatar
                                        class="ma-5"
                                        size="125"
                                        tile
                                    > -->
                                        
                                        <!-- <v-img :src="house.house_url"></v-img> -->
                                    <!-- </v-avatar> -->
                                    </div>
                                </v-card>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-sheet>
                </v-container>
                <v-container>
                    <v-divider style="margin : 3vh;"></v-divider>
                    <v-sheet elevation="3" shaped>
                        <v-container style="padding : 3vh;">
                            <v-subheader>Usage history - 모든 이용기록은 트랜잭션으로 블록에 기록되며 위변조가 불가능합니다</v-subheader>
                            <p style="text-align :center; margin-top :3vh;" v-if="this.transactions.length == 0">이용내역이 없어요!</p>
                            <v-simple-table v-else>
                                <template>
                                <thead>
                                    <tr>
                                    <v-tooltip bottom>
                                        <template v-slot:activator="{ on, attrs }">
                                            <th class="text-left" v-bind="attrs" v-on="on">
                                                이용번호
                                            </th>
                                        </template>
                                        <span>트랜잭션 해시값의 일부를 표시합니다.</span>
                                    </v-tooltip>
                                    <v-tooltip bottom>
                                        <template v-slot:activator="{ on, attrs }">
                                            <th class="text-center" v-bind="attrs" v-on="on">
                                                이용내역
                                            </th>
                                        </template>
                                        <span>트랜잭션이 담긴 블럭의 내용을 해석하여 표시합니다.</span>
                                    </v-tooltip>
                                    <th class="text-left">
                                        유형
                                    </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr
                                    v-for="(transaction, i) in this.transactions"
                                    :key="i"
                                    >
                                    <td>{{ transaction.t_id }}</td>
                                    <td>{{ transaction.t_contents }}</td>
                                    <td>{{ transaction.t_type }}</td>
                                    </tr>
                                </tbody>
                                </template>
                            </v-simple-table>
                        </v-container>
                    </v-sheet>
                </v-container>
            </v-container>
        </v-container>
    </div>
</template>
<script>
import {mapState,mapMutations,mapActions} from 'vuex'

export default {
    data(){
        return{
            member: {},
        }
    },
    computed:{
        ...mapState({
            reservations : state => state.reservation.myReservations,
            houses : state => state.house.myHouses,
            transactions : state => state.transaction.transactions
        })
    },
    methods:{
        ...mapMutations(['setpwdDialog']),
        ...mapActions('reservation',['fetchReservationsByEmail']),
        ...mapActions('house',['fetchHousesByEmail']),
        ...mapActions('transaction',['fetchTransactionsByEmail']),
        setPassword(){
            this.setpwdDialog(true)
        }
    },
    mounted(){
        //
        this.member = this.$cookies.get('member')
        if(this.member.member_img == '' || this.member.member_img == null) this.member.member_img = require('../assets/avatar_default.png')
        this.fetchHousesByEmail(this.member.member_email)
        this.fetchReservationsByEmail(this.member.member_email)
        this.fetchTransactionsByEmail(this.member.member_email)
    }
}
</script>
<style scoped>
.profile{
    background-color: white;
    height: 100vh;
    padding : 0
}
</style>