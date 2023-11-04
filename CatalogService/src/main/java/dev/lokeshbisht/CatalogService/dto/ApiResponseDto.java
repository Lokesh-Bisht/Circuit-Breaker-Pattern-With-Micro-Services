package dev.lokeshbisht.CatalogService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDto<T> {

    private T data;

    private String message;

    private String[] errors;

    private MetaDataDto metaDataDto;
}
