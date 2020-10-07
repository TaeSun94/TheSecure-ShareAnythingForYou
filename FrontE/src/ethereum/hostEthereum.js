import web3  from '../web3';
import etherConfig from './ethereumConfig';

var hostEthereum = {};

//누가 pk번호 집을 무엇을 하였다.
hostEthereum.registerHost = async function(_member,_host){
    var str = _member.email + "가 "+_host.num+"을 등록하였습니다.";
    var funcData = web3.eth.abi.encodeParameter('string',str);
    web3.eth.personal.unlockAccount(_member.account,_member.password);
    var hostTx = await web3.eth.personal.sendTransaction({
        from: _member.account,
        gasPrice: 50000,
        gas: 50000,
        to: etherConfig.host.address,
        value: 0,
        data: funcData
    },_member.password);

    return hostTx;
}

hostEthereum.modifyHost = async function(_member,_host){
    var str = _member.email + "가 "+_host.num+"을 수정하였습니다.";
    var funcData = web3.eth.abi.encodeParameter('string',str);
    web3.eth.personal.unlockAccount(_member.account,_member.password);
    var hostTx = await web3.eth.personal.sendTransaction({
        from: _member.account,
        gasPrice: 50000,
        gas: 50000,
        to: etherConfig.host.address,
        value: 0,
        data: funcData
    },_member.password);

    return hostTx;
}

hostEthereum.deleteHost = async function(_member,_host){
    var str = _member.email + "가 "+_host.num+"을 삭제하였습니다.";
    var funcData = web3.eth.abi.encodeParameter('string',str);
    web3.eth.personal.unlockAccount(_member.account,_member.password);
    var hostTx = await web3.eth.personal.sendTransaction({
        from: _member.account,
        gasPrice: 50000,
        gas: 50000,
        to: etherConfig.host.address,
        value: 0,
        data: funcData
    },_member.password);

    return hostTx;
}

export default hostEthereum;