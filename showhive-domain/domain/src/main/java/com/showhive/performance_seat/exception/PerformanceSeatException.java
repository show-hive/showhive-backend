package com.showhive.performance_seat.exception;

import com.showhive.ShowHiveException;
import lombok.Getter;

@Getter
public class PerformanceSeatException extends ShowHiveException {

    public PerformanceSeatException(PerformanceSeatErrorCode performanceErrorCode) {
        super(performanceErrorCode.getMessage(), performanceErrorCode.getStatusCode());
    }
}
