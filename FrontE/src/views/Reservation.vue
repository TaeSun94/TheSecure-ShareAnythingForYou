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
                <v-col cols="3" v-for="house in this.recent_houses" :key="house.register_id" @click="reserve(house.register_id)">
                    <v-hover v-slot:default="{ hover }">
                        <v-card :elevation="hover ? 12 : 2">
                            <v-img :src="house.house_url" height="30vh">
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
                                    <div class="headline">{{house.house_address}}</div>
                                    <span class="grey--text">{{house.house_type}}</span>
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
                                v-for="(item,i) in 5"
                                :key="i"
                            >
                                <v-expansion-panel-header>Item</v-expansion-panel-header>
                                <v-expansion-panel-content>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
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
        }
    },
    methods:{
        ...mapActions('house',['fetchRecentHouses']),
        reserve(id){
            this.$router.push({
                name:'Reservation_detail',
                params:{
                    house_id : id
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