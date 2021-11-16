package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)  // test 후에도 Rollback 실행시키지 않는다.
    public void testMember() throws Exception {
        //given - username 입력하여 member 생성(id는 자동 생성된다.)
        Member member = new Member();
        member.setUsername("memberA");

        //when  - member를 db에 저장하면 id값을 return 받는다.
        Long savedId = memberRepository.save(member);   //member db에 저장하기
        Member findMember = memberRepository.find(savedId); //id를 가지고 db에서 해당 member 정보 불러오기
        
        //then - 입력한 member 정보와 불러온 member 정보가 같은지 test
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);

        System.out.println("findMember == member: " + (findMember == member));
        //같은 트랜잭션 안에서 저장하고 조회하면 영속성 context가 같다. 같은 영속성 context 안에서는 id 값이 같으면 같은 entity로 식별한다.
        //그래서 @Rollback(false)로 실행을하면 로그에 select문은 없고 insert문만 출력되는걸 확인할 수 있다.

    }
}