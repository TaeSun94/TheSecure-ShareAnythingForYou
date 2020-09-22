<template>
    <div class = "Sharing_Steps">
    <v-stepper
      v-model="e1"
      :vertical="vertical"
      :alt-labels="altLabels"
    >
      <template>
          <!-- 스텝 윗부분 표시  -->
        <v-stepper-header>
          <template v-for="n in steps">
            <v-stepper-step
              :key="`${n}-step`"
              :complete="e1 > n"
              :step="n"
              :editable="editable"
            >
             {{steps_subtitle[n-1]}} 
            </v-stepper-step>

            <v-divider
              v-if="n !== steps"
              :key="n"
            ></v-divider>
          </template>
        </v-stepper-header>

        <v-stepper-items>
          <v-stepper-content
            v-for="n in steps"
            :key="`${n}-content`"
            :step="n"
          >
          <div class="page1" v-if="e1 == 1">
            <v-card
              class="mb-6 mt-6"
              color="white"
              height="inherit"
              style="text-align:left; padding : 2em;"
            >
            <v-sheet>
            <h3>회원님 거주 지역의 법규와 세금</h3><br><br>
            <p>
                에어비앤비의 차별 금지 정책과 현지 법을 숙지하세요.
                잠시 시간을 내어 숙소에 적용되는 현지 법규를 확인하세요.<br> 성공적인 출발을 위해선 모든 준비를 완벽하게 해야 하니까요.<br><br>
                대부분의 도시에는 홈셰어링 관련 규정이 있으며, 구획, 건축, 인허가, 조세 등 다양한 법에 관련 법령과 조례가 담겨 있습니다.
                대부분의 도시에서 숙소를 에어비앤비에 등록하거나 호스팅을 시작하기 전에 등록, 허가 또는 면허를 취득해야 하며,
                호스트가 특정 세금을 수금하여 납부해야 하는 경우도 있습니다.
                일부 도시에서는 단기 임대를 완전히 금지하기도 합니다.<br><br>
                숙소 등록 또는 예약 결정에 대한 책임은 사용자에게 있으므로,
                SSAFY의 숙소를 등록하기 전에 관련 규정을 숙지하셔야 합니다.
                '지역별 법규'에서 유용한 자료를 확인하실 수 있습니다.<br><br>
                <a>책임감 있는 호스팅에 대해 자세히 알아보기</a><br><br>
            </p>
            </v-sheet>
            </v-card>

            <v-checkbox
            style="padding : 1em; text-align : center;" 
            v-model="agreement" 
            label="SSAFY 서비스 약관에 동의하고 숙소를 등록하는 것은 관련 법규를 준수하겠습니다.">
            </v-checkbox>
            </div>
            <div v-else-if = "e1 === 2">
                <sharing-step-2/>
            </div>

            <v-btn
              v-if="e1 > 1"
              color="secondary"
              @click="prevStep(n)"
            >
              &lt; 뒤로
            </v-btn>
            <v-btn
              v-if="e1 < steps"
              color="primary"
              @click="nextStep(n)"
            >
              다음
            </v-btn>
            <v-btn
              v-else
              color="success"
              @click="save"
            >
              완료
            </v-btn>
            <v-btn text @click="closeModal">Cancel</v-btn>
          </v-stepper-content>
        </v-stepper-items>
      </template>
    </v-stepper>
  </div>
</template>
<script>
import {mapState,mapMutations,mapActions} from 'vuex'
import SharingStep2 from './SharingStep2.vue'
    export default {
        data () {
            return {
                e1: 1,
                steps: 5,
                steps_subtitle : [ '약관 동의하기','위치 등록하기','사진 추가하기','날짜 정하기','옵션및 가격 정하기'],
                vertical: false,
                altLabels: false,
                editable: false,
                agreement: false,
                houseData : [],
            }
        },
        components:{
            SharingStep2,
        },
        computed:{
          ...mapState({
            formValid : state => state.formValid
          })
        },
        watch: {
          steps (val) {
              if (this.e1 > val) {
              this.e1 = val
              }
          },
        },

        methods: {
          ...mapMutations('house',['setHouseData','initHouseData']),
          
          onInput (val) {
              this.steps = parseInt(val)
          },
          nextStep (n) {
            switch(n){
              case 1 : 
                if(this.agreement == true){
                    if (n === this.steps) {
                        this.e1 = n
                    } else {
                        this.e1 = n + 1
                    }
                }else{
                    alert("동의해야함")
                }
                break
              case 2 :
                if(this.formValid == true){
                  this.e1 = n + 1
                }else{
                  alert("상세주소를 입력해야합니다.")
                } 
            }
          },
          prevStep (n) {
              if( n === 1){
                  this.e1 = 1
              } else {
                  this.e1 = n - 1
              }
          },
          save(){
              alert("완료되었습니다.")
              this.closeModal()
              // setTimeout(function(){this.e1 = 1, this.agreement = false,alert("init"+this.e1)}, 500)
          },
          closeModal(){
              this.$emit('closeModal')
              this.initHouseData()
              this.e1 = 1,
              this.agreement = false
          },
        },
        created(){
          console.log('test')
        },
    }
</script>
<style scoped>

</style>