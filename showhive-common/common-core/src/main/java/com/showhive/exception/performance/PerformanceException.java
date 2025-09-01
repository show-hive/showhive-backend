package com.showhive.exception.performance;

import com.showhive.exception.ShowHiveException;
import lombok.Getter;

@Getter
public class PerformanceException extends ShowHiveException {

    public PerformanceException(PerformanceErrorCode performanceErrorCode) {
        super(performanceErrorCode.getMessage(), performanceErrorCode.getStatusCode());
    }
}
