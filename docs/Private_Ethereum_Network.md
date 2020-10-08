# Private Ethereum Network

### 구축 방법

1.  ppa를 이용한 다운로드

   레포지토리 추가

   `sudo add-apt-repository -y ppa:ethereum/ethereum`

   go-ethereum 설치

   ```
   sudo apt-get update
   sudo apt-get install ethereum
   ```

2.  docker를 이용한 설치

   ```
   docker pull ethereum/client-go
   docker run -it -p 30303:30303 ethereum/client-go
   ```

   image가 가지는 port관련

   8545 : TCP- http- JSON RPC API

   8546 : TCP-Websocket-JSON RPC API

   8547 : TCP-GraphQL API

   30303: TCP,UDP -P2P network

3. 깃헙 코드를 받아서 돌리는 경우

   ```
   git clone https://github.com/ethereum/go-ethereum.git
   cd go-ethereum
   make geth
   ```

   이 때 유의할 점은 make geth를 할 때 go 버전이 일정 버전 이상인 경우만 가능함. 현재 2020-10-04/ go버전은 1.13이상을 지원

   stable version은 v1.9.21이다.



### Geth 사용

다양한 geth command line이 존재한다. geth를 잘 사용하기 위해서는 reference를 잘 활용해야한다.

[Geth reference](https://geth.ethereum.org/docs/interface/command-line-options) 에서 참고하여 필요한 용어들을 정리할 필요가 있다. 자세한 설명은 레퍼런스를 참고.



### 제네시스 블록 설정

1. 제네시스 블록이란

   블록체인 네트워크에 가장 먼저 등록되는 첫번째 블록이다. 다양한 옵션들이 존재하지만 필요에 따라 추가하거나 제거할 수 있다. 꼭 들어가야하는 요소로 config, difficulty, gasLimit, extraData, alloc이 있다.

​	**참고** [제네시스 블록관련](https://programmer-student.tistory.com/m/16)

2. 블록설정 예제

   ```
   {
           "config": {
                   "chainId": 15,
                   "homesteadBlock": 0,
                   "eip150Block": 0,
                   "eip155Block": 0,
                   "eip158Block": 0
           },
           "difficulty": "0x10",
           "gasLimit": "9999999",
           "extraData": "",
           "alloc": {
           	"D7a016eeF56913402afE2E61e5067fbDDa427E81": {"balance": "80000000"},
           	"DD8D6Da41c3199297C9AF36Fc263467baBe5F86A": {"balance": "80000000"},
           	"D79A3677C7729286393761435DaE50B74B9C1769": {"balance": "80000000"}
           }
   }
   ```

   0xD7a016eeF56913402afE2E61e5067fbDDa427E81 : Coinbase

   0xDD8D6Da41c3199297C9AF36Fc263467baBe5F86A : Host

   0xD79A3677C7729286393761435DaE50B74B9C1769 : Reservation

   

3. 새로운 account 생성

   ```
   geth --datadir (저장 경로) account new
   //예시
   geth --datadir /home/vagrant/dev/data account new
   ```

   account를 geth명령어로 실행하는 이유는 네트워크를 시작했을 때 미리 생성한 지갑에 일정량의 balance를 두기 위함.

   

### 사설 이더리움 네트워크 실행

1. 네트워크 init

   ```
   geth --datadir (데이터 보관 경로) init (genesis.json이 있는 경로)/genesis.json
   geth --datadir /home/vagrant/dev/data init /home/vagrant/dev/data/genesis.json
   ```

   init을 통하여 사설 이더리움 네트워크에 제네시스 블록을 올리므로 네트워크 구동을 시작한다.

   

2. 실행 명령어

   1.1 mining 옵션 추가

   ```
   geth --networkid 921 --maxpeers 2 --datadir /home/ubuntu/ethereum/dev/eth_localdata --port 30303 --rpc --rpcport 8545 --rpcaddr 0.0.0.0 --rpccorsdomain "*" --rpcapi "admin,net,miner,eth,rpc,web3,txpool,debug,db,personal" --rpcvhosts=* --allow-insecure-unlock --mine --miner.threads 1 console 2>> /home/ubuntu/ethereum/dev/eth_localdata/geth.log
   ```

   1.2 mining 옵션 삭제

   ```
   geth --networkid 921 --maxpeers 2 --datadir /home/ubuntu/ethereum/dev/eth_localdata --port 30303 --rpc --rpcport 8545 --rpcaddr 0.0.0.0 --rpccorsdomain "*" --rpcapi "admin,net,miner,eth,rpc,web3,txpool,debug,db,personal" --allow-insecure-unlock console 2>> /home/ubuntu/ethereum/dev/eth_localdata/geth.log
   ```



### TestNet 및 Metamask 사용

##### Ropsten을 이용하여 testnet 사용

1. Ropsten에 연동하기전 project를 생성해야한다.

   project 생성은 https://infura.io/에서 진행하고 생성후 나오는 Project Id를 이용하여 연동

2. ropsten을 연동

   ```
   var web3 = new Web3(new Web3.providers.HttpProvider(
       'https://ropsten.infura.io/v3/(project id)'
   ));
   
   //예시
   var web3 = new Web3(new Web3.providers.HttpProvider(
       'https://ropsten.infura.io/v3/db0d1eed91704284b3568547d27ef502'
   ));
   ```



##### Metamask 사용

Metamask를 사용하는 이유는 사설 이더리움 네트워크를 구축했으면 외부와 연결이 잘 되었는지 가장 쉽게 확인할 수 있기 때문이고 eth를 전송이 되는지 등을 확인할 수 있다.

1. Chrome 확장 프로그램에서 metamask 검색후 다운로드를한다.
2. 기존 account가 있다면 account를 접속 아니라면 새로 생성을 한다.
3. 생성 과정에서 나오는 단어 리스트는 개인적으로 보관하며 추후 비밀번호 재설정에서 사용한다.
4. testnet의 경우 테스트 ether를 받기 위하여 buy를 클릭한 뒤 파우셋 테스트에서 이더 얻기를 클릭하여 테스트 ether를 발급 받는다.
5. 해당 account의 개인키를 보기 위해서 account 오른쪽에 버튼을 클릭한 뒤에 개인키 내보내기로 확인이 가능하다.



### 구축 과정에서 생긴 오류들

##### rpc domain오류

1. web3를 연동할때 localhost:8545로 연동할 경우 경로를 https://localhost:8545가 아닌 http://localhost:8545로 접근하여야한다.
2. private ethereum network를 aws에 구축하여 사용할 경우 corsdomain을 설정을 해도 안되는 경우가 있는데 이때 --rpcvhosts=*를 command에 추가하여 사용

##### unknown account / Invalid address 오류

가장 힘들었던 오류인데 이는 일반 테스트넷이나 사설 네트워크냐로 나뉜다.

web3.eth.accounts.create()로 새로운 account를 만들경우 해당 account로 사설 네트워크에서 transaction과 관련된 signTransaction(),sendTransaction()을 할 때 해당 오류가 발생하였다.

이에 대한 이유를 찾아보다가 wallet()에 추가하지 않아서 사용이 불가능했던것인가 해서 `web3.eth.accounts.wallet`을 만들고 wallet에 add 두개의 account를 추가한 뒤 실행 -> 똑같은 오류 유지. 하지만 발급받은 account로 coinbase에서 송금이 가능하였다. 이것이 혼선을 주었는데 다른 방법을 찾게되었다.

transaction내부에 들어가는 데이터들을 파악하다 보니 common이라는 명칭으로 사설 네트워크에 넣기위한 필요 변수들을 추가해줬다. 

```
{
	common:{
		networkId: geth실행시 설정했던 networkId 값,
		chainId: genesis.json config에 작성한 chainId값
	}
}
```

이러한 변수를 추가하여 작성을 해보았지만 똑같은 오류.(tx 내부 data 문제가 아님)

다른 방법으로 `web3.eth.personal.newAccount('비밀번호')`를 이용하여 account를 생성한 뒤 `web3.eth.personal.sendTransaction()`을 이용하여 테스트해본 결과 정상 작동.

이를 통하여 사설 네트워크를 구축할 경우 새로운 account생성을 할 때 `web3.eth.personal.newAccount('비밀번호')`를 통하여 생성된 account는 내부 --datadir 경로에 있는 keystore파일 내부에 추가가 되는것으로 보아 사설 네트워크는 keystore내부에 들어있는 account에 관련해서만 동작을 한다는 것을 알게 되었다.

##### Transaction(tx)발생시킬때 생긴 문제

가장 먼저 tx를 생성하는 account에 gas리밋을 갖고 있는지 없는지를 확인한다. tx로 value에 wei를 전송하지 않더라도 tx에 지정한 gas양은 보유하고 있어야 tx가 만들어진다.



geth --networkid 921 --maxpeers 2 --datadir /home/ubuntu/ethereum/dev/eth_localdata --port 30303 --rpc --rpcport 8545 --rpcaddr 0.0.0.0 --rpccorsdomain "*" --rpcapi "admin,net,miner,eth,rpc,web3,txpool,debug,db,personal" --rpcvhosts=* --allow-insecure-unlock console 2>> /home/ubuntu/ethereum/dev/eth_localdata/geth.log