package com.deezzex.crypto.entity;

import com.deezzex.crypto.model.Blockchain;
import com.deezzex.crypto.model.Token;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "wallets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private Blockchain blockchain;

    private String address;

    @Enumerated(EnumType.STRING)
    private Token token;

    private Instant createdAt;

    @ToString.Exclude
    @OneToMany(mappedBy = "fromWallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> outcomingTransaction;

    @ToString.Exclude
    @OneToMany(mappedBy = "toWallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> incomingTransaction;
}
