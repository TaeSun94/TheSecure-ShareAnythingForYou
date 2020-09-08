<template>
    <div>
        <button @click="kakaoLogin"><img src="../assets/kakao_login.png" alt=""></button>
    </div>
</template>
<script>
export default {
  methods: {
    kakaoLogin () {
      window.location.href = 'https://kauth.kakao.com/oauth/authorize?client_id=f67bfd6f7cdb600868d04a4e1408199f&redirect_uri=http://localhost:3000/login&response_type=code'
    }
  }
}
</script>
<style scoped>
button{
    border: none;
    padding : 0;
}
</style>
