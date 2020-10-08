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
        <div id="cover" style="margin-top : -40vh;">
            <div>
                <div class="tb">
                <div class="td"><input type="text" placeholder="Search" required id="search_input" v-model="keyword"></div>
                <div class="td" id="s-cover">
                    <button id="search" @click="search">
                    <div id="s-circle"></div>
                    <span></span>
                    </button>
                </div>
                </div>
            </div>
        </div>
        <v-container class="recent_host">
            <p>원하는 숙소 예약하기</p>
        </v-container>
        <v-container v-if="this.houses.length > 0">
            <v-divider></v-divider>
            <v-container>
                <v-subheader>검색 결과 : {{result_keyword}}</v-subheader>
                <v-row>
                    <v-col cols="3" v-for="house in this.houses" :key="house.register_id" @click="reserve(house)">
                        <v-hover v-slot:default="{ hover }">
                            <v-card :elevation="hover ? 12 : 2" style="height : 50vh;">
                                <v-img :src="house.host_images[0]" height="30vh">
                                <v-expand-transition>
                                    <div
                                        v-if="hover"
                                        class="d-flex transition-fast-in-fast-out blue darken-2 v-card--reveal display-3 white--text"
                                        style="height: 100%;"
                                    >
                                        <v-btn color="deep-purple accent-4" class="btn-1" rounded height="4em"><p class="btn-name">예약하기</p></v-btn>
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
                                    <v-btn color="deep-purple accent-4" class="btn-1" rounded height="4em"><p class="btn-name">예약하기</p></v-btn>
                                </div>
                            </v-expand-transition>    
                            </v-img>
                            <v-card-title primary-title>
                                <div>
                                    <div class="headline">{{house.host_address}}</div>
                                    <span class="grey--text">{{house.host_type}}</span>
                                </div>
                            </v-card-title>
                            <v-card-text class="grey--text" style="margin-left:2vh;" ><span>{{house.host_capacity}}</span></v-card-text>
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
import {mapState,mapActions,mapMutations} from 'vuex'

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
            ],
            keyword : '',
            result_keyword : '',
        }
    },
    methods:{
        ...mapActions('house',['fetchRecentHouses','fetchHouseByKeyword']),
        ...mapMutations('house',['initHouses']),
        reserve(house){
            this.$router.push({
                name:'Reservation_detail',
                params:{
                    house_id : house.host_num,
                    house : house
                }
            })
        },
        search(){
            this.result_keyword = this.keyword
            this.fetchHouseByKeyword(this.keyword)
            this.keyword=''
        }
    },
    computed : {
        ...mapState({
            recent_houses : state => state.house.recentHouses,
            houses : state => state.house.houses
        })
    },
    mounted(){
        document.documentElement.scrollTop = 0;
        this.fetchRecentHouses()
    },
    beforeDestroy(){
        this.initHouses()
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


*
{
    outline: none;
}



body
{
    margin: 0;
    background-color: #ffd8d8;
}

.tb
{
    display: table;
    width: 100%;
}

.td
{
    display: table-cell;
    vertical-align: middle;
}

#search_input, #search
{
    color: #fff;
    font-family: Nunito;
    padding: 0;
    margin: 0;
    border: 0;
    background-color: transparent;
}

#cover
{
    position: absolute;
    top: 50%;
    left: 0;
    right: 0;
    /* width: 20px; */
    width: 90vh;
    padding: 35px;
    margin: -83px auto 0 auto;
    background-color: #ff7575;
    border-radius: 20px;
    box-shadow: 0 10px 40px #ff7c7c, 0 0 0 20px #ffffffeb;
    transform: scale(0.6);
}

/* form
{
    height: 96px;
} */

#search_input[type="text"]
{
    width: 100%;
    height: 96px;
    font-size: 60px;
    line-height: 1;
}

#search_input[type="text"]::placeholder
{
    /* color: #e16868; */
    color:whitesmoke;
}

#s-cover
{
    width: 1px;
    padding-left: 35px;
}

#search
{
    position: relative;
    display: block;
    width: 84px;
    height: 96px;
    cursor: pointer;
}

#s-circle
{
    position: relative;
    top: -8px;
    left: 0;
    width: 66px;
    height: 66px;
    margin-top: 0;
    border-width: 15px;
    border: 15px solid #fff;
    background-color: transparent;
    border-radius: 50%;
    transition: 0.5s ease all;
}

button span
{
    position: absolute;
    top: 68px;
    left: 43px;
    display: block;
    width: 45px;
    height: 15px;
    background-color: transparent;
    border-radius: 10px;
    transform: rotateZ(52deg);
    transition: 0.5s ease all;
}

button span:before, button span:after
{
    content: '';
    position: absolute;
    bottom: 0;
    right: 0;
    width: 45px;
    height: 15px;
    background-color: #fff;
    border-radius: 10px;
    transform: rotateZ(0);
    transition: 0.5s ease all;
}

#s-cover:hover #s-circle
{
    top: -1px;
    width: 67px;
    height: 15px;
    border-width: 0;
    background-color: #fff;
    border-radius: 20px;
}

#s-cover:hover span
{
    top: 50%;
    left: 56px;
    width: 25px;
    margin-top: -9px;
    transform: rotateZ(0);
}

#s-cover:hover button span:before
{
    bottom: 11px;
    transform: rotateZ(52deg);
}

#s-cover:hover button span:after
{
    bottom: -11px;
    transform: rotateZ(-52deg);
}
#s-cover:hover button span:before, #s-cover:hover button span:after
{
    right: -6px;
    width: 40px;
    background-color: #fff;
}

#ytd-url {
  display: block;
  position: fixed;
  right: 0;
  bottom: 0;
  padding: 10px 14px;
  margin: 20px;
  color: #fff;
  font-family: Nunito;
  font-size: 14px;
  text-decoration: none;
  background-color: #ff7575;
  border-radius: 4px;
  box-shadow: 0 10px 20px -5px rgba(255, 117, 117, 0.86);
  z-index: 125;
}
</style>