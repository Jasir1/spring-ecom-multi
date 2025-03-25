package com.xrontech.web.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByNameIgnoreCase(String name);
    List<Product> findByStatusAndDeleteAndCategoryId(boolean status,boolean deleted, Long categoryId);
    List<Product> findByStatusAndDeleteAndCategoryIdAndIdNot(boolean status,boolean deleted, Long categoryId, Long productId);
    List<Product> findByDeleteAndUserIdAndCategoryId(boolean deleted,Long userId, Long categoryId);
    Optional<Product> findByIdAndStatus(Long id, boolean status);
    Optional<Product> findByIdAndStatusAndDelete(Long id, boolean status, boolean deleted);
    Optional<Product> findByIdAndUserIdAndStatusAndDelete(Long id,Long userId, boolean status,boolean deleted);
    List<Product> findByUserIdAndDeleteAndCondition(Long userId, boolean deleted,Condition condition);
    Optional<Product> findByIdAndUserIdAndDelete(Long id,Long userId,boolean deleted);
    List<Product> findByStatusAndDeleteAndNameContainsIgnoreCase(boolean status,boolean deleted, String name);
    List<Product> findByUserIdAndDeleteAndNameContainsIgnoreCase(Long userId,boolean deleted, String name);
    List<Product> findAllByStatusAndDelete(boolean status,boolean deleted);
    List<Product> findAllByStatusAndDeleteOrderByCreatedAt(boolean status,boolean deleted);
//    List<Product> findByIdAndQty(Long productId,int qty);
    List<Product> findAllByUserIdAndDelete(Long userId,boolean deleted);
    Optional<Product> findFirstByUserIdAndDeleteOrderByIdDesc(Long userId,boolean deleted);
//    Optional<Product> findByUserIdAndDeleteOrderByIdDesc(Long userId,boolean deleted);
    List<Product> findByStatusAndDeleteAndCondition(boolean status, boolean deleted, Condition condition);

    List<Product> findByUserIdAndDeleteAndStatus(Long id, boolean deleted, boolean status);
}
