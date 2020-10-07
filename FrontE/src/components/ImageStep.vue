<template>
    <v-card
              class="mb-6 mt-6"
              color="white"
              height="inherit"
              style="padding : 2em;"
            >
            <v-sheet>
              <h3 style="text-align:left;">숙소의 사진을 등록해주세요</h3><br><br>
              
              <v-img
                  v-if="imageUrl" :src="imageUrl"
                  max-height = "30vh" max-width = "30vh"
                  style="margin: 4em auto;"
              ></v-img>
              <!-- <input ref="imageInput" type="file" hidden @change="onChangeImages">
              <v-btn type="button" @click="onClickImageUpload">이미지 업로드</v-btn> -->
              <v-file-input
                label="File input"
                filled
                prepend-icon="mdi-camera"
                @change="onChangeImages"
              ></v-file-input>
            </v-sheet>
    </v-card>
</template>
<script>
import {mapState,mapActions} from 'vuex'

export default {
    data() {
        return {
            imageUrl: '',
            img : [],
        }
    },
    computed:{
      ...mapState({
        house : state => state.house.houseData,
      }),
    },
    methods: {
        onClickImageUpload() {
            this.$refs.imageInput.click();
        },
        onChangeImages(e) {
            // console.log(e.target.files)
            // const file = e.target.files[0];
            if(e == undefined){
              this.imageUrl = ''
              this.house.imageUrl = ''
            }else{
              this.imageUrl = URL.createObjectURL(e);
              this.house.imageUrl = this.imageUrl;
              // 기존에 파일이있엇을 경우에 일단 1장이므로 이렇게 처리
              if(this.img.length !== 0){
                console.log("this.file.length !== 0")
                this.img.pop()
                this.img.push(e)
                this.house.img = this.img
              }else{
                console.log("else")
                this.img.push(e)
                this.house.img = this.img
              }
            }
            console.log(this.house.img)
        },
        // click: function(){
        //   alert("클릭!")
        // }
    },
    mounted(){
      this.imageUrl = this.house.imageUrl
    }
}
</script>

