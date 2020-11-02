package com.backend.application.service;

import java.util.HashMap;

public interface StatisticsService {

    HashMap<String, String> getMyConcertList(Long userId);
}
