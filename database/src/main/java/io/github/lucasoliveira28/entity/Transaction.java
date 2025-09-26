package io.github.lucasoliveira28.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "balance", precision = 10, scale = 2, nullable = false)
    private BigDecimal value;

    @ManyToOne()
    @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToOne()
    @JoinColumn(name = "payee_id")
    private User payee;

}
