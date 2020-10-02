package com.backend.application.service;

import com.backend.application.dto.concert.*;
import com.backend.core.user.User;

import java.util.List;

public interface ConcertService {

    ConcertDetailResponse create(ConcertCreateRequest request, User user);

    ConcertDetailResponse findById(Long id);

    List<ConcertSimpleResponse> findAll(ConcertListRequest request);

    ConcertDetailResponse update(Long id, ConcertUpdateRequest request, User user);
}
