import web3  from '../web3';
import etherConfig from './ethereumConfig';

var membershipEthereum = {};

membershipEthereum.createAccount = async function(_password){
    var account = await web3.eth.personal.newAccount(_password);
    var funcData = web3.eth.abi.encodeParameter('string','가입을 축하드립니다.');
    web3.eth.personal.unlockAccount(etherConfig.coinbase.address,etherConfig.coinbase.password);
    web3.eth.personal.sendTransaction({
        from: etherConfig.coinbase.address,
        gasPrice: 50000,
        gas: 50000,
        to: account,
        value: 30000000,
        data: funcData
    },etherConfig.coinbase.password);
    return account;
}

membershipEthereum.getTransactions = async function(_txList){
    var transactions = new Array;

    for(var i in _txList){
        var txHash = _txList[i];
        var receipt = await web3.eth.getTransaction(txHash);
        var data = {
            "txHash" : txHash,
            "data" : web3.eth.abi.decodeParameter('string', receipt.input)
        }
        transactions.push(data);
    }

    return transactions;
}

export default membershipEthereum;