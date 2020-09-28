<template>
    <div class="home_container">
        <v-app>
            
            <v-navigation-drawer app
            v-model="drawer"
            permanent = "permanent">
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title class="title">
                        2%
                        </v-list-item-title>
                        <v-list-item-subtitle>
                        당신의 숙소를 확인하세요
                        </v-list-item-subtitle>
                    </v-list-item-content>
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
            }
        },
    },
}
</script>
<style scoped>
.home_container{
    background-color : black
}
v-footer{
    background-color: black;
}

</style>