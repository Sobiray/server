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


    public Event getEventById(String eventId) {
        try {
            return new Event(contract.getEvent(eventId).send(), getEventGuests(eventId));
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
                    Event e = new Event(contract.getEventByNum(BigInteger.valueOf(i)).send());
                    e.setGuests(getEventGuests(e.getEventId()));
                    events.add(e);
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

    private List<String> getEventGuests(String eventId) {
        List<String> eventGuests = new ArrayList<>();
        try {
            int n = contract.getEventGuestsLength(eventId).send().intValue();
            for (int i = 0; i < n; i++)
                eventGuests.add(contract.getEventGuestsIds(eventId, BigInteger.valueOf(i)).send());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return eventGuests;
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
