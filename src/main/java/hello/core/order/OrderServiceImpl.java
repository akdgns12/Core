package hello.core.order;

import com.sun.source.tree.UsesTree;
import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
//@RequiredArgsConstructor // final이 붙은 필수 생성값들을 기존의 생성자 코드와 똑같이 자동으로 만들어주는 역할
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    // 추상화(인터페이스)에만 의존하도록 코드 변경
    private final DiscountPolicy discountPolicy;

    @Autowired
//      AUtowired는 먼저 타입으로 매칭을 시도하고, 이때 같은 타입의 빈이 여러개 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
