package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Hello;
import study.querydsl.entity.QHello;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
@Rollback(value = false)
class QuerydslApplicationTests {

    @Autowired
    EntityManager em;

    @Test
    public void contextLoads() {
        //given
        Hello hello = new Hello();
        em.persist(hello);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QHello qHello = new QHello("h");

        //when
		Hello result = queryFactory
				.selectFrom(qHello)
				.fetchOne();

        //then
		Assertions.assertThat(result).isEqualTo(hello);
		Assertions.assertThat(result.getId()).isEqualTo(hello.getId());
    }
}
