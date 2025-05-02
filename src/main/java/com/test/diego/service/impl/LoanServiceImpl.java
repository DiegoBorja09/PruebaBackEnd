package com.test.diego.service.impl;

import com.test.diego.dto.LoanRequestDto;
import com.test.diego.dto.LoanResponseDto;
import com.test.diego.entity.Loan;
import com.test.diego.entity.LoanStatus;
import com.test.diego.repository.LoanRepository;
import com.test.diego.service.LoanService;
import com.test.diego.util.exception.LoanNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository repo;

    public LoanServiceImpl(LoanRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public LoanResponseDto requestLoan(LoanRequestDto dto) {
        Loan loan = new Loan(dto.getAmount(), dto.getTermMonths(), dto.getUserId());
        Loan saved = repo.save(loan);
        return toDto(saved);
    }

    @Override
    public LoanResponseDto getLoanById(Long id) {
        Loan loan = repo.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));
        return toDto(loan);
    }

    @Override
    public List<LoanResponseDto> getLoansByUser(Long userId) {
        return repo.findByUserId(userId)
                   .stream()
                   .map(this::toDto)
                   .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LoanResponseDto changeStatus(Long id, String newStatus) {
        Loan loan = repo.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));
        loan.setStatus(LoanStatus.valueOf(newStatus.toUpperCase()));
        Loan updated = repo.save(loan);
        return toDto(updated);
    }

    private LoanResponseDto toDto(Loan loan) {
        LoanResponseDto dto = new LoanResponseDto();
        dto.setId(loan.getId());
        dto.setAmount(loan.getAmount());
        dto.setTermMonths(loan.getTermMonths());
        dto.setStatus(loan.getStatus());
        dto.setUserId(loan.getUserId());
        return dto;
    }
}

