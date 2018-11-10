package com.opensoul.sobiray.server.controller;

import com.opensoul.sobiray.server.utils.ContractHelper;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class VkController {

    private List<String> eventList = null;
    private ContractHelper contractHelper = new ContractHelper();

    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "{\"result\":\"" + "Hello, " + name + "!" + "\"}";
    }

    @RequestMapping("/payment")
    public boolean payment(@RequestParam(value = "eventId") String eventId,
                           @RequestParam(value = "userId") String userId,
                           @RequestParam(value = "transactionId") String transactionId) {
        return contractHelper.createNewPayment(eventId, userId, transactionId);
    }

    @RequestMapping("/events")
    public String events() {
        if (eventList == null) {
            eventList = contractHelper.getEventList();
            eventList = Stream.of("2", "fd", "sfs").collect(Collectors.toList());
        }
        StringBuffer result = new StringBuffer("[");
        int i = 0;
        for (String event : eventList) {
            result.append(i++ == 0 ? "{" : ",{");
            result.append(" \"id\": 1,\n" +
                    "    \"preorderPrice\": 150,\n" +
                    "    \"price\": 300,\n" +
                    "    \"deadline\": \"27.01.2018\",\n" +
                    "    \"date\": \"15.02.2018\",\n" +
                    "    \"requiredMoney\": 15000,\n" +
                    "    \"maxGuests\": 50");
            result.append("}");
        }
        result.append("]");
        return result.toString();
    }

    @RequestMapping("/event")
    public boolean event(@RequestParam(value = "eventId") String eventId,
                         @RequestParam(value = "successSum") BigInteger successSum,
                         @RequestParam(value = "maxGuestsCount") BigInteger maxGuestsCount,
                         @RequestParam(value = "presalePrice") BigInteger presalePrice,
                         @RequestParam(value = "salePrice") BigInteger salePrice,
                         @RequestParam(value = "fundingDeadline") String fundingDeadline,
                         @RequestParam(value = "eventDate") String eventDate
                         ) {
        return contractHelper.createNewEvent(eventId, successSum, maxGuestsCount, presalePrice, salePrice,
                BigInteger.valueOf(LocalDate.parse(fundingDeadline).toEpochDay()), BigInteger.valueOf(LocalDate.parse(eventDate).toEpochDay()));
    }
}
