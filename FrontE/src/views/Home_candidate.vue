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
                >
                    <v-list-item-icon>
                    <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>
        
                    <v-list-item-content>
                    <v-list-item-title @click="excuteMenu(item.title)">{{ item.title }}</v-list-item-title>
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
                <v-container fluid>
                    <!-- If using vue-router -->
                    <router-view></router-view>
                    <v-carousel
                        cycle
                        height="100%"
                        hide-delimiter-background
                        show-arrows-on-hover="vertical"
                        interval="3000"
                        vertical="true"
                        vertical-delimiters="right"
                    >
                        <v-carousel-item
                        v-for="(slide, i) in slides"
                        :key="i"
                        >
                        <v-sheet
                            :color="colors[i]"
                            height="95vh"
                        >
                            <v-row
                            class="fill-height"
                            align="center"
                            justify="center"
                            >
                            <!-- 페이지 별 내용 입력 -->
                            <!-- <div class="display-3">{{ slide }}</div>  -->
                            <div class="display-3">{{ slide }}</div> 
                            </v-row>
                        </v-sheet>
                        </v-carousel-item>
                    </v-carousel> 
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

export default {
    data () {
    return {
        drawer : true,
        permanent : true,
        expandOnHover : true,
        items: [
            { title: 'Reservation', icon: 'mdi-image' },
            { title: 'Home-Sharing', icon: 'mdi-help-box' },
            { title: 'Review', icon: 'mdi-view-dashboard' },
            { title: 'FQA', icon: 'mdi-view-dashboard' },
            { title: 'Logout', icon:'mdi-exit-run'},
        ],
        right: null,
        colors: [
          'indigo',
          'warning',
          'pink darken-2',
          'red lighten-1',
          'deep-purple accent-4',
        ],
        slides: [
          'Introduction',
          'Reservation',
          'Home_Sharing',
          'Leave a Review',
          'FAQ',
        ],
        }
    },
    methods:{
        ...mapMutations('member',['setisLogin']),
        excuteMenu(menu) {
            switch(menu){
                // 로그아웃
                case "Logout":
                    this.$cookies.remove("code");
                    this.$router.push({ name : 'Cover'})
                    //this.$cookies.remove("token");
                    this.setisLogin(false);
                    break;
                case "Home-Sharing":
                    this.$router.push({name : 'HomeSharing'})
            }
        }
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