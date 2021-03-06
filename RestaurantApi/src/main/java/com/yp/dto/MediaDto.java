package com.yp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediaDto {

    private Long id;
    private String name;
    private byte[] fileContent;
}
