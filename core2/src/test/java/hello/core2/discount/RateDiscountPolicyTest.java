package hello.core2.discount;

import hello.core2.AppConfig;
import hello.core2.member.Grade;
import hello.core2.member.Member;
import hello.core2.member.MemberService;
import hello.core2.order.Order;
import hello.core2.order.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void createOrder(){
        //given
        Long memberId = 2L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        //then
        assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }



}