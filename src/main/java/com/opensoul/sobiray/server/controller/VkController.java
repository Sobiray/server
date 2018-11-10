package com.opensoul.sobiray.server.controller;

import com.opensoul.sobiray.server.model.Event;
import com.opensoul.sobiray.server.utils.ContractHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VkController {

    private List<String> eventList = null;
    private ContractHelper contractHelper = new ContractHelper();

    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "{\"result\":\"" + "Hello, " + name + "!" + "\"}";
    }

    @RequestMapping(value = "/eventById", method = RequestMethod.GET)
    public String eventById(@RequestParam(value = "eventId") String eventId) {
        if (StringUtils.isNoneEmpty(eventId)) {
            Event e = contractHelper.getEventById(eventId);
            if (e != null) {
                return e.toString();
            }
        }
        return null;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String events() {
        List<Event> events = contractHelper.getAllEvents();
        return "[" + events.stream().map(e -> e.toString()).collect(Collectors.joining(",")) + "]";
    }

    @RequestMapping(value = "/event", method = RequestMethod.PUT)
    public boolean event(@RequestParam(value = "eventId") String eventId,
                         @RequestParam(value = "successSum") BigInteger successSum,
                         @RequestParam(value = "maxGuestsCount") BigInteger maxGuestsCount,
                         @RequestParam(value = "presalePrice") BigInteger presalePrice,
                         @RequestParam(value = "salePrice") BigInteger salePrice,
                         @RequestParam(value = "fundingDeadline") String fundingDeadline,
                         @RequestParam(value = "eventDate") String eventDate
    ) {
        return contractHelper.createNewEvent(eventId, successSum, maxGuestsCount, presalePrice, salePrice,
                BigInteger.valueOf(LocalDate.parse(fundingDeadline).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()),
                BigInteger.valueOf(LocalDate.parse(eventDate).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()));
    }

    @RequestMapping(value = "/payment", method = RequestMethod.PUT)
    public boolean payment(@RequestParam(value = "eventId") String eventId,
                           @RequestParam(value = "userId") String userId,
                           @RequestParam(value = "transactionId") String transactionId) {
        return contractHelper.createNewPayment(eventId, userId, transactionId);
    }
}
