<template>
    <div :class="this.modalClass" id="modal-container">
      <div class="modal-background" >
        <div class ="modal" v-click-outside="closeModal">
        <button @click="kakaoLogin"><img src="../assets/kakao_login.png" alt=""></button>
        <!-- <a class='example7' href="http://google.com">Outside Webpage (Iframe)</a> -->
        </div>
      </div>
    </div>
</template>
<script>
export default {
  data(){
    return{
      active : false,
      check  : 0
      // modalClass : "",
    }
  },
  computed:{
    modalClass(){
      return this.modalOn
    }
  },
  props: {
    modalOn: String,
  },
  methods: {
    kakaoLogin () {
      window.location.href = 'https://kauth.kakao.com/oauth/authorize?client_id=f67bfd6f7cdb600868d04a4e1408199f&response_type=code&redirect_uri=http://localhost:3000/login'
      // window.location.href = 'https://kauth.kakao.com/oauth/authorize?client_id=f67bfd6f7cdb600868d04a4e1408199f&response_type=code&redirect_uri=http://j3c201.p.ssafy.io:80/login/'
    },
    closeModal(){
      if(this.check != 0){
        if(this.modalOn == "one"){
          this.$emit('closeModal');
          this.check = 0;
        }
      }else{
        if(this.modalOn == "one")
        this.check =1;
      }
    }
  },
  mounted(){
    this.active=false;
  }
}
</script>
<style scoped>
#modal-container {
    position: fixed;
    left: 0;
    top: 0;
    display: table;
    height: 100%;
    width: 100%;
    z-index: 1;
    /* hidden 처리 */
    transform: scale(0);
}

#modal-container .modal-background {
    display: table-cell;
    background: rgba(0, 0, 0, .8);
    text-align: center;
    vertical-align: middle;
}

#modal-container .modal-background .modal {
    background: #fff;
    padding: 50px;
    display: inline-block;
    border-radius: 3px;
    position: relative;
}

#modal-container .modal-background .modal h2 {
    font-size: 25px;
    line-height: 25px;
    margin-bottom: 15px;
}

#modal-container .modal-background .modal p {
    font-size: 18px;
    line-height: 22px;
}

/* * ------------------------------ * Unfolding Animation * ------------------------------ */
#modal-container.one {
    /*transform: scaleX(0) scaleY(1);*/
    animation: unfoldIn 1s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}

#modal-container.one .modal-background .modal {
    /* 초기 display: none 처럼 크기를 0 으로 하여 보여주지 않음*/
    transform: scale(0);
    /* 0.5 초 동안 애니메이션을 실행, 단 0.8초 지연시켜 0.8초 후에 애니메이션을 실행 */
    animation: zoomIn .5s .8s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}

#modal-container.one.out {
    transform: scale(1);
    animation: unfoldOut 1s .3s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}

#modal-container.one.out .modal-background .modal {
    animation: zoomOut 0.5s cubic-bezier(0.165, 0.84, 0.44, 1) forwards;
}

/* Dimmed showing */
@keyframes unfoldIn {
    0% {
        transform: scaleX(0) scaleY(0.005);
    }

    50% {
        /* 0.5 초간 세로 크기는 작은상태로 가로 너비만 확대 */
        transform: scaleX(1) scaleY(0.005);
    }

    100% {
        /* 0.5 초간 세로 크기를 확대 */
        transform: scaleY(1) scaleX(1);
    }
}

/* Show modal content */
@keyframes zoomIn {
    from {
        transform: scale(0);
    }

    to {
        transform: scale(1);
    }
}

/* Hide modal content */
@keyframes zoomOut {
    from {
        transform: scale(1);
    }

    to {
        transform: scale(0);
    }
}

/* Dimmed hiding */
@keyframes unfoldOut {
    0% {
        transform: scale(1)
    }

    50% {
        transform: scaleX(1) scaleY(0.005);
    }

    100% {
        transform: scaleX(0) scaleY(0.005);
    }
}

button{
    border: none;
    padding : 0;
}
</style>
