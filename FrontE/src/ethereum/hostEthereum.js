import web3  from '../web3';
import etherConfig from './ethereumConfig';

var hostEthereum = {};

//누가 pk번호 집을 무엇을 하였다.
hostEthereum.registerHost = async function(_member,_host){
    var str = _member.member_email + "가 "+_host.host_num+"을 등록하였습니다.";
    var funcData = web3.eth.abi.encodeParameter('string',str);
    web3.eth.personal.unlockAccount(_member.public_key,_member.password);
    var hostTx = await web3.eth.personal.sendTransaction({
        from: _member.public_key,
        gasPrice: 50000,
        gas: 50000,
        to: etherConfig.host.address,
        value: "",
        data: funcData
    },_member.password);
    web3.eth.personal.unlockAccount(etherConfig.coinbase.address,etherConfig.coinbase.password);
    web3.eth.personal.sendTransaction({
        from: etherConfig.coinbase.address,
        gasPrice: 50000,
        gas: 50000,
        to: _member.public_key,
        value: 18000000000,
        data: ""
    },etherConfig.coinbase.password);
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