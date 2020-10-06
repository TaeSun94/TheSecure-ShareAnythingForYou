<template>
    <div class="home_container">
        <v-app>
            
            <v-navigation-drawer app
            v-model="drawer"
            permanent = "permanent">
                <v-list-item>
                    <v-container style="text-align:center;">
                        <v-avatar size="6em" @click="moveProfile">
                            <img
                                :src= "member.member_imgurl"
                                alt="카카오프로필이미지"
                                
                            >
                        </v-avatar>
                        <p class="profile_nickname">{{member.member_nickname}} 님</p>
                    </v-container>
                </v-list-item>
                <v-divider></v-divider>
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
                    <v-list-item-subtitle v-else><a @click.prevent="makeWallet">지갑 생성하기</a></v-list-item-subtitle>
                </v-list-item>
                <v-divider></v-divider>
                <v-list
                dense
                nav
                >
                <v-list-item
                    v-for="item in items"
                    :key="item.title"
                    link
                    @click="excuteMenu(item.title)"
                >
                    <v-list-item-icon>
                    <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>
        
                    <v-list-item-content >
                    <v-list-item-title >{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                </v-list>
            </v-navigation-drawer>

            <!-- <v-app-bar app>
                2%
            </v-app-bar> -->

            <!-- Sizes your content based upon application components -->
            <v-main>

                <!-- Provides the application the proper gutter -->
                <v-container fluid style="padding-top:0px; padding-bottom:0px;">
                    <!-- If using vue-router -->
                    <router-view></router-view>
                </v-container>
            </v-main>

            <!-- <v-footer app>
                <h5>2%</h5>
                <span>유태선 김기영 이완희 이한별</span>
            </v-footer> -->
        </v-app>
    </div>
</template>
<script>
import {mapMutations} from 'vuex'
import HomeContents from './HomeContents.vue'
export default {
    data () {
    return {
        drawer : true,
        permanent : true,
        expandOnHover : true,
        items: [
            { title: 'Our Service', icon: 'mdi-view-dashboard'},
            { title: 'Reservation', icon: 'mdi-image' },
            { title: 'Home-Sharing', icon: 'mdi-help-box' },
            { title: 'Review', icon: 'mdi-view-dashboard' },
            { title: 'FAQ', icon: 'mdi-view-dashboard' },
            { title: 'Logout', icon:'mdi-exit-run'},
        ],
        right: null,
        member : 
            {
                // member_email : '',
                // member_nickname : '',
                // member_email : '',
                // member_imgurl : ''
            }
        }
    },
    methods:{
        ...mapMutations('member',['setisLogin']),
        excuteMenu(menu) {
            switch(menu){
                // 로그아웃
                case "Our Service":
                    this.$router.push({ path: '/home' })
                    .catch(()=>{});
                    break;
                case "Logout":
                    this.$cookies.remove("code");
                    this.$router.push({ name : 'Cover'})
                    //this.$cookies.remove("token");
                    this.setisLogin(false);
                    break;
                case "Home-Sharing":
                    //window.addEventListener('mousewheel',function(e){return true;})
                    this.$router.push({name : 'Sharing'})
                    .catch(()=>{});
                    break;
                case "Reservation":
                    this.$router.push({name : 'Reservation'}).catch(()=>{})
                    break
                case "FAQ":
                    this.$router.push({name : 'FAQ'}).catch(()=>{})
                    break
            }
        },
        moveProfile(){
            this.$router.push({name : 'Profile'}).catch(()=>{});
        },
        makeWallet(){
            alert("지갑 생성하기!")
        }
    },
    mounted(){
        // member = this.$cookies.get('member')
        this.member =
                {
                "member_email": "test@gmail.com",
                "member_nickname": "testnickname",
                "member_imgurl" : "http://k.kakaocdn.net/dn/bpLTxG/btqJRmspqjR/390YKJMfPaukWYDUbbMcz1/img_640x640.jpg",
                // "public_key": "0xA8566F6bC8FB2E46B14e9eEb282ecDc0e53AA37C"
                "public_key": ""
                }
        
    }
}
</script>
<style scoped>
.home_container{
    background-color : black
}
v-footer{
    background-color: black;
}
.profile_nickname{
    font-size: 1em;
    margin-top : 0.5em;
    margin-bottom : 0.5em;
}
</style>