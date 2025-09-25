package com.showhive.category.exception;

import com.showhive.ShowHiveException;
import lombok.Getter;

@Getter
public class CategoryException extends ShowHiveException {

    public CategoryException(CategoryErrorCode categoryErrorCode) {
        super(categoryErrorCode.getMessage(), categoryErrorCode.getStatusCode());
    }
}
