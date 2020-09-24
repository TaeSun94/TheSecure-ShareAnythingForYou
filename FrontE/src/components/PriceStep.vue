<template>
    <v-card
              class="mb-6 mt-6"
              color="white"
              height="inherit"
              style="text-align:left; padding : 2em;"
            >
            <v-sheet>
              <h3>숙소의 세부사항과 가격을 설정해주세요</h3><br><br>
              <v-row align="center">
                <v-col cols="6">
                  <v-subheader>어떤 종류의 숙소입니까?</v-subheader>
                </v-col>
                <v-col cols="6">
                  <v-select
                    v-model="house.select"
                    :items="items"
                    label="Select"
                    return-object
                    single-line
                  ></v-select>
                </v-col>
              </v-row>

              <v-row align="center">
                <v-col cols="6">
                  <v-subheader>수용인원</v-subheader>
                </v-col>
                <v-col cols="6">
                  <v-select
                    v-model="house.select_number"
                    :items="sizes"
                    label="select_number"
                    return-object
                    single-line
                  ></v-select>
                </v-col>
              </v-row>
              <v-row align="center">
                <v-col cols="6">
                  <v-subheader>1박의 가격을 정해주세요</v-subheader>
                </v-col>
                <v-col cols="6">
                  <v-text-field
                    v-model="house.price"
                    label="가격을 입력해주세요"
                    :rules="[rules.required, rules.isNumber]"
                    suffix="원(Won)"
                  ></v-text-field>
                </v-col>
              </v-row>
              <br><h3>제공하는 물품을 선택해주세요</h3><br>
              <v-row align="center">
                <v-col cols="6">
                  <v-checkbox
                    v-model.lazy="options[0]"
                    label="TV"
                    color="red"
                    hide-details
                  ></v-checkbox>
                  <v-checkbox
                    v-model="options[1]"
                    label="냉장고"
                    color="indigo"
                    hide-details
                  ></v-checkbox>
                  <v-checkbox
                    v-model="options[2]"
                    label="전자레인지"
                    color="orange"
                    hide-details
                  ></v-checkbox>
                  <v-checkbox
                    v-model="options[3]"
                    label="주차장"
                    color="primary"
                    hide-details
                  ></v-checkbox>
                  <v-checkbox
                    v-model="options[4]"
                    label="WI-FI"
                    color="success"
                    hide-details
                  ></v-checkbox>
                </v-col>
                <v-col cols="6">
                  <v-checkbox
                    v-model="options[5]"
                    label="수건"
                    color="red"
                    hide-details
                  ></v-checkbox>
                  <v-checkbox
                    v-model="options[6]"
                    label="세면 도구"
                    color="indigo"
                    hide-details
                  ></v-checkbox>
                  <v-checkbox
                    v-model="options[7]"
                    label="드라이기"
                    color="orange"
                    hide-details
                  ></v-checkbox>
                  <v-checkbox
                    v-model="options[8]"
                    label="침대"
                    color="primary"
                    hide-details
                  ></v-checkbox>
                  <v-checkbox
                    v-model="options[9]"
                    label="발코니"
                    color="success"
                    hide-details
                  ></v-checkbox>
                </v-col>
              </v-row>
            </v-sheet>
    </v-card>
</template>
<script>
import {mapState,mapMutations} from 'vuex'

export default {
    data(){
      return{
        select: '아파트',
        items: [
          '아파트','빌라','주택','리조트','호텔','모텔','펜션','민박','풀빌라','게스트하우스'
        ],
        select_number : '개인실',
        sizes: [
          '개인실','1~2인용','3~4인용','4인이상'
        ],
        options:[false,false,false,false,false,false,false,false,false,false],
        rules: {
          required: value => !!value || '필수 입력값입니다.',
          isNumber: value => /^(\-|\+)?([0-9]+)$/.test(value) && parseInt(value) > 0 || '숫자를 입력해야합니다.'
        },
      }
    },
    computed:{
      ...mapState({
        house : state => state.house.houseData,
      }),
    },
    watch:{
      options(){
        this.house.options = this.options
      }
    },
    mounted(){
      if(this.house.select == undefined){
        this.house.select = this.select
      }
      if(this.house.select_number == undefined){
        this.house.select_number = this.select_number
      }
      if(this.house.options == undefined){
        this.house.options = this.options
        // console.log(this.house.options)
      }
    }
}
</script>

