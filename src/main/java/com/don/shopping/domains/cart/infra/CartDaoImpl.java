package com.don.shopping.domains.cart.infra;

import com.don.shopping.domains.cart.query.dao.CartDao;
import com.don.shopping.domains.cart.query.dto.CartLineDto;
import com.don.shopping.domains.product.domain.ImageUsage;
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
//com.don.shopping.domains.cart.query.dto.
    @Override
    public List<CartLineDto> getCartLineDtoList(Long userId) {
        StringBuilder sb = new StringBuilder();
        sb
            .append("SELECT")
            .append(" new com.don.shopping.domains.cart.query.dto.CartLineDto")
            .append("(p.id, pi.filePath, p.productName, p.rprice, p.dprice, cl.orderAmount, p.stock)")
            .append(" FROM CartEntity c")
            .append(" JOIN c.cart cl")
            .append(" ON c.cartId = cl.cartId")
            .append(" JOIN ProductEntity p")
            .append(" ON cl.productId = p.id")
            .append(" JOIN ProductImageEntity pi")
            .append(" ON p.id = pi.product.id")
            .append(" WHERE c.userId = :userId")
            .append(" AND pi.imageUsage = :imageUsage");

        List<CartLineDto> cartLineDtoList =
                em
                    .createQuery(sb.toString(), CartLineDto.class)
                    .setParameter("userId", userId)
                    .setParameter("imageUsage", ImageUsage.THUMBNAIL1)
                    .getResultList();

        return cartLineDtoList;
    }
}
