package com.commons.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Price implements Comparable<Price> {
    @Column
    @Getter
    private BigDecimal price;

    public Price(long price) {this.price = new BigDecimal(price);}

    /**
     * null == null
     * null < (not null)
     */
    @Override
    public int compareTo(@NotNull Price other) {
        if (price == null) {
            return other.price == null ? 0 : -1;
        }
        return other.price == null ? 1 : price.compareTo(other.price);
    }
}
