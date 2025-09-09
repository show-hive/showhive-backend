package com.showhive.member.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

//    private final MemberSignUpUseCase memberSignUpUseCase;
//    private final MemberFindUseCase memberFindUseCase;
//
//    @PostMapping
//    public ResponseEntity<Void> createMember(@RequestBody @Valid MemberSignUpRequest request) {
//        memberSignUpUseCase.signUp(request.toCommand());
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/{memberId}")
//    public ResponseEntity<MemberResponse> findMember(@PathVariable long memberId) {
//        MemberResponse response = memberFindUseCase.findMember(memberId);
//        return ResponseEntity.ok(response);
//    }
}
