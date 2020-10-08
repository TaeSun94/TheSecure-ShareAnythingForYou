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
                                v-for="(item,i) in items_sharing"
                                :key="i"
                            >
                                <v-expansion-panel-header>{{item.subject}}</v-expansion-panel-header>
                                <v-expansion-panel-content>
                                {{item.text}}
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
                                v-for="(item,i) in items_reservations"
                                :key="i"
                            >
                                <v-expansion-panel-header>{{item.subject}}</v-expansion-panel-header>
                                <v-expansion-panel-content>
                                {{item.text}}
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
    data(){
        return{
            items_reservations:[
                {subject: "예약 확인은 어떻게 하나요?", text:"완료된 예약의 경우 왼쪽 사이드바 상단의 프로필 사진을 클릭하면 My page로 넘어가 확인하실 수 있습니다."},
                {subject: "게스트가 예약에 대하여 믿고 쓸 수 있을까요?", text:"예약의 경우 블록에 저장하는 데이터를 통하여 사용자들에게 신뢰를 주고 모두가 확인할 수 있어서 투명성을 보장합니다."},
                {subject: "블록체인에 트랜잭션을 호출하기 위하여 일정량의 암호 화폐가 필요한데 충전은 어떻게 하나요?", text:"저희 서비스는 사설 이더리움 네트워크를 사용하기 때문에 수수료없이 트랜잭션을 발행할 수 있고 그로 인해 충전의 기능은 따로 제공하지 않습니다. 대신 Account 생성을 할 때 서비스 이용을 위한 소정의 Ether를 전송하여 불편함없이 이용하실 수 있습니다."},
            ],
            items_sharing:[
                {subject: "SSAFY 호스트가 되기 위한 조건이 있나요?", text:"대부분의 지역에서 쉽게 SSAFY 호스트가 될 수 있고, 숙소는 언제든지 무료로 등록할 수 있습니다. SSAFY를 통해 아파트와 집 전체, 개인실, 트리하우스, 성 등 다양한 숙소를 공유할 수 있습니다."},
                {subject: "재산 피해에 대비한 보호 장치에는 어떤 것이 있나요?", text:"호스트 보호 프로그램은 현금, 증권, 수집품, 희귀 예술작품, 보석, 반려동물 또는 대인 피해에 대한 책임 배상에는 적용되지 않습니다. 따라서 숙소를 호스팅하기에 앞서 귀중품은 안전하게 보관하거나 치우실 것을 권장합니다. 또한, 이 프로그램은 마모로 인한 재산상의 손실이나 피해를 보상하지 않습니다."},
                {subject: "숙소 등록 비용은 얼마인가요?", text:"회원 가입과 숙소 등록은 무료이지만, 예약을 받은 호스트에게는 일반적으로 일정량 서비스 수수료가 부과되어, SSAFY 운영 비용을 충당하는 데 사용됩니다."},
            ]
        }
    }
}
</script>
<style scoped>
.FAQ{
    background-color: white;
    height: 100vh;
    padding : 0
}
</style>