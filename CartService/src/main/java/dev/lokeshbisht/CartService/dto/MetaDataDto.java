package dev.lokeshbisht.CartService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MetaDataDto {

    private int page;

    private int size;

    private int total;

    private double took;
}
