package com.libraryManagementSystem.service;

import com.libraryManagementSystem.dto.FineDTO;

import java.util.List;

public interface FineService {
    public FineDTO calculateFine(Long memberId);
    public List<FineDTO> getPendingFines(Long memberId);
    public FineDTO payFine(Long fineId);

}
