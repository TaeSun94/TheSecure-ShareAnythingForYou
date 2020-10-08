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
                                :src="member.member_img"
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
                    <v-list-item-subtitle v-if="member.public_key !== null">{{member.public_key}}</v-list-item-subtitle>
                    <v-list-item-subtitle v-else>
                        <div>
                            <v-dialog
                            v-model="dialog"
                            width="60vh"
                            >
                            <template v-slot:activator="{ on, attrs }">
                                <a @click.prevent v-bind="attrs"
                                v-on="on">지갑 생성하기</a>
                            </template>

                            <v-card>
                                <v-card-title class="headline grey lighten-2">
                                비밀번호 설정하기
                                </v-card-title>

                                <v-card-text style="padding-bottom:0; margin-top: 2vh;">
                                    2%에서 서명에 사용되는 비밀번호입니다.<br>
                                    <strong>잊지마세요!</strong>
                                </v-card-text>
                                <v-form ref="form" style="margin-top: 0; padding : 0 2vh;">
                                    <v-text-field v-model="password" label="비밀번호" :rules="passwordRules" 
                                    :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'" :type="show1 ? 'text' : 'password'" @click:append="show1 = !show1"></v-text-field>
                                    <v-text-field v-model="password_confirm" label="비밀번호 확인" :rules="passwordConfirmRule"
                                    :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'" :type="show2? 'text' : 'password'" @click:append="show1 = !show2"></v-text-field>
                                </v-form>
                                <v-divider></v-divider>

                                <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn
                                    color="primary"
                                    text
                                    @click="setPassword"
                                >
                                    설정하기
                                </v-btn>
                                <v-btn
                                    color="primary"
                                    text
                                    @click="closeDialog"
                                >
                                    취소
                                </v-btn>
                                </v-card-actions>
                            </v-card>
                            </v-dialog>
                        </div>
                    </v-list-item-subtitle>
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
                <v-container fluid style="padding: 0px;">
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
import {mapMutations,mapActions} from 'vuex'
import HomeContents from './HomeContents.vue'
export default {
    data () {
    return {
        // dialog : false,
        drawer : true,
        permanent : true,
        expandOnHover : true,
        password : undefined,
        password_confirm : '',
        passwordRules: [
            value => !!value || '비밀번호는 필수 값입니다.',
            value => (value && value.length >= 4) || '비밀번호의 길이는 4자 이상이어야 합니다.',
        ],
        passwordConfirmRule : [v => (v && this.validate) || '비밀번호가 일치하지 않습니다.'],
        show1 : false,
        show2 : false,
        items: [
            { title: 'Our Service', icon: 'mdi-view-dashboard'},
            { title: 'Reservation', icon: 'mdi-image' },
            { title: 'Home-Sharing', icon: 'mdi-view-dashboard' },
            // { title: 'Review', icon: 'mdi-view-dashboard' },
            { title: 'FAQ', icon: 'mdi-help-box' },
            { title: 'Logout', icon:'mdi-exit-run'},
        ],
        right: null,
        member : 
        {
            // member_email : '',
            // member_nickname : '',
            // member_email : '',
            member_img : ''
        },
        
        }
        
    },
    computed : {
        dialog : {
            get(){
                return this.$store.state.pwdDialog
            },
            set(val){
                return this.setpwdDialog(val)
            }
        },
        validate(){
            return (this.password != undefined && this.password.length >= 4) && (this.password == this.password_confirm) 
        }
        
    },
    watch : {
        dialog(){
            if(this.dialog == false)
                this.$refs.form.reset()
        }
    },
    methods:{
        ...mapMutations('member',['setisLogin']),
        ...mapMutations(['setpwdDialog']),
        ...mapActions('member',['setMemberPassword']),
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
        setPassword(){
            if(this.validate){
                this.member.password = this.password
                this.setMemberPassword(this.member)
                this.setpwdDialog(false)
                // this.dialog = false
            }else{
                alert("비밀번호를 확인하세요")
                this.$refs.form.reset()
                // this.password=''
            }
        },
        closeDialog(){
            // this.dialog = false
            this.setpwdDialog(false)
            this.$refs.form.reset()
            // this.password=''
        }
    },
    mounted(){
        this.member = this.$cookies.get('member')
        // this.member.member_img = ''
        if(this.member.member_img == '' || this.member.member_img == null) this.member.member_img = require('../assets/avatar_default.png')
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