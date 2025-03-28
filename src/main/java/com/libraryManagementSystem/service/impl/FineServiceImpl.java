package com.libraryManagementSystem.service.impl;

import com.libraryManagementSystem.dto.FineDTO;
import com.libraryManagementSystem.mapper.FineMapper;
import com.libraryManagementSystem.model.Fine;
import com.libraryManagementSystem.model.Member;
import com.libraryManagementSystem.model.Transaction;
import com.libraryManagementSystem.repository.FineRepository;
import com.libraryManagementSystem.repository.MemberRepository;
import com.libraryManagementSystem.repository.TransactionRepository;
import com.libraryManagementSystem.service.FineService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class FineServiceImpl implements FineService {


    private final FineRepository fineRepository;
    private final TransactionRepository transactionRepository;
    private final MemberRepository memberRepository;
    private final FineMapper fineMapper;

    private static final double DAILY_FINE_RATE = 5.0; // ₹5 per day after due date

    @Autowired
    public FineServiceImpl(FineRepository fineRepository,
                           TransactionRepository transactionRepository,
                           MemberRepository memberRepository, FineMapper fineMapper){
        this.fineRepository = fineRepository;
        this.transactionRepository = transactionRepository;
        this.memberRepository = memberRepository;
        this.fineMapper = fineMapper;
    }

    // Calculate fine for a member if any book is overdue
    @Transactional
    public FineDTO calculateFine(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not Found with id:" + memberId));

        List<Transaction> overdueTransactions = transactionRepository
                .findByBorrowerAndDueDateBeforeAndReturnDateIsNull(member, LocalDate.now());

        double totalFine = 0;
        Fine fine = new Fine();;

        for(Transaction transaction : overdueTransactions){
            long overdueDays = ChronoUnit.DAYS.between(transaction.getDueDate(), LocalDate.now());
            double fineAmount = overdueDays * DAILY_FINE_RATE;
            totalFine += fineAmount;

            fine.setMember(member);
            fine.setAmount(fineAmount);
            fine.setDueDate(LocalDate.now().plusDays(7)); // Fine must be paid within a week
            fine.setPaidStatus(false);
            fineRepository.save(fine);
        }

        return totalFine > 0 ? fineMapper.toDTO(fine) : fineMapper.toDTO(null);
    }

    // Get all pending fines
    @Transactional(readOnly = true)
    public List<FineDTO> getPendingFines(Long memberId){
        return fineMapper.toDTOList(fineRepository.findByMemberIdAndPaidStatusFalse(memberId));
    }

    // pay fine
    @Transactional
    public FineDTO payFine(Long fineId){
        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() -> new EntityNotFoundException("fine is not found with id: " + fineId));

        if(fine.isPaidStatus()){
            System.out.println("Fine is already paid");
            return fineMapper.toDTO(fine);
        }

        fine.setPaidStatus(true);
        fineRepository.save(fine);
        return fineMapper.toDTO(fine);
    }
}