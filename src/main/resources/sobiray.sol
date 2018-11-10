pragma solidity ^0.4.25;
pragma experimental ABIEncoderV2;

contract Sobiray {
    
    enum EVENT_STATUS { CREATED, FUNDRASING, CANCELED, FAILED, SUCCESS }
    
    struct Guest{
        string guestId;
        string eventId;
        uint256 payDate;
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
        events.push(Event({status:EVENT_STATUS.CREATED,
        eventId:eventId,
        successSum:successSum,
        currentSum:0,
        maxGuestsCount:maxGuestsCount,
        presalePrice: presalePrice,
        salePrice: salePrice,
        fundingDeadline: fundingDeadline,
        eventDate: eventDate
        }));
    }


    function getEvent(string evId) public view returns( EVENT_STATUS status,
                                                            string eventId,
                                                            uint successSum,
                                                            uint currentSum,
                                                            uint maxGuestsCount,
                                                            uint presalePrice,
                                                            uint salePrice,
                                                            uint256 fundingDeadline,
                                                            uint256 eventDate)
                    {
        for (uint i=0; i<events.length; i++) {
          if (keccak256(events[i].eventId) == keccak256(evId)) {
            Event memory e = events[i];
            return( e.status,
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

    function getEventStatus(string eventId) public view returns (EVENT_STATUS status){
        uint successSum;
        uint currentSum;
        uint maxGuestsCount;
        uint presalePrice;
        uint salePrice;
        uint256 fundingDeadline;
        uint256 eventDate;

        (status, eventId, successSum, currentSum, maxGuestsCount, presalePrice, salePrice, fundingDeadline, eventDate)= getEvent(eventId);
    }

    function setEventStatus(string evId, EVENT_STATUS status) public
    {
        for (uint i=0; i<events.length; i++) {
          if (keccak256(events[i].eventId) == keccak256(evId)) {
            Event memory e = events[i];
            e.status = status;
            break;
        }
     }
    }

    function changeEventCurrentSum(string evId, uint delta) private
    {
        for (uint i=0; i<events.length; i++) {
          if (keccak256(events[i].eventId) == keccak256(evId)) {
            Event memory e = events[i];
            e.currentSum = e.currentSum + delta;
            break;
        }
     }
    }

    function startPresale(string eventId) public{
    EVENT_STATUS status = getEventStatus(eventId);
    if (status == EVENT_STATUS.CREATED){
            setEventStatus(eventId, EVENT_STATUS.FUNDRASING);
        }
    }

    function cancelPresale(string eventId) public{
    EVENT_STATUS status = getEventStatus(eventId);
    if (status == EVENT_STATUS.FUNDRASING){
            setEventStatus(eventId, EVENT_STATUS.CANCELED);
        }
    }

    function failPresale(string eventId) public{
    EVENT_STATUS status = getEventStatus(eventId);
    if (status == EVENT_STATUS.FUNDRASING){
            setEventStatus(eventId, EVENT_STATUS.FAILED);
        }
    }

    function successPresale(string eventId) public{
    EVENT_STATUS status = getEventStatus(eventId);
    if (status == EVENT_STATUS.FUNDRASING){
            setEventStatus(eventId, EVENT_STATUS.SUCCESS);
        }
    }

    function checkEndPresale(string eventId) public{
        EVENT_STATUS status;
        uint successSum;
        uint currentSum;
        uint maxGuestsCount;
        uint presalePrice;
        uint salePrice;
        uint256 fundingDeadline;
        uint256 eventDate;

        (eventId, successSum, currentSum, maxGuestsCount, presalePrice, salePrice, fundingDeadline, eventDate)= getEventSums(eventId);
        if (fundingDeadline<now){
            if (currentSum>=successSum){
                successPresale(eventId);
            }
            else{
                failPresale(eventId);
            }
        }
    }

    function addGuest(
        string eventId,
        string guestId)
    public {
        guests[eventId].push(Guest(
            {eventId : eventId,
            guestId : guestId,
            payDate: 0
            }
            )
        );
    }

    function getEventGuestsIds(string eventId) view public returns(string[]){
        Guest[] memory guests_ = guests[eventId];
        string[] storage guestsIds;
        for (uint j=0; j<guests_.length; j++) {
            guestsIds.push(guests_[j].guestId);
        }
        return guestsIds;
    }

    function setGuestPayDate(string eventId, string guestId, uint256 date) private{
        Guest[] memory event_guests = guests[eventId];
        for (uint j=0; j<event_guests.length; j++) {
            if (keccak256(event_guests[j].guestId) == keccak256(guestId)) {
                Guest memory g = event_guests[j];
                g.payDate = date;
            }
        }
    }

    function guestPaid(string eventId, string guestId, uint sum) public{
        EVENT_STATUS status = getEventStatus(eventId);
        setGuestPayDate(eventId, guestId, now);
        checkEndPresale(eventId);
        if (status == EVENT_STATUS.FUNDRASING){
            changeEventCurrentSum(eventId, sum);
        }
        if (status == EVENT_STATUS.SUCCESS){
            changeEventCurrentSum(eventId, sum);
        }
    }
}