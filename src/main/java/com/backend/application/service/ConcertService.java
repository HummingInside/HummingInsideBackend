package com.backend.application.service;

import com.backend.application.dto.concert.*;
import com.backend.core.member.Member;

import java.util.List;

public interface ConcertService {

    ConcertDetailResponse create(ConcertCreateRequest request, Member member);

    ConcertDetailResponse findById(Long id, Member member);

    List<ConcertSimpleResponse> findAll(ConcertListRequest request);

    ConcertDetailResponse update(Long id, ConcertUpdateRequest request, Member member);

    Long reserve(Long id, Long memberId);

    void delete(Long id);
}
