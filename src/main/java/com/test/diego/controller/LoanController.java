package com.test.diego.controller;

import com.test.diego.dto.LoanRequestDto;
import com.test.diego.dto.LoanResponseDto;
import com.test.diego.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService svc;

    public LoanController(LoanService svc) {
        this.svc = svc;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanResponseDto requestLoan(@Valid @RequestBody LoanRequestDto dto) {
        return svc.requestLoan(dto);
    }

    @GetMapping("/{id}")
    public LoanResponseDto getLoan(@PathVariable Long id) {
        return svc.getLoanById(id);
    }

    @GetMapping
    public List<LoanResponseDto> listByUser(@RequestParam("userId") Long userId) {
        return svc.getLoansByUser(userId);
    }

    @PutMapping("/{id}/status")
    public LoanResponseDto changeStatus(
            @PathVariable Long id,
            @RequestParam("status") String status) {
        return svc.changeStatus(id, status);
    }
    
}
