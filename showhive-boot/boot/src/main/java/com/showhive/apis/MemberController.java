package com.showhive.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.showhive.apis.dto.MemberResponse;
import com.showhive.apis.dto.MemberSignUpRequest;
import com.showhive.application.MemberFindUseCase;
import com.showhive.application.MemberSignUpUseCase;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSignUpUseCase memberSignUpUseCase;
    private final MemberFindUseCase memberFindUseCase;

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberSignUpRequest request) {
        memberSignUpUseCase.signUp(request.toCommand());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable long memberId) {
        MemberResponse response = memberFindUseCase.findMember(memberId);
        return ResponseEntity.ok(response);
    }
}
