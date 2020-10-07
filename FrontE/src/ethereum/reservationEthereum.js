import web3  from '../web3';
import etherConfig from './ethereumConfig';

var reservationEthereum = {};

//누가 pk번호 집을 무엇을 하였다.
reservationEthereum.registerReservation = async function(_member,_host){
    var str = _member.email + "가 "+_host.num+"을 예약하였습니다.";
    var funcData = web3.eth.abi.encodeParameter('string',str);
    web3.eth.personal.unlockAccount(_member.account,_member.password);
    var hostTx = await web3.eth.personal.sendTransaction({
        from: _member.account,
        gasPrice: 50000,
        gas: 50000,
        to: etherConfig.reservation.address,
        value: 0,
        data: funcData
    },_member.password);

    return hostTx;
}

// reservationEthereum.modifyHost = function(_member,_host){
//     var str = _member.email + "가 "+_host.num+"을 수정하였습니다.";
//     var funcData = web3.eth.abi.encodeParameter('string',str);
//     web3.eth.personal.unlockAccount(_member.account,_member.password);
//     var hostTx = await web3.eth.personal.sendTransaction({
//         from: _member.account,
//         gasPrice: 50000,
//         gas: 50000,
//         to: etherConfig.host.address,
//         value: 0,
//         data: funcData
//     },_member.password);

//     return hostTx;
// }

// reservationEthereum.deleteHost = function(_member,_host){
//     var str = _member.email + "가 "+_host.num+"을 삭제하였습니다.";
//     var funcData = web3.eth.abi.encodeParameter('string',str);
//     web3.eth.personal.unlockAccount(_member.account,_member.password);
//     var hostTx = await web3.eth.personal.sendTransaction({
//         from: _member.account,
//         gasPrice: 50000,
//         gas: 50000,
//         to: etherConfig.host.address,
//         value: 0,
//         data: funcData
//     },_member.password);

//     return hostTx;
// }

export default reservationEthereum;