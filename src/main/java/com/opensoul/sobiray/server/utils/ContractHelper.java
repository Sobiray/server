package com.opensoul.sobiray.server.utils;

import com.opensoul.sobiray.server.contract.api.Sobiray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public class ContractHelper {
    private static final Logger log = LoggerFactory.getLogger(ContractHelper.class);

    private final ContractAPI contractAPI;
    private final Sobiray contract;

    public ContractHelper() {
        contractAPI = new ContractAPI();
        contract = contractAPI.getContract();
        log.info("Contract {} is loaded", contract.getContractAddress());
    }

    public List<String> getEventList() {
        return null;
    }

    public boolean createNewPayment(String from, String to, BigDecimal amount) {
        return false;
    }

    public boolean createNewEvent(String eventId, BigInteger successSum, BigInteger maxGuestsCount,
                                  BigInteger presalePrice, BigInteger salePrice, BigInteger fundingDeadline, BigInteger eventDate) {
        try {
            TransactionReceipt transactionReceipt = contract.addEvent(eventId, successSum, maxGuestsCount,
                    presalePrice, salePrice, fundingDeadline, eventDate).send();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
