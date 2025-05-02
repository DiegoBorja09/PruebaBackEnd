package com.test.diego.service;

import com.test.diego.dto.LoanRequestDto;
import com.test.diego.dto.LoanResponseDto;
import java.util.List;

public interface LoanService {

    LoanResponseDto requestLoan(LoanRequestDto dto);
    LoanResponseDto getLoanById(Long id);
    List<LoanResponseDto> getLoansByUser(Long userId);
    LoanResponseDto changeStatus(Long id, String newStatus);
}
