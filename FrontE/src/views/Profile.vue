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
                                    :src= "member.member_imgurl"
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
                                <v-list-item-subtitle v-if="member.public_key !== ''">{{member.public_key}}</v-list-item-subtitle>
                                <v-list-item-subtitle v-else><a @click.prevent="setPassword">지갑 생성하기</a></v-list-item-subtitle>
                            </v-list-item>
                        </v-col>
                    </v-row>
                </v-sheet>
                <v-container>
                    <v-divider style="margin : 3vh;"></v-divider>
                    <v-sheet elevation="3" shaped>
                        <v-container style="padding : 3vh;">
                            <v-subheader>Reservation - 예약</v-subheader>
                            <v-simple-table>
                                <template v-slot:default>
                                <thead>
                                    <tr>
                                    <th class="text-left">
                                        Name
                                    </th>
                                    <th class="text-left">
                                        Calories
                                    </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr
                                    v-for="item in desserts"
                                    :key="item.name"
                                    >
                                    <td>{{ item.name }}</td>
                                    <td>{{ item.calories }}</td>
                                    </tr>
                                </tbody>
                                </template>
                            </v-simple-table>
                        </v-container>
                    </v-sheet>
                </v-container>
                <v-container>
                    <v-divider style="margin : 3vh;"></v-divider>
                    <v-sheet elevation="3" shaped>
                        <v-container style="padding : 3vh;">
                            <v-subheader>Usage history - 모든 이용기록은 트랜잭션으로 블록에 기록되며 위변조가 불가능합니다</v-subheader>
                            <v-simple-table>
                                <template v-slot:default>
                                <thead>
                                    <tr>
                                    <th class="text-left">
                                        Name
                                    </th>
                                    <th class="text-left">
                                        Calories
                                    </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr
                                    v-for="item in desserts"
                                    :key="item.name"
                                    >
                                    <td>{{ item.name }}</td>
                                    <td>{{ item.calories }}</td>
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
import {mapMutations} from 'vuex'

export default {
    data(){
        return{
            member: {},
            reservations : 
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
            ],
            desserts: [
            {
                name: 'Frozen Yogurt',
                calories: 159,
            },
            {
                name: 'Ice cream sandwich',
                calories: 237,
            },
            {
                name: 'Eclair',
                calories: 262,
            },
            {
                name: 'Cupcake',
                calories: 305,
            },
            {
                name: 'Gingerbread',
                calories: 356,
            },
            {
                name: 'Jelly bean',
                calories: 375,
            },
            {
                name: 'Lollipop',
                calories: 392,
            },
            {
                name: 'Honeycomb',
                calories: 408,
            },
            {
                name: 'Donut',
                calories: 452,
            },
            {
                name: 'KitKat',
                calories: 518,
            },
            ],
        }
    },
    methods:{
        ...mapMutations(['setpwdDialog']),
        setPassword(){
            this.setpwdDialog(true)
        }
    },
    mounted(){
        //
        this.member = this.$cookies.get('member') 
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