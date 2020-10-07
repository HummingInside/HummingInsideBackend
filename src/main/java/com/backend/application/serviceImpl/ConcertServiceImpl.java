package com.backend.application.serviceImpl;

import com.backend.application.dto.concert.*;
import com.backend.application.service.ConcertService;
import com.backend.core.concert.CategoryRepository;
import com.backend.core.concert.Concert;
import com.backend.core.concert.ConcertRepository;
import com.backend.core.concert.ConcertStatus;
import com.backend.core.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Transactional
@Service
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ConcertDetailResponse create(ConcertCreateRequest request, Member member) {
        // TODO : validate request
        return categoryRepository.findById(request.getCategoryId()).map(category -> {
            Concert concert = concertRepository.save(request.toEntity(member, category));
            return new ConcertDetailResponse(concert);
        }).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public ConcertDetailResponse findById(Long id) {
        // TODO : validate request
        return concertRepository.findById(id)
                .map(ConcertDetailResponse::new).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConcertSimpleResponse> findAll(ConcertListRequest request) {
        // TODO : validate request
        // TODO : pagination

        ConcertStatus status = request.getConcertStatus() == null ?
                null : ConcertStatus.valueOf(request.getConcertStatus());
        return concertRepository.findAllByCriteria(status,
                request.getCategoryId(), request.getPerformerName())
                .stream().map(ConcertSimpleResponse::new).collect(toList());
    }

    @Override
    public ConcertDetailResponse update(Long id, ConcertUpdateRequest request, Member member) {
        // TODO : validate request
        return concertRepository.findById(id).map(concert -> {
            concert.updateInfo(request.getTitle(), request.getDate(), request.getDescription(),
                    request.getMaxAudience(), request.getPrice());
            return new ConcertDetailResponse(concert);
        }).orElse(null);
    }
}
