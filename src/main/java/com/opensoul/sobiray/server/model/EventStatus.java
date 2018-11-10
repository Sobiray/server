package com.opensoul.sobiray.server.model;

public enum EventStatus {
    CREATED(0), FUNDRASING(1), CANCELED(2), FAILED(3), SUCCESS(4);

    public int id;

    EventStatus(int id) {
        this.id = id;
    }

    public static EventStatus byId(int id) {
        for (EventStatus eventStatus : EventStatus.values()) {
            if (eventStatus.id == id) {
                return eventStatus;
            }
        }
        return null;
    }
}
