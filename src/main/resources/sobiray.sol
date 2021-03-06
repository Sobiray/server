pragma solidity ^0.4.25;
pragma experimental ABIEncoderV2;

contract Sobiray {

    enum EVENT_STATUS {CREATED, FUNDRASING, CANCELED, FAILED, SUCCESS}

    struct Guest {
        string guestId;
        string eventId;
        uint256 payDate;
        string transactionId;
    }

    struct Event {
        EVENT_STATUS status;
        string eventId;
        uint successSum;
        uint currentSum;
        uint maxGuestsCount;
        uint presalePrice;
        uint salePrice;
        uint256 fundingDeadline;
        uint256 eventDate;
    }

    Event[] events;
    mapping(string => Guest[]) private guests;

    constructor() public {}

    function addEvent(
        string eventId,
        uint successSum,
        uint maxGuestsCount,
        uint presalePrice,
        uint salePrice,
        uint256 fundingDeadline,
        uint256 eventDate)
    public {
        events.push(Event({status : EVENT_STATUS.CREATED,
            eventId : eventId,
            successSum : successSum,
            currentSum : 0,
            maxGuestsCount : maxGuestsCount,
            presalePrice : presalePrice,
            salePrice : salePrice,
            fundingDeadline : fundingDeadline,
            eventDate : eventDate
            }));
    }

    function getEventIds() view public returns (string[] ids){
        string[] eventIds;
        for (uint j = 0; j < events.length; j++) {
            eventIds.push(events[j].eventId);
        }
        return eventIds;
    }

    function getEventsLength() view public returns (uint eventLength){
        return events.length;
    }

    function getEventByNum(uint eventNum) view public returns (EVENT_STATUS status,
        string eventId,
        uint successSum,
        uint currentSum,
        uint maxGuestsCount,
        uint presalePrice,
        uint salePrice,
        uint256 fundingDeadline,
        uint256 eventDate){
        Event memory e = events[eventNum];
        return (e.status,
        e.eventId,
        e.successSum,
        e.currentSum,
        e.maxGuestsCount,
        e.presalePrice,
        e.salePrice,
        e.fundingDeadline,
        e.eventDate);
    }


    function getEvent(string evId) public view returns (EVENT_STATUS status,
        string eventId,
        uint successSum,
        uint currentSum,
        uint maxGuestsCount,
        uint presalePrice,
        uint salePrice,
        uint256 fundingDeadline,
        uint256 eventDate) {
        for (uint i = 0; i < events.length; i++) {
            if (keccak256(events[i].eventId) == keccak256(evId)) {
                Event memory e = events[i];
                return (e.status,
                e.eventId,
                e.successSum,
                e.currentSum,
                e.maxGuestsCount,
                e.presalePrice,
                e.salePrice,
                e.fundingDeadline,
                e.eventDate);
            }
        }
    }

    function getEventPrices(string evId) public view returns (
        string eventId,
        uint presalePrice,
        uint salePrice)
    {
        for (uint i = 0; i < events.length; i++) {
            if (keccak256(events[i].eventId) == keccak256(evId)) {
                Event memory e = events[i];
                return (
                e.eventId,
                e.presalePrice,
                e.salePrice);
            }
        }
    }

    function getEventSums(string evId) public view returns (
        string eventId,
        uint successSum,
        uint currentSum)
    {
        for (uint i = 0; i < events.length; i++) {
            if (keccak256(events[i].eventId) == keccak256(evId)) {
                Event memory e = events[i];
                return (
                e.eventId,
                e.successSum,
                e.currentSum);
            }
        }
    }

    function getEventStatus(string eventId) public view returns (EVENT_STATUS status){
        for (uint i = 0; i < events.length; i++) {
            if (keccak256(events[i].eventId) == keccak256(eventId)) {
                Event memory e = events[i];
                return (e.status);
            }
        }
    }

    function setEventStatus(string evId, EVENT_STATUS status) public
    {
        for (uint i = 0; i < events.length; i++) {
            if (keccak256(events[i].eventId) == keccak256(evId)) {
                Event memory e = events[i];
                e.status = status;
                break;
            }
        }
    }

    function changeEventCurrentSum(string evId, uint delta) private
    {
        for (uint i = 0; i < events.length; i++) {
            if (keccak256(events[i].eventId) == keccak256(evId)) {
                Event memory e = events[i];
                e.currentSum = e.currentSum + delta;
                break;
            }
        }
    }

    function startPresale(string eventId) public {
        EVENT_STATUS status = getEventStatus(eventId);
        if (status == EVENT_STATUS.CREATED) {
            setEventStatus(eventId, EVENT_STATUS.FUNDRASING);
        }
    }

    function cancelPresale(string eventId) public {
        EVENT_STATUS status = getEventStatus(eventId);
        if (status == EVENT_STATUS.FUNDRASING) {
            setEventStatus(eventId, EVENT_STATUS.CANCELED);
        }
    }

    function failPresale(string eventId) public {
        EVENT_STATUS status = getEventStatus(eventId);
        if (status == EVENT_STATUS.FUNDRASING) {
            setEventStatus(eventId, EVENT_STATUS.FAILED);
        }
    }

    function successPresale(string eventId) public {
        EVENT_STATUS status = getEventStatus(eventId);
        if (status == EVENT_STATUS.FUNDRASING) {
            setEventStatus(eventId, EVENT_STATUS.SUCCESS);
        }
    }

    function checkEndPresale(string eventId) public {
        EVENT_STATUS status;
        uint successSum;
        uint currentSum;
        uint maxGuestsCount;
        uint presalePrice;
        uint salePrice;
        uint256 fundingDeadline;
        uint256 eventDate;

        (eventId, successSum, currentSum) = getEventSums(eventId);
        if (fundingDeadline < now) {
            if (currentSum >= successSum) {
                successPresale(eventId);
            }
            else {
                failPresale(eventId);
            }
        }
    }

    function addGuest(
        string eventId,
        string guestId,
        string transactionId)
    public {
        guests[eventId].push(Guest(
                {eventId : eventId,
                guestId : guestId,
                payDate : 0,
                transactionId : transactionId
                }
            )
        );
    }


    function getGuest(string eventId, string guestId) public view returns
    (string _guestId,
        string _eventId,
        uint256 payDate,
        string transactionId) {
        Guest[] memory guests_ = guests[eventId];
        for (uint i = 0; i < guests_.length; i++) {
            if (keccak256(guests_[i].guestId) == keccak256(guestId)) {
                Guest memory g = guests_[i];
                return (g.guestId,
                g.eventId,
                g.payDate,
                g.transactionId);
            }
        }
    }

    function getEventGuestsLength(string eventId) view public returns (uint){
        Guest[] memory guests_ = guests[eventId];
        return guests_.length;
    }

    function getEventGuestsIds(string eventId, uint id) view public returns (string){
        Guest[] memory guests_ = guests[eventId];
        return guests_[id].guestId;
    }

    function setGuestPayDate(string eventId, string guestId, uint256 date) private {
        Guest[] memory event_guests = guests[eventId];
        for (uint j = 0; j < event_guests.length; j++) {
            if (keccak256(event_guests[j].guestId) == keccak256(guestId)) {
                Guest memory g = event_guests[j];
                g.payDate = date;
            }
        }
    }

    function guestPaid(string eventId, string guestId) public {
        uint presalePrice;
        uint salePrice;
        (eventId, presalePrice, salePrice) = getEventPrices(eventId);

        EVENT_STATUS status = getEventStatus(eventId);
        setGuestPayDate(eventId, guestId, now);
        checkEndPresale(eventId);
        if (status == EVENT_STATUS.FUNDRASING) {
            changeEventCurrentSum(eventId, presalePrice);
        }
        if (status == EVENT_STATUS.SUCCESS) {
            changeEventCurrentSum(eventId, salePrice);
        }
    }
}