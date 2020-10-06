<template>
    <div class="FAQ">
        <v-container>
            <v-flex class="host_contents" style="height:60vh;">
                    <p>자주하는 질문(FAQ) - 호스트 </p>
                    <v-sheet elevation="10" >
                        <v-row justify="center">
                            <v-expansion-panels popout
                            focusable
                            hover>
                            <v-expansion-panel
                                v-for="(item,i) in 5"
                                :key="i"
                            >
                                <v-expansion-panel-header>Item</v-expansion-panel-header>
                                <v-expansion-panel-content>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                </v-expansion-panel-content>
                            </v-expansion-panel>
                            </v-expansion-panels>
                        </v-row>
                    </v-sheet>
                </v-flex>
        </v-container>
        <v-divider></v-divider>
        <v-container style="margin-top:2em;">
            <v-flex class="host_contents" style="height:60vh;">
                    <p>자주하는 질문(FAQ) - 예약 </p>
                    <v-sheet elevation="10">
                        <v-row justify="center">
                            <v-expansion-panels popout
                            focusable
                            hover>
                            <v-expansion-panel
                                v-for="(item,i) in 5"
                                :key="i"
                            >
                                <v-expansion-panel-header>Item</v-expansion-panel-header>
                                <v-expansion-panel-content>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                </v-expansion-panel-content>
                            </v-expansion-panel>
                            </v-expansion-panels>
                        </v-row>
                    </v-sheet>
                </v-flex>
        </v-container>  
    </div>
</template>
<script>
export default {
    
}
</script>
<style scoped>
.FAQ{
    background-color: white;
    height: 100vh;
    padding : 0
}
</style>