package com.libraryManagementSystem.mapper;

import com.libraryManagementSystem.dto.FineDTO;
import com.libraryManagementSystem.model.Fine;
import com.libraryManagementSystem.model.Member;
import com.libraryManagementSystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FineMapper {

    @Autowired
    private MemberRepository memberRepository;

    public FineDTO toDTO(Fine fine){
        if(fine == null){
            return null;
        }

        return new FineDTO(
                fine.getId(),
                fine.getMember() != null ? fine.getMember().getId() : null,
                fine.getAmount(),
                fine.getDueDate(),
                fine.isPaidStatus()
        );
    }

    public Fine toEntity(FineDTO fineDTO){
        if(fineDTO == null){
            return null;
        }

        Member member = fineDTO.getMemberId() != null
                ? memberRepository.findById(fineDTO.getMemberId()).orElse(null) : null;

        Fine fine = new Fine(
                member,
                fineDTO.getAmount(),
                fineDTO.getDueDate(),
                fineDTO.isPaidStatus()
        );

        fine.setId(fineDTO.getId());

        return fine;
    }

    public List<FineDTO> toDTOList(List<Fine> fines) {
        return fines.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Fine> toEntityList(List<FineDTO> fineDTOs) {
        return fineDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}