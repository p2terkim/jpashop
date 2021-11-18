package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //JPA의 모든 데이터 변경이나, 로직들을 가급적이면 트랜잭션 안에서 실행돼야 한다.
//@AllArgsConstructor //생성자를 만들어준다.
@RequiredArgsConstructor    //final이 붙은 필드를 가지고 생성자를 만든다.
public class MemberService {

//    @Autowired    //보통 이렇게 많이 사용한다. 하지만 변경이 불가능하다는 단점이 있다. 그래서 생성자 Injection을 권장한다.
//    private MemberRepository memberRepository;

    private final MemberRepository memberRepository;

//    생성자 Injection
//    @Autowired
//    public MemberService (MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원가입
     */
    @Transactional  //readOnly=false가 default
    public Long join(Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복회원 검증  - 같은 이름이면 회원 가입 안됨
     *
     *              * 하지만 똑같은 이름으로 동시에 회원가입(insert)를 할 수도 있다.
     *              그럼 둘이 동시에 중복회원 검증 로직을 통과할 수 있다.
     *              따라서 검증 비즈니스 로직이 있다고 하더라도 데이터베이스의 Member 테이블의
     *              name에 Unique 제약조건으로 잡아주는 것을 권장한다.
     * @param member
     */
    private void validateDuplicateMember(Member member) {

        List<Member> findMembers = memberRepository.findByName(member.getName());
        //EXCEPTION
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     * @return
     */
//    @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

//    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
