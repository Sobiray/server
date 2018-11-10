package com.opensoul.sobiray.server.controller;

import com.opensoul.sobiray.server.utils.ContractHelper;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class VkController {

    private List<String> eventList = null;

    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "{\"result\":\"" + "Hello, " + name + "!" + "\"}";
    }

    @RequestMapping("/payment")
    public boolean payment(@RequestParam(value = "from") String from,
                           @RequestParam(value = "to") String to,
                           @RequestParam(value = "amount") BigDecimal amount) {
        return ContractHelper.createNewPayment(from, to, amount);
    }

    @RequestMapping("/events")
    public String events() {
        if (eventList == null) {
            eventList = ContractHelper.getEventList();
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
                         @RequestParam(value = "successSum") BigDecimal successSum,
                         @RequestParam(value = "maxGuestsCount") int maxGuestsCount,
                         @RequestParam(value = "presalePrice") BigDecimal presalePrice,
                         @RequestParam(value = "salePrice") BigDecimal salePrice,
                         @RequestParam(value = "lastPresaleDate") LocalDateTime lastPresaleDate) {
        return ContractHelper.createNewEvent(eventId, successSum, maxGuestsCount, presalePrice, salePrice, lastPresaleDate);
    }
}
