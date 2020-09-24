<template>
    <div id = "host">
        <v-container fluid>
            <v-layout column>
                <v-flex class="host_title">
                    <span style="margin-right : 100px;">Home Share(호스트)</span>
                    <v-btn>시작하기</v-btn>
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
                                <v-spacer></v-spacer>
                                <v-btn icon>
                                <v-icon>mdi-heart</v-icon>
                                </v-btn>
                
                                <v-btn icon>
                                <v-icon>mdi-bookmark</v-icon>
                                </v-btn>
                
                                <v-btn icon>
                                <v-icon>mdi-share-variant</v-icon>
                                </v-btn>
                            </v-card-actions>
                        </v-col>
                        <v-col cols="4">
                            <v-card-actions style ="height:30vh;">
                                <v-spacer></v-spacer>
                                
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
                            <v-spacer>hello</v-spacer>
                                <v-card-text>
                                    숙소 유형과 제공품목을 설정합니다.
                                </v-card-text>
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