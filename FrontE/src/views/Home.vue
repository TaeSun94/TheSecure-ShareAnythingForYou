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
                    <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                </v-list>
            </v-navigation-drawer>

            <v-app-bar app>
                2%
            </v-app-bar>

            <!-- Sizes your content based upon application components -->
            <v-main>

                <!-- Provides the application the proper gutter -->
                <v-container fluid>
                    <!-- If using vue-router -->
                    <router-view></router-view>
                </v-container>
            </v-main>

            <v-footer app>
                <h5>2%</h5>
                <span>유태선 김기영 이완희 이한별</span>
            </v-footer>
        </v-app>
    </div>
</template>
<script>
export default {
    data () {
    return {
        drawer : true,
        permanent : true,
        expandOnHover : true,
        items: [
            { title: 'Dashboard', icon: 'mdi-view-dashboard' },
            { title: 'Photos', icon: 'mdi-image' },
            { title: 'About', icon: 'mdi-help-box' },
        ],
        right: null,
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