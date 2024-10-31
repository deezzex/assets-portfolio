package com.deezzex.fiat.service;

import com.deezzex.fiat.dto.report.CreateReportDto;
import com.deezzex.fiat.entity.Account;
import com.deezzex.fiat.entity.Report;
import com.deezzex.fiat.entity.Transaction;
import com.deezzex.shared.exception.DataNotFoundException;
import com.deezzex.fiat.model.DebitCreditIndicator;
import com.deezzex.fiat.repository.ReportRepository;
import com.deezzex.fiat.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public Report getReport(Integer reportId) {
        log.info("Getting report by id: {}", reportId);

        return reportRepository.findById(reportId).orElseThrow(() -> new DataNotFoundException("Report", reportId));
    }

    public Report createReport(CreateReportDto createReportDto) {
        log.info("Creating report: {}", createReportDto);

        Account account = accountService.getAccount(createReportDto.getAccountId());

        List<Transaction> transactions = transactionRepository
                .findByCreatedAtBetween(createReportDto.getStartDate().atStartOfDay().toInstant(ZoneOffset.UTC),
                        createReportDto.getEndDate().atStartOfDay().toInstant(ZoneOffset.UTC));

        BigDecimal debits = getTotalAmount(transactions, DebitCreditIndicator.D);

        BigDecimal credits = getTotalAmount(transactions, DebitCreditIndicator.C);

        var report = buildReport(createReportDto, account, credits, debits);

        report = reportRepository.save(report);

        log.info("Report successfully created: {}", report);

        return report;
    }

    private BigDecimal getTotalAmount(List<Transaction> transactions, DebitCreditIndicator debitCreditIndicator) {
        return transactions.stream()
                .filter(trx -> trx.getDebitCreditIndicator().equals(debitCreditIndicator))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static Report buildReport(CreateReportDto createReportDto, Account account, BigDecimal credits, BigDecimal debits) {
        return Report.builder()
                .account(account)
                .createdAt(Instant.now())
                .startDate(createReportDto.getStartDate())
                .endDate(createReportDto.getEndDate())
                .totalCreditAmount(credits)
                .totalDebitAmount(debits)
                .build();
    }
}
