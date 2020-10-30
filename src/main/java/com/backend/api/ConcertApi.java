package com.backend.api;

import com.backend.api.exception.ResourceNotFoundException;
import com.backend.application.dto.concert.*;
import com.backend.application.service.FileService;
import com.backend.application.serviceImpl.ConcertServiceImpl;
import com.backend.core.member.Member;
import com.backend.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/concerts")
@RestController
public class ConcertApi {

    private final ConcertServiceImpl concertService;
    private final FileService fileService;
    private final MemberRepository memberRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ConcertCreateRequest request){
        // TODO : get member
        Member member = memberRepository.findByName("junyoung").get();
        ConcertDetailResponse response = concertService.create(request, member);
        if(response == null){
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        ConcertDetailResponse response = concertService.findById(id);
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
                                    @RequestBody ConcertUpdateRequest request){
        // TODO : get member
        Member member = memberRepository.findByName("junyoung").get();
        ConcertDetailResponse response = concertService.update(id, request, member);
        if(response == null){
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<?> uploadImage(@PathVariable Long id,
                                         @RequestBody MultipartFile file) throws IOException {
        String fileName = fileService.store(id, file);
        if(fileName == null){
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(fileName);
    }
}
