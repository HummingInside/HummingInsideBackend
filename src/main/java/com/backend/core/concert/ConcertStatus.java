package com.backend.core.concert;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConcertStatus {

    ONAIR("ON-AIR"),
    UPCOMING("UPCOMING"),
    ENDED("ENDED");

    private final String desc;
}
