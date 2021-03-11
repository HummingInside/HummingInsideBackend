package com.humming.application.service;

import com.humming.application.dto.concert.*;
import com.humming.core.member.Member;

import java.util.List;

public interface ConcertService {

    ConcertDetailResponse create(ConcertCreateRequest request, Member member);

    ConcertDetailResponse findById(Long id, Member member);

    List<ConcertSimpleResponse> findAll(ConcertListRequest request);

    ConcertDetailResponse update(Long id, ConcertUpdateRequest request, Member member);

    Long reserve(Long id, Long memberId);

    void delete(Long id);

    ConcertDetailResponse updateStatus(Long id, Member member, String status);
}
