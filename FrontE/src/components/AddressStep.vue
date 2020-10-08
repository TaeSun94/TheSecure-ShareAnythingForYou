<template>
        <v-card
              class="mb-6 mt-6"
              color="white"
              height="inherit"
              style="text-align:left; padding : 2em;"
            >
            <v-sheet>
              <h3>숙소의 위치를 등록해주세요</h3><br><br>
              <vue-daum-postcode
              v-if="this.address.sido === '' || this.address.sido === undefined || this.address.sido === null"
              :max-suggest-items=max_items 
              @complete="complete"
              />
              <v-form
                v-else
                ref="form"
                v-model="valid"
                :lazy-validation="lazy"
              >
                <v-row style="margin : 0">
                <v-text-field style="margin-right : 2em;"
                  v-model="address.sido"
                  label="시/도"
                  :disabled = read
                  required
                ></v-text-field>
                <v-text-field
                  v-model="address.sigungu"
                  label="시/군/구"
                  :disabled = read
                  required
                ></v-text-field>
                </v-row>
                <v-text-field
                  v-model="address.jibunAddress"
                  label="지번 주소"
                  :disabled = read
                  required
                ></v-text-field>
                <v-text-field
                  v-model="address.roadAddress"
                  label="도로명 주소"
                  :disabled = read
                  required
                ></v-text-field>
                <v-text-field
                  v-model="address.detail"
                  label="상세주소를 입력해주세요"
                  required
                  :rules="[v => !!v || '상세주소는 빈칸일 수 없습니다.']"
                ></v-text-field>

                <v-btn
                  color="error"
                  class="mr-4"
                  @click="reset"
                >
                  초기화
                </v-btn>
              </v-form>
            </v-sheet>
        </v-card>
</template>

<script>
import Vue from "vue"
import {mapState,mapMutations} from 'vuex'
import VueDaumPostcode from "vue-daum-postcode"
Vue.use(VueDaumPostcode)

export default {
  data(){
    return{
      valid : false,
      read : true,
      max_items: 10,
      lazy: false,
      detail: '',
      // result: {address:'',},
    }
  },
  computed:{
    ...mapState({
      address : state => state.house.houseData,
      formValid : state => state.formValid
    }),
  },
  watch:{
    valid(val){
      this.setFormValid(this.valid)
    },
  },
  methods:{
    ...mapMutations('house',['setHouseData','initHouseData']),
    ...mapMutations(['setFormValid']),
    complete(data){
      this.setHouseData(data)
    },
    reset () {
      this.initHouseData()
      this.detail = ''
      // this.$refs.form.reset()
    },
  },
  mounted(){
  }
}
</script>

<style>

</style>