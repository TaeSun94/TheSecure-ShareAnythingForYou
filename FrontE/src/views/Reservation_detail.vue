<template>
    <div id="reservation_detail">
        <v-container>
            <v-subheader style="margin-left:20vh;">숙소 사진</v-subheader>
            <v-divider></v-divider>
            <v-carousel style="width:60%; margin: 2em auto;"
            :hide-delimiter-background="hide">
                <v-carousel-item
                v-for="(item,i) in house.host_images"
                :key="i"
                :src="item"
                ></v-carousel-item>
            </v-carousel>
        </v-container>
        <v-container>
            <v-subheader style="margin-left:20vh;">상세 정보</v-subheader>
            <v-divider></v-divider>
            <v-row style="padding : 0 20vh 0;">
                <v-col cols="8">
                    <v-sheet rounded="rounded" elevation="5">
                        <v-row>
                        <v-col cols="8">
                        <v-list-item>
                            <v-list-item-icon>
                                <v-icon>mdi-send</v-icon>
                            </v-list-item-icon>
                            <v-list-item-subtitle>{{house.host_address}}</v-list-item-subtitle>
                        </v-list-item> 

                        <v-list-item>
                            <v-list-item-icon>
                                <v-icon>mdi-send</v-icon>
                            </v-list-item-icon>
                            <v-list-item-subtitle>{{house.host_type}}</v-list-item-subtitle>
                        </v-list-item> 

                        <v-list-item>
                            <v-list-item-icon>
                                <v-icon>mdi-send</v-icon>
                            </v-list-item-icon>
                            <v-list-item-subtitle>{{house.host_capacity}}</v-list-item-subtitle>
                        </v-list-item> 
                        </v-col>
                        <v-col cols="4" class="display-1" align-self="center">
                            <v-subheader>1박 비용</v-subheader>
                            {{house.host_price}}  원
                        </v-col>
                        </v-row>
                        
                        <v-divider></v-divider>
                        <v-list-item style="padding:2em;">
                            <v-subheader style="margin-right:1em;">제공물품</v-subheader>
                            <v-row align="center">
                                <v-col cols="6">
                                <v-checkbox style="margin-top:0;"
                                    v-model="options[0]"
                                    label="TV"
                                    color="red"
                                    hide-details
                                    readonly
                                ></v-checkbox>
                                <v-checkbox
                                    v-model="options[1]"
                                    label="냉장고"
                                    color="indigo"
                                    hide-details
                                    readonly
                                ></v-checkbox>
                                <v-checkbox
                                    v-model="options[2]"
                                    label="전자레인지"
                                    color="orange"
                                    hide-details
                                    readonly
                                ></v-checkbox>
                                <v-checkbox
                                    v-model="options[3]"
                                    label="주차장"
                                    color="primary"
                                    hide-details
                                    readonly
                                ></v-checkbox>
                                <v-checkbox
                                    v-model="options[4]"
                                    label="WI-FI"
                                    color="success"
                                    hide-details
                                    readonly
                                ></v-checkbox>
                                </v-col>
                                <v-col cols="6">
                                <v-checkbox style="margin-top:0;"
                                    v-model="options[5]"
                                    label="수건"
                                    color="red"
                                    hide-details
                                    readonly
                                ></v-checkbox>
                                <v-checkbox
                                    v-model="options[6]"
                                    label="세면 도구"
                                    color="indigo"
                                    hide-details
                                    readonly
                                ></v-checkbox>
                                <v-checkbox
                                    v-model="options[7]"
                                    label="드라이기"
                                    color="orange"
                                    hide-details
                                    readonly
                                ></v-checkbox>
                                <v-checkbox
                                    v-model="options[8]"
                                    label="침대"
                                    color="primary"
                                    hide-details
                                    readonly
                                ></v-checkbox>
                                <v-checkbox
                                    v-model="options[9]"
                                    label="발코니"
                                    color="success"
                                    hide-details
                                    readonly
                                ></v-checkbox>
                                </v-col>
                            </v-row>
                        </v-list-item> 
                    </v-sheet>
                </v-col>
                <v-col cols="4">
                    <v-sheet rounded="rounded" elevation="5">
                        <v-subheader>Host</v-subheader>
                        <v-container style="text-align:center;">
                        <v-avatar size="8em">
                            <img
                                :src= "member.member_img"
                                alt="John"
                            >
                        </v-avatar>
                        <p class="profile_nickname">{{member.member_nickname}}</p>
                        <v-divider></v-divider>
                        <v-list-item>
                            <v-list-item-icon>
                                <v-icon>mdi-mail</v-icon>
                            </v-list-item-icon>
                            <v-list-item-subtitle>{{member.member_email}}</v-list-item-subtitle>
                        </v-list-item>
                        </v-container>
                    </v-sheet>
                </v-col>
            </v-row>
        </v-container>
        <v-container>
            <v-subheader style="margin-left:20vh;">날짜 선택</v-subheader>
            <v-divider></v-divider>
            <v-container style="padding : 0 20vh 0;">
                <v-row>
                    <v-col cols="12" sm="6">
                        <v-date-picker
                        v-model="dates"
                        :allowed-dates="getAllowedDates"
                        range
                        :show-current="showCurrent"
                        ></v-date-picker>
                    </v-col>
                    <v-col cols="12" sm="6">
                        <template>
                            <v-text-field
                                v-model="checkInDay"
                                label="Check In"
                                prepend-icon="mdi-view-dashboard"
                                readonly
                                clearable
                                style="margin-bottom:0; padding-bottom:0;"
                            ></v-text-field>
                            <v-text-field
                                v-model="checkOutDay"
                                label="Check Out"
                                prepend-icon="mdi-view-dashboard"
                                readonly
                                clearable
                                style="margin-top: 0em;"
                            ></v-text-field>
                            <v-row style="height: 15vh;" v-if="dates.length >= 2">
                                <v-col cols="6">
                                <div> &#8361;{{house.host_price}} X {{reservation.reservation_days.length-1}}박</div>
                                </v-col>

                                <v-col cols="6" style="text-align:right;">
                                    <div> &#8361; {{(reservation.reservation_days.length-1) * house.host_price}}원</div>
                                </v-col>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row v-if="dates.length >= 2" style="margin-top: 0;">
                                <v-col cols="8" style="margin:auto;">
                                    <p style="margin:auto;"><strong>결제하기</strong></p>
                                </v-col>
                                <v-col cols="4" style="text-align:right;">
                                    <!-- <button class="img-btn" @click="linkKakao()">
                                    <v-img src="../assets/kakao_payment.png"></v-img>
                                    </button> -->
                                    <v-btn rounded color="deep-purple accent-4" dark @click="reserve">
                                        예약하기
                                    </v-btn>
                                </v-col>
                            </v-row>
                            
                        </template>
                    </v-col>
                </v-row>
            </v-container>
        </v-container>
        <!-- <v-container>
            <v-subheader style="margin-left:20vh;">후기</v-subheader>
            <v-divider></v-divider>
            <v-container>
                <v-flex v-if="reviews.length == 0" style="height : 30vh; padding-top : 15vh;">
                    <p class="No_review">아직 등록된 후기가 없어요!</p>
                </v-flex>
                <v-flex v-else>
                    <v-col cols="6">

                    </v-col>
                </v-flex>
            </v-container>
        </v-container> -->
    </div>
