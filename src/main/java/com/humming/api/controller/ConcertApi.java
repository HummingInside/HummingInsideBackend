package com.humming.api.controller;

import com.humming.domain.Member;
import com.humming.api.dto.concert.*;
import com.humming.exception.ResourceNotFoundException;
import com.humming.repository.ConcertRepository;
import com.humming.repository.MemberRepository;
import com.humming.service.ConcertService;
import com.humming.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/concerts")
@RestController
public class ConcertApi {

    private final ConcertService concertService;
    private final FileService fileService;
    private final MemberRepository memberRepository;
    private final ConcertRepository concertRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ConcertCreateRequest request){
        Member member = (Member) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        ConcertDetailResponse response = concertService.create(request, member);
        if(response == null){
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Member member = (Member) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        ConcertDetailResponse response = concertService.findById(id, member);
        if(response == null){
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getList(ConcertListRequest request){
        List<ConcertSimpleResponse> responses = concertService.findAll(request);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody ConcertUpdateRequest request) throws IOException {
        Member member = (Member) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        fileService.delete(id, request.getImgUrl());
        ConcertDetailResponse response = concertService.update(id, request, member);
        if(response == null){
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException {
        Member member = (Member) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        fileService.delete(id, "delete");
        concertService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity<?> reserve(@PathVariable Long id){
        Member member = (Member) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(concertService.reserve(id, member.getId()));
    }
}
