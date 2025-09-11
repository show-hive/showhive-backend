package com.showhive.member.api;

import com.showhive.member.api.dto.MemberResponse;
import com.showhive.member.api.dto.MemberSignUpRequest;
import com.showhive.member.application.MemberFindUseCase;
import com.showhive.member.application.MemberSignUpUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSignUpUseCase memberSignUpUseCase;
    private final MemberFindUseCase memberFindUseCase;

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody @Valid MemberSignUpRequest request) {
        memberSignUpUseCase.signUp(request.toCommand());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable long memberId) {
        MemberResponse response = memberFindUseCase.findMember(memberId);
        return ResponseEntity.ok(response);
    }
}
