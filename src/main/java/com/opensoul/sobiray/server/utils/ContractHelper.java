package com.opensoul.sobiray.server.utils;

import com.opensoul.sobiray.server.contract.api.Sobiray;
import com.opensoul.sobiray.server.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.ArrayList;
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
//        try {
////            TransactionReceipt transactionReceipt = contract.getEvent().send();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
        return null;
    }

    public Event getEventById(String eventId) {
        try {
            return new Event(contract.getEvent(eventId).send());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try {
            BigInteger eventsLength = contract.getEventsLength().send();
            if (eventsLength != null && eventsLength.compareTo(BigInteger.ZERO) > 0) {
                for (int i = 0; i < eventsLength.intValue(); i++) {
                    events.add(new Event(contract.getEventByNum(BigInteger.valueOf(i)).send()));
                }
            }
            return events;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean createNewPayment(String eventId, String userId, String transactionId) {
        try {
            TransactionReceipt transactionReceipt = contract.addGuest(eventId, userId, transactionId).send();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean createNewEvent(String eventId, BigInteger successSum, BigInteger maxGuestsCount,
                                  BigInteger presalePrice, BigInteger salePrice, BigInteger fundingDeadline, BigInteger eventDate) {
        try {
            TransactionReceipt transactionReceipt = contract.addEvent(eventId, successSum, maxGuestsCount,
                    presalePrice, salePrice, fundingDeadline, eventDate).send();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
