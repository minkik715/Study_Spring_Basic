package hello.core2.autowired;

import hello.core2.AutoAppConfig;
import hello.core2.discount.DiscountPolicy;
import hello.core2.member.Grade;
import hello.core2.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);


        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        
        int price = discountService.discount(member, 10000, "fixDiscountPolicy");
        int price2 = discountService.discount(member, 20000, "rateDiscountPolicy");

        Assertions.assertThat(price).isEqualTo(1000);
        Assertions.assertThat(price2).isEqualTo(2000);

    }

    static class DiscountService{

        private final Map<String, DiscountPolicy> map;
        private final List<DiscountPolicy> policies;


        @Autowired
        public DiscountService(Map<String, DiscountPolicy> map, List<DiscountPolicy> policies) {
            this.map = map;
            this.policies = policies;
            System.out.println("map = " + map);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = map.get(discountCode);
            return discountPolicy.discount(member, price);

        }
    }
}
