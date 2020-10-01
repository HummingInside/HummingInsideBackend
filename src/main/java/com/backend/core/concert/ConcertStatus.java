package com.backend.core.concert;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConcertStatus {

    ONAIR("방송중"),
    UPCOMING("준비중"),
    ENDED("종료된");

    private final String desc;
}
