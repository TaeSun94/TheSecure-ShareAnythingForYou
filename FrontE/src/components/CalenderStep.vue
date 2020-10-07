<template>
    <v-card
              class="mb-6 mt-6"
              color="white"
              height="inherit"
              style="text-align:left; padding : 2em;"
            >
            <v-sheet>
              <h3>숙소제공이 가능한 날짜를 선택하세요</h3><br><br>
                <v-row>
                  <v-col cols="12" sm="6">
                    <v-date-picker
                      v-model="dates"
                      multiple
                      :show-current="showCurrent"
                    ></v-date-picker>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <template>
                        <v-combobox
                          v-model="dates"
                          multiple
                          chips
                          clearable
                          label="선택하신 날짜"
                          prepend-icon="mdi-view-dashboard"
                          readonly
                          
                        ></v-combobox>
                      </template>
                  </v-col>
                </v-row>
            </v-sheet>
    </v-card>
</template>
<script>
import {mapState,mapMutations} from 'vuex'

export default {
  data() {
    return{
      year : '',
      month : '',
      day : '',
      dates: [],
      showCurrent:true
    }
  },
  props:['bus'],
  computed : {
    ...mapState({
      houseDates : state => state.house.houseDates,
    }),
  },
  watch :{
    dates(){
      this.setHouseDates(this.dates)
    }
  },
  methods: {
    ...mapMutations('house',['setHouseDates']),
    // allowedDates: val => parseInt(val.split('-')[2], 10) % 2 === 0,
    gettomorrow(){
      var today = new Date();
      this.year = today.getFullYear();
      this.month = today.getMonth();
      this.day = today.getDate();
      // this.current = new Date(this.year,this.month,this.day)
      today.setDate(today.getDate()+1)
      var dates = new Array()
      dates[0] = today.toISOString().substr(0,10)
      // this.houseDates = dates
      this.dates = dates
    }
    
  },
  mounted(){
    if(this.houseDates.length <= 0){
      this.gettomorrow()
    }else{
      this.dates = this.houseDates
    }
  }
}
</script>

