package com.opensoul.sobiray.server.model;

import org.web3j.tuples.generated.Tuple9;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Event {
    String eventId;
    EventStatus eventStatus;
    BigInteger successSum;
    BigInteger currentSum;
    BigInteger maxGuestsCount;
    BigInteger presalePrice;
    BigInteger salePrice;
    LocalDate fundingDeadline;
    LocalDate eventDate;
    List<String> guests;

    public Event(String eventId, BigInteger successSum, BigInteger currentSum, BigInteger maxGuestsCount,
                 BigInteger presalePrice, BigInteger salePrice, BigInteger fundingDeadline, BigInteger eventDate, List<String> guests) {
        this.eventId = eventId;
        this.successSum = successSum;
        this.currentSum = currentSum;
        this.maxGuestsCount = maxGuestsCount;
        this.presalePrice = presalePrice;
        this.salePrice = salePrice;
        this.fundingDeadline = Instant.ofEpochMilli(Long.valueOf(fundingDeadline.toString())).atOffset(ZoneOffset.UTC).toLocalDate();
        this.eventDate = Instant.ofEpochMilli(Long.valueOf(eventDate.toString())).atOffset(ZoneOffset.UTC).toLocalDate();
        this.guests = guests;
    }

    public Event(Tuple9<BigInteger, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> contractRes) {
        this.eventId = contractRes.getValue2();
        this.successSum = contractRes.getValue3();
        this.currentSum = contractRes.getValue4();
        this.maxGuestsCount = contractRes.getValue5();
        this.presalePrice = contractRes.getValue6();
        this.salePrice = contractRes.getValue7();
        this.fundingDeadline = Instant.ofEpochMilli(Long.valueOf(contractRes.getValue8().toString())).atOffset(ZoneOffset.UTC).toLocalDate();
        this.eventDate = Instant.ofEpochMilli(Long.valueOf(contractRes.getValue9().toString())).atOffset(ZoneOffset.UTC).toLocalDate();
        this.eventStatus = EventStatus.byId(contractRes.getValue1().intValue());
        this.guests = new ArrayList<>();
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public String getEventId() {
        return eventId;
    }

    public BigInteger getSuccessSum() {
        return successSum;
    }

    public BigInteger getMaxGuestsCount() {
        return maxGuestsCount;
    }

    public BigInteger getPresalePrice() {
        return presalePrice;
    }

    public BigInteger getSalePrice() {
        return salePrice;
    }

    public LocalDate getFundingDeadline() {
        return fundingDeadline;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public List<String> getGuests() {
        return guests;
    }

    @Override
    public String toString() {
        return "{" +
                "    \"eventId\": \"" + eventId + "\"," +
                "    \"eventStatus\": " + eventStatus + "," +
                "    \"successSum\": " + successSum + "," +
                "    \"currentSum=\": " + currentSum + "," +
                "    \"maxGuestsCount\": " + maxGuestsCount + "," +
                "    \"presalePrice\": " + presalePrice + "," +
                "    \"salePrice\": " + salePrice + "," +
                "    \"fundingDeadline\": " + fundingDeadline + "," +
                "    \"eventDate\": " + eventDate + "," +
                "    \"guests\": [" + guests.stream().map(g -> "\"" + g + "\"").collect(Collectors.joining(", ")) + "]" +
                "}";
    }
}
