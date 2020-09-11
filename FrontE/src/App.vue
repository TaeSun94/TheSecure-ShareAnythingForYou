<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import {mapMutations,mapActions} from 'vuex'

export default {
  methods:{
    ...mapMutations('member',['setisLogin']),
    ...mapActions('member',['getAccessToken']),
    getParameters(paramName) { 
      // 리턴값을 위한 변수 선언 
      var returnValue; 
      // 현재 URL 가져오기 
      var url = location.href; 
      // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔 
      var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&'); 
      // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 
      for (var i = 0; i < parameters.length; i++) { 
        var varName = parameters[i].split('=')[0]; 
        if (varName.toUpperCase() == paramName.toUpperCase()) {
            returnValue = parameters[i].split('=')[1];
            return decodeURIComponent(returnValue); 
        } 
      }
    },
  },
  mounted(){
    let code = this.getParameters('code')
    let error = this.getParameters('error')
    if(error !== undefined){
      this.$router.push({'name' : 'Cover'})
    }
    else if(code !== undefined){
      console.log(code)
      this.$cookies.set("code",code,"1h")
      //this.getAccessToken(code)
      // user id 도 저장할거임 
      this.$router.push({'name' : 'Home'})
    }

    //
    if(this.$cookies.get('code') !== null){
      this.setisLogin(true);
    }
    // if(this.$cookie.get('token') !== null){
    //   this.setisLogin(true);
    // }
  }
}
</script>

<style>
#app {
font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-image: url(./assets/background.png);
  background-repeat: repeat;
  background-size:contain;
  height: 100vh;
  width: 100%;
}
</style>
