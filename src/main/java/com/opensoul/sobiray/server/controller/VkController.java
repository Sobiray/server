package com.opensoul.sobiray.server.controller;

import com.opensoul.sobiray.server.utils.ContractHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class VkController {

    private List<String> eventList = null;

    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name;
    }

    @RequestMapping("/newPayment")
    public boolean newPayment(@RequestParam(value = "from") String from,
                              @RequestParam(value = "to") String to,
                              @RequestParam(value = "amount") BigDecimal amount) {
        return ContractHelper.createNewPayment(from, to, amount);
    }

    @RequestMapping("/eventList")
    public List<String> eventList() {
        if (eventList == null) {
            eventList = ContractHelper.getEventList();
        }
        return eventList;
    }

    @RequestMapping("/newEvent")
    public boolean newEvent(@RequestParam(value = "eventId") String eventId,
                            @RequestParam(value = "successSum") BigDecimal successSum,
                            @RequestParam(value = "maxGuestsCount") int maxGuestsCount,
                            @RequestParam(value = "presalePrice") BigDecimal presalePrice,
                            @RequestParam(value = "salePrice") BigDecimal salePrice,
                            @RequestParam(value = "lastPresaleDate") LocalDateTime lastPresaleDate) {
        return ContractHelper.createNewEvent(eventId, successSum, maxGuestsCount, presalePrice, salePrice, lastPresaleDate);
    }
}
