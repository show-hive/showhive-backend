package com.showhive.member.exception;

import com.showhive.ShowHiveException;
import lombok.Getter;

@Getter
public class MemberException extends ShowHiveException {

    public MemberException(MemberErrorCode memberErrorCode) {
        super(memberErrorCode.getMessage(), memberErrorCode.getStatusCode());
    }
}
