package com.booking.tourbookingplatform.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SEATS_NOT_AVAILABLE(400, "Không đủ chỗ trống"),
    SCHEDULE_NOT_FOUND(404,"Không tìm thấy lịch trình");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
