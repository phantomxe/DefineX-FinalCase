package com.patika.garantiservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor; 
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "creditcard")
public class CreditCard implements Serializable, Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "fee")
    private BigDecimal fee;

    @ManyToMany
    @JoinTable(
        name = "creditcard_campaign",
        joinColumns = @JoinColumn(name = "creditcard_id"),
        inverseJoinColumns = @JoinColumn(name = "campaign_id")
    )
    private List<Campaign> campaignList;

    private final String bank = "garanti";
}
