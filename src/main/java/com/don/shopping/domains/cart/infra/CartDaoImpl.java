package com.don.shopping.domains.cart.infra;

import com.don.shopping.domains.cart.query.dao.CartDao;
import com.don.shopping.domains.cart.query.dto.CartLineDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    private EntityManager em;
    private JPAQueryFactory query;

    public CartDaoImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory((em));
    }

    @Override
    public List<CartLineDto> getCartLineDtoList(Long userId) {
        StringBuilder sb = new StringBuilder();
        sb
            .append("SELECT")
            .append(" new com.don.shopping.domains.cart.query.dto.CartLineDTO")
            .append("(p.productId, p.filePath, p.productname, p.rprice, p.dprice, cl.orderAmount, p.stock")
            .append(" FROM CartEntity c")
            .append(" JOIN c.cart cl")
            .append(" ON cl.productId = p.productId")
            .append(" WHERE c.userId = :userId");

        List<CartLineDto> cartLineDtoList =
                em
                    .createQuery(sb.toString(), CartLineDto.class)
                    .setParameter("userId", userId)
                    .getResultList();

        return cartLineDtoList;
    }
}
