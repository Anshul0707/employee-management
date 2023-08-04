package com.employee.management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {
    private String status;
    private String message;
    private String requestId;

}
