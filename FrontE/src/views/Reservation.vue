<template>
    <div id="reservation">
        <v-container fluid style="padding : 0">
            <v-carousel
                cycle
                height="35vh"
                hide-delimiter-background
                hide-delimiters
                interval="3000"
                :show-arrows="showArrow"
            >
                <v-carousel-item
                v-for="(slide, i) in slides"
                :key="i"
                :src="slide.src"
                >
                </v-carousel-item>
            </v-carousel>
            <v-container id=""></v-container>
        </v-container>

        <v-container class="recent_host">
            <p>원하는 숙소 예약하기</p>
        </v-container>
        <v-divider></v-divider>
        <v-container>
            <v-subheader>최근에 등록된 호스트의 숙소</v-subheader>
            <v-row>
                <v-col cols="3" v-for="house in this.recent_houses" :key="house.register_id" @click="reserve(house)">
                    <v-hover v-slot:default="{ hover }">
                        <v-card :elevation="hover ? 12 : 2" style="height : 50vh;">
                            <v-img :src="house.host_images[0]" height="30vh">
                            <v-expand-transition>
                                <div
                                    v-if="hover"
                                    class="d-flex transition-fast-in-fast-out blue darken-2 v-card--reveal display-3 white--text"
                                    style="height: 100%;"
                                >
                                    <v-btn color="deep-purple accent-4" class="btn-1" rounded height="4em"><span class="btn-name">예약하기</span></v-btn>
                                </div>
                            </v-expand-transition>    
                            </v-img>
                            <v-card-title primary-title>
                                <div>
                                    <div class="headline">{{house.host_address}}</div>
                                    <span class="grey--text">{{house.host_type}}</span>
                                </div>
                            </v-card-title>
                        </v-card>
                    </v-hover>
                </v-col>
            </v-row>
        </v-container>
        <v-container style="margin-top:5em;">
            <v-flex class="host_contents" style="height:60vh;">
                    <p>자주하는 질문(FAQ) </p>
                    <v-divider></v-divider>
                    <v-sheet elevation="10" style="margin-top: 5em;">
                        <v-row justify="center">
                            <v-expansion-panels popout
                            focusable
                            hover>
                                <v-expansion-panel
                                    v-for="(item,i) in items"
                                    :key="i"
                                >
                                    <v-expansion-panel-header>
                                        {{item.subject}}
                                    </v-expansion-panel-header>
                                    <v-expansion-panel-content>
                                        {{item.text}}
                                    </v-expansion-panel-content>
                                </v-expansion-panel>
                            </v-expansion-panels>
                        </v-row>
                    </v-sheet>
                </v-flex>
        </v-container>
    </div>
</template>
<script>
import {mapState,mapActions} from 'vuex'

export default {
    data(){
        return{
            vartical : false,
            showArrow : false,
            slides: [
                {
                    src: require('../assets/봄.jpg')
                },
                {
                    src: require('../assets/여름.jpg')
                },
                {
                    src: require('../assets/가을.jpg')
                },
                {
                    src: require('../assets/겨울.jpg')
                }
            ],
            items:[
                {subject: "예약 확인은 어떻게 하나요?", text:"완료된 예약의 경우 왼쪽 사이드바 상단의 프로필 사진을 클릭하면 My page로 넘어가 확인하실 수 있습니다."},
                {subject: "게스트가 예약에 대하여 믿고 쓸 수 있을까요?", text:"예약의 경우 블록에 저장하는 데이터를 통하여 사용자들에게 신뢰를 주고 모두가 확인할 수 있어서 투명성을 보장합니다."},
                {subject: "블록체인에 트랜잭션을 호출하기 위하여 일정량의 암호 화폐가 필요한데 충전은 어떻게 하나요?", text:"저희 서비스는 사설 이더리움 네트워크를 사용하기 때문에 수수료없이 트랜잭션을 발행할 수 있고 그로 인해 충전의 기능은 따로 제공하지 않습니다. 대신 Account 생성을 할 때 서비스 이용을 위한 소정의 Ether를 전송하여 불편함없이 이용하실 수 있습니다."},
            ]
        }
    },
    methods:{
        ...mapActions('house',['fetchRecentHouses']),
        reserve(house){
            this.$router.push({
                name:'Reservation_detail',
                params:{
                    house_id : house.host_num,
                    house : house
                }
            })
        }
    },
    computed : {
        ...mapState({
            recent_houses : state => state.house.recentHouses
        })
    },
    mounted(){
        this.fetchRecentHouses()
    }

}
</script>
<style scoped>
#reservation{
    background-color: white;
    height: 100vh;
    padding : 0
}
p{
    font-size: 2.5em;
    font-style: bald;

}
.span{
    text-align: left;
    padding-left: 0%;
    margin-left: 0%;

}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  position: absolute;
  width: 100%;
  opacity: .5;
}
.btn-1:not(.v-card--reveal){
  opacity: 2.5;
  bottom: 10;
  
}

.btn-name{
    color: white;
    font-size: 1em;
    font-style: unset;
}
</style>