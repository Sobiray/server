package com.opensoul.sobiray.server.utils;

import com.opensoul.sobiray.server.contract.api.Sobiray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;


public class ContractAPI {
    private static final Logger log = LoggerFactory.getLogger(ContractAPI.class);

    public Web3j getWeb3j() {
        Web3j web3j = null;
        try {
            web3j = Web3j.build(new HttpService(
                    "https://rinkeby.infura.io/FMT"));  // FIXME: Enter your Infura token here;
            log.info("Connected to Ethereum client version: "
                    + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return web3j;
    }


    public Credentials getCredentials() {
        Credentials credentials = Credentials.create(System.getProperty("privateKey", System.getenv("privateKey")));
        return credentials;
    }

    public Sobiray getContract() {
        Sobiray contract = null;
        try {
            log.info("Trying to load contract {}", System.getProperty("contractAddress", System.getenv("contractAddress")));
            contract = Sobiray.load(System.getProperty("contractAddress", System.getenv("contractAddress")),
                    getWeb3j(), getCredentials(),
                    GAS_PRICE, GAS_LIMIT);
            log.info("Contract is loaded");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return contract;
    }

    public Sobiray createContract() {
        Sobiray contract = null;
        try {
            contract = Sobiray.deploy(getWeb3j(), getCredentials(), GAS_PRICE, GAS_LIMIT).send();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        String contractAddress = contract.getContractAddress();
        log.info("Smart contract deployed to address " + contractAddress);
        log.info("View contract at https://rinkeby.etherscan.io/address/" + contractAddress);
        return contract;
    }
}
