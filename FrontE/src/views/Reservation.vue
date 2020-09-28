<template>
    <div id="reservation">
        <v-container fluid style="padding : 0">
            <v-carousel
                cycle
                height="35vh"
                hide-delimiter-background
                hide-delimiters
                interval="3000"
                :show-arrows="showArrow"
            >
                <v-carousel-item
                v-for="(slide, i) in slides"
                :key="i"
                >
                <v-sheet
                    :color="colors[i]"
                    height="95vh"
                >
                    <v-row
                    class="fill-height"
                    align="center"
                    justify="center"
                    >
                    <!-- 페이지 별 내용 입력 -->
                    <!-- <div class="display-3">{{ slide }}</div>  -->
                    <div class="display-3">{{ slide }}</div> 
                    </v-row>
                </v-sheet>
                </v-carousel-item>
            </v-carousel>
            <v-container id=""></v-container>
        </v-container>
    </div>
</template>
<script>
export default {
    data(){
        return{
            vartical : false,
            showArrow : false,
            colors: [
            'indigo',
            'warning',
            'pink darken-2',
            'red lighten-1',
            'deep-purple accent-4',
            ],
            slides: [
            'Introduction',
            'Reservation',
            'Home_Sharing',
            'Leave a Review',
            'FAQ',
            ],
        }
    }
}
</script>
<style scoped>
#reservation{
    background-color: white;
    height: 100vh;
    padding : 0
}
</style>