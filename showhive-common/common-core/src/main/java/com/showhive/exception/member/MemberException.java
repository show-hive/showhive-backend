package com.showhive.exception.member;

import com.showhive.exception.ShowHiveException;
import lombok.Getter;

@Getter
public class MemberException extends ShowHiveException {

    public MemberException(MemberErrorCode memberErrorCode) {
        super(memberErrorCode.getMessage(), memberErrorCode.getStatusCode());
    }
}
