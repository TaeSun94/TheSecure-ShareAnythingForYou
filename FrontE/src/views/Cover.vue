<template>
    <div class="Home">
        <div class="drawsvg">
            <svg version="1.1" viewBox="0 0 700 300">
                <symbol id="fade-text">
                <text x="45%" y="40%" text-anchor="middle" style="font">Welcome to SSAFY</text>
                <text x="55%" y="60%" text-anchor="middle">by 2% </text>
                </symbol>
                <g> <use class="stroke" xlink:href="#fade-text"/> <use class="fill" xlink:href="#fade-text"/> </g>
            </svg>
        </div>
        <div class ="col" style="text-align:center;">
          <div v-if ="this.isLogin">
            <button @click="moveHome()">Home</button>
          </div>            
          <div v-else>
            <!-- <button style="margin-right : 10px;" @click="moveHome()">Test</button>  -->
            <button @click="openModal()">Go</button>
          </div>
        </div>
        <Login :modalOn="modalOn" @closeModal="closeModal"/>
    </div>
</template>

<script>
import Login from '@/components/Login.vue'
import {mapState} from 'vuex'

export default {
  components: {
    Login
  },
  data(){
    return {
      modalOn: ""
      }
  },
  computed:{
    ...mapState({
      isLogin : state => state.member.isLogin,
    })
  },
  methods: {
    openModal () {
      this.modalOn = "one"    
    },
    closeModal(){
      this.modalOn= "one out"
    },

    moveHome(){
      this.$router.push({path : '/home'})
    }
  },
  mounted(){
  },
}
</script>
<style scoped>
.Home{
  overflow: hidden;
}

.drawsvg,svg {width: 100%; height: 350px}
.stroke {
  stroke: #000;
  stroke-width: 1px;
  stroke-dasharray: 0 250;
  stroke-opacity: 1; fill: none;
  -webkit-animation: stroke_offset 8s infinite;
  animation: stroke_offset 8s infinite;
  -webkit-animation-timing-function: cubic-bezier(.25, .46, .45, .94);
  animation-timing-function: cubic-bezier(.25, .46, .45, .94)
  }
  @-webkit-keyframes stroke_offset {
    100%, 25% {stroke-dasharray: 0 250; stroke-opacity: 1 }
    50%, 75% {stroke-dasharray: 250 0; stroke-opacity: .75 }
    55%, 70% {stroke-dasharray: 250 0; stroke-opacity: 0 }
  } @keyframes stroke_offset
  { 100%, 25% {stroke-dasharray: 0 250; stroke-opacity: 1 }
  50%, 75% {stroke-dasharray: 250 0; stroke-opacity: .75 }
  55%, 70% {stroke-dasharray: 250 0; stroke-opacity: 0 } }
  .fill { fill: #000; fill-opacity: 0;
  -webkit-animation: fill_offset 8s infinite;
  animation: fill_offset 8s infinite;
  -webkit-animation-timing-function: cubic-bezier(.25, .46, .45, .94);
  animation-timing-function: cubic-bezier(.25, .46, .45, .94) }
  @-webkit-keyframes fill_offset {
    100%, 25%, 35%, 90% { fill-opacity: 0 }
    50%, 70% { fill-opacity: 1 }
  }
  @keyframes fill_offset {
    100%, 25%, 35%, 90% { fill-opacity: 0 }
    50%, 70% { fill-opacity: 1 }
  } #fade-text {
    font-family: 'Alex Brush', cursive;
    font-size: 4em;
  }

  @import url(https://fonts.googleapis.com/css?family=Alex+Brush);

  button{
  background:#5f6099;
  color:#fff;
  border:none;
  position:relative;
  height:60px;
  font-size:1.6em;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
  border-radius: 10px;
  margin-top: 5em;
}
button:hover{
  background:#fff;
  color:#5f6099;
}
button:before,button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #5f6099;
  transition:400ms ease all;
}
button:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
button:hover:before,button:hover:after{
  width:100%;
  transition:800ms ease all;
}

</style>
