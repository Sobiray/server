package com.opensoul.sobiray.server.controller;

import com.opensoul.sobiray.server.model.Event;
import com.opensoul.sobiray.server.utils.ContractHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(VkController.class);
    private List<String> eventList = null;
    private ContractHelper contractHelper = new ContractHelper();

    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "World") String name) {
        log.info("Calling /hi. Input params: name={}", name);
        String res = "{\"result\":\"" + "Hello, " + name + "!" + "\"}";
        log.info("Method /hi returned {}", res);
        return res;
    }

    @RequestMapping(value = "/eventById", method = RequestMethod.GET)
    public String eventById(@RequestParam(value = "eventId") String eventId) {
        log.info("Calling /eventById. Input params: eventId={}", eventId);
        if (StringUtils.isNoneEmpty(eventId)) {
            Event e = contractHelper.getEventById(eventId);
            if (e != null) {
                log.info("Method /eventById returned {}", e.toString());
                return e.toString();
            }
        }
        log.info("Method /eventById returned null");
        return null;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String events() {
        log.info("Calling /events. No input params");
        List<Event> events = contractHelper.getAllEvents();
        String res = "[" + events.stream().map(e -> e.toString()).collect(Collectors.joining(",")) + "]";
        log.info("Method /events returned {}", res);
        return res;
    }

    @RequestMapping(value = "/event")
    public boolean event(@RequestParam(value = "eventId") String eventId,
                         @RequestParam(value = "successSum") BigInteger successSum,
                         @RequestParam(value = "maxGuestsCount") BigInteger maxGuestsCount,
                         @RequestParam(value = "presalePrice") BigInteger presalePrice,
                         @RequestParam(value = "salePrice") BigInteger salePrice,
                         @RequestParam(value = "fundingDeadline") String fundingDeadline,
                         @RequestParam(value = "eventDate") String eventDate
    ) {
        log.info("Calling /event. Input params: eventId={}, successSum={}, maxGuestsCount={}, presalePrice={}, salePrice={}, fundingDeadline={}, eventDate={}",
                eventId, successSum, maxGuestsCount, presalePrice, salePrice, fundingDeadline, eventDate);
        boolean res = contractHelper.createNewEvent(eventId, successSum, maxGuestsCount, presalePrice, salePrice,
                BigInteger.valueOf(LocalDate.parse(fundingDeadline).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()),
                BigInteger.valueOf(LocalDate.parse(eventDate).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()));
        log.info("Method /event returned {}", res);
        return res;
    }

    @RequestMapping(value = "/payment")
    public boolean payment(@RequestParam(value = "eventId") String eventId,
                           @RequestParam(value = "userId") String userId,
                           @RequestParam(value = "transactionId") String transactionId) {
        log.info("Calling /payment. Input params: eventId={}, userId={}, transactionId={}",
                eventId, userId, transactionId);
        boolean res = contractHelper.createNewPayment(eventId, userId, transactionId);
        log.info("Method /payment returned {}", res);
        return res;
    }
}