</template>
<script>
import {mapState,mapActions} from 'vuex'
var check = true
export default {
    data(){
        return{
            hide : true,
            house_id : this.$route.params.house_id,
            member : {},
            house : {},
            rounded : true,
            options:[],
            //달력
            dates: [],
            allowedDates : [],
            showCurrent:true,
            
            //가격
            housePriceFormat : '',
            resultPriceFormat : '',

            //예약
            reservation : {
                member_email : '',
                host_num : '',
                reservation_days: [],
                reservation_price : '',
            },
            reviews : [],
        }
    },
    computed:{
        checkOutDay:{
            get(){
                if(this.dates.length > 1){
                    this.calculateDates(this.dates[0],this.dates[this.dates.length-1])
                    if(check){
                        return this.dates[this.dates.length-1]
                    }else{
                        this.reservation.reservation_days=[]
                        this.dates = []
                        check = true
                        return ''
                    }
                }
                else{
                    this.reservation.reservation_days=[]
                    return ''
                }
            },
            set(val){
                this.reservation.reservation_days=[]
                return this.dates = []
            }
        },
        checkInDay:{
            get(){
                if(this.dates.length == 0){
                    this.reservation.reservation_days=[]
                    return ''
                }
                else
                    return this.dates[0]
            },        
            set(val){
                this.reservation.reservation_days=[]
                return this.dates = []
            }
        }
    },
    methods:{
        ...mapActions('reservation',['reserveHouses']),
        gettomorrow(){
            var today = new Date();
            var year = today.getFullYear();
            var month = today.getMonth();
            var day = today.getDate();
            // this.current = new Date(this.year,this.month,this.day)
            today.setDate(today.getDate()+1)
            var dates = new Array()
            dates[0] = today.toISOString().substr(0,10)
            // this.houseDates = dates
            // this.dates = dates
        },
        linkKakao(){
            var url = "https://www.naver.com";
            var name = "네이버";
            window.open(url,"Naver");
        },
        calculateDates(start,end){
            // start : '2020-09-30' end : '2020-10-04' 계산하기
            //하루씩 더해가면서 다음날이 end와 같으면 거기서 종료시키기
            //같지 않으면 예약일배열에 넣기
            if(start == end){
                swal({
                      text : "이용기간을 하루이상 선택해주세요",
                      icon: "error"
                  })
                check = false
            }
            var year = start.substr(0,4) *1
            var month = start.substr(5,2) *1 - 1
            var date = start.substr(8,2) *1
            var start = new Date(year,month,date,9)//UTC기준이라 9시아래는 더하기 1을했을떄 다음날이아님 당일이나옴
            var dayformat = start.toISOString().substr(0,10)
            this.reservation.reservation_days.push(dayformat)
            while(dayformat != end){
                start.setDate(start.getDate() + 1)
                dayformat = start.toISOString().substr(0,10)
                if(!this.allowedDates.includes(dayformat)){
                    swal({
                      text : "이용가능기간을 연속되게 선택해주세요",
                      icon: "error"
                  })
                    check= false
                    break;
                }
                this.reservation.reservation_days.push(dayformat)
            }
            //같아지면
        },
        getAllowedDates (val){
            if (this.allowedDates.indexOf(val) !== -1) {
                return true
            } else {
                return false
            }
        },
        reserve(){
            var reserveData={
                host_num : this.house.host_num,
                member_email : this.$cookies.get('member').member_email,
                price : this.house.host_price * (this.reservation.reservation_days.length-1),
                reserve_day : this.reservation.reservation_days
            }
            this.reserveHouses(reserveData)
        }
    },
    mounted(){
        this.gettomorrow()
        this.house = this.$route.params.house
        if(this.house.member.member_img == '' || this.house.member.member_img == undefined) this.house.member.member_img = require('../assets/avatar_default.png')
        this.allowedDates = this.house.host_available_day
        this.options = this.house.host_provide_items
        this.member = this.house.member
    }
}
</script>
<style scoped>
#reservation_detail{
    background-color: white;
    height: 100vh;
    padding : 0
}
.profile_nickname{
    font-size: 2em;
    margin-top : 0.5em;
    margin-bottom : 0;
}
.img-btn{
    border : 0;
    outline: 0;
}
.No_review{
    text-align: center;
}
</style>