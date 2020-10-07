<template>
    <div id = "host">
        <v-container fluid style="text-align:center;">
            <v-layout column>
                <v-flex class="host_title">
                    <span style="margin-right : 100px;">Home Share(호스트)</span>
                </v-flex>
                <v-flex class="host_contents">
                    <p>호스팅 하기(도움말)</p>
                    <v-sheet elevation="10" height="70vh;">
                        <v-row dense>
                        <v-col cols="4">
                            <v-card>
                            <v-img
                                :src="cards[0].src"
                                class="white--text align-end"
                                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                height="40vh"
                            >
                                <v-card-title v-text="cards[0].title"></v-card-title>
                            </v-img>
                            </v-card>
                            <v-card-actions>
                                <div class="col-lg-4" style="font-size: 20px; font-weight:bolder;">
                                    무료로 Host <br>등록하기
                                </div>
                                <div class="col-lg-8">
                                    남는 방 하나부터 집 전체까지, 다양한 형태의 공간을 무료로 등록하고 공유하세요.
                                </div>
                            </v-card-actions>
                        </v-col>
                        <v-col cols="4">
                            <v-card-actions>
                                <div class="col-lg-4" style="font-size: 20px; font-weight:bolder;  text-align: center;">
                                    Host 세부사항<br>설정하기
                                </div>
                                <div class="col-lg-8">
                                    호스팅할 수 있는 날짜, 숙소 요금, 게스트 필수요건을 선택하세요. 2%가 호스팅 전반에 걸쳐 도움을 드립니다.
                                </div>
                            </v-card-actions>
                            <v-card>
                            <v-img
                                :src="cards[1].src"
                                class="white--text align-end"
                                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                height="40vh"
                            >
                                <v-card-title v-text="cards[1].title"></v-card-title>
                            </v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="4">
                            <v-card>
                            <v-img
                                :src="cards[2].src"
                                class="white--text align-end"
                                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                height="40vh"
                            >
                                <v-card-title v-text="cards[2].title"></v-card-title>
                            </v-img>
                            </v-card>
                            <v-card-actions>
                                <div class="col-lg-3" style="font-size: 20px; font-weight:bolder;  text-align: center;">
                                    첫 게스트<br>맞이하기
                                </div>
                                <div class="col-lg-9">
                                    숙소가 게시되면 설정하신 예약 조건에 부합하는 게스트가 예약을 요청할 거에요. 게스트를 맞이하기 전에 궁금한 점이 있으면 언제든 E-mail로 물어볼 수 있습니다.
                                </div>
                            </v-card-actions>
                        </v-col>    
                        </v-row>
                    </v-sheet>
                </v-flex>
                <v-flex class="host_contents" style="height:60vh;">
                    <p>자주하는 질문(FAQ) </p>
                    <v-sheet elevation="10" >
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
            </v-layout>
            
        </v-container>
        <v-dialog v-model="dialog" persistent max-width="150vh">
      <template v-slot:activator="{ on, attrs }">
        <div style="display:scroll;position:fixed;bottom:10vh;right:15vh; z-index:3" @click="openModal" v>
                <v-btn 
                height="8vh" 
                color="deep-purple accent-4"
                v-bind="attrs"
                v-on="on"
                :rounded= btnval
                ><span class="btn-name" >숙소 등록하기</span></v-btn>
        </div> 
      </template>
      <v-card>
        <!-- <v-card-title>
          <span class="headline">Register Your House</span>
        </v-card-title>
        <v-card-text >
        </v-card-text> -->
        <SharingStep style="width : inherit;" @closeModal="closeModal"></SharingStep>
        <!-- <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
        </v-card-actions> -->
      </v-card>
    </v-dialog>
    </div>
</template>
<script>
import SharingStep from '@/components/SharingStep.vue'

export default {
    data(){
        return{
            dialog: false,
            modalOn: "",
            btnval : true,
            cards: [
            { title: '1단계', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 4 },
            { title: '2단계', src: 'https://cdn.vuetifyjs.com/images/cards/road.jpg', flex: 4 },
            { title: '3단계', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 4 },
            ],
            items:[
                {subject: "SSAFY 호스트가 되기 위한 조건이 있나요?", text:"대부분의 지역에서 쉽게 SSAFY 호스트가 될 수 있고, 숙소는 언제든지 무료로 등록할 수 있습니다. SSAFY를 통해 아파트와 집 전체, 개인실, 트리하우스, 성 등 다양한 숙소를 공유할 수 있습니다."},
                {subject: "재산 피해에 대비한 보호 장치에는 어떤 것이 있나요?", text:"호스트 보호 프로그램은 현금, 증권, 수집품, 희귀 예술작품, 보석, 반려동물 또는 대인 피해에 대한 책임 배상에는 적용되지 않습니다. 따라서 숙소를 호스팅하기에 앞서 귀중품은 안전하게 보관하거나 치우실 것을 권장합니다. 또한, 이 프로그램은 마모로 인한 재산상의 손실이나 피해를 보상하지 않습니다."},
                {subject: "숙소 등록 비용은 얼마인가요?", text:"회원 가입과 숙소 등록은 무료이지만, 예약을 받은 호스트에게는 일반적으로 일정량 서비스 수수료가 부과되어, SSAFY 운영 비용을 충당하는 데 사용됩니다."},
            ]
        }
    },
    components:{
       SharingStep,
    },
    methods:{
        openModal () {
        this.modalOn = "one"    
        },
        closeModal(){
        this.modalOn= "one out",
        this.dialog = false
        }
    },
    mounted(){
        document.documentElement.scrollTop = 0;
    }    
}
</script>
<style scoped>
#host{
    background-color: white;
    height: 100vh;
}
.host_title{
    margin : 10px;
    display: inline-block;
}
.host_contents{
    /* height  */
    margin-bottom: 10vh;
}
p{
    font-size: 2.5em;
    font-style: bald;

}
.btn-name{
    color: white;
    font-size: 1em;
    font-style: unset;
}
</style>