package com.showhive.performance.exception;

import com.showhive.ShowHiveException;
import lombok.Getter;

@Getter
public class PerformanceException extends ShowHiveException {

    public PerformanceException(PerformanceErrorCode performanceErrorCode) {
        super(performanceErrorCode.getMessage(), performanceErrorCode.getStatusCode());
    }
}
