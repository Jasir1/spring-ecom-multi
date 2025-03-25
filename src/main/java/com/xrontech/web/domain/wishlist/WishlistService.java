package com.xrontech.web.domain.wishlist;

import com.xrontech.web.domain.product.ProductRepository;
import com.xrontech.web.domain.security.repos.UserRepository;
import com.xrontech.web.domain.user.UserService;
import com.xrontech.web.dto.ApplicationResponseDTO;
import com.xrontech.web.exception.ApplicationCustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public ApplicationResponseDTO addToWishlist(Long productId) {
        productRepository.findByIdAndStatus(productId,true)
                .orElseThrow(() -> new ApplicationCustomException(HttpStatus.BAD_REQUEST, "PRODUCT_NOT_FOUND", "Product not found"));

        if (wishlistRepository.findByProductIdAndUserIdAndStatus(productId, userService.getCurrentUser().getId(),true).isPresent()) {
            throw new ApplicationCustomException(HttpStatus.BAD_REQUEST, "PRODUCT_ALREADY_ADDED_TO_WISHLIST", "Product already added to wishlist");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setProductId(productId);
        wishlist.setUserId(userService.getCurrentUser().getId());
        wishlistRepository.save(wishlist);
        return new ApplicationResponseDTO(HttpStatus.CREATED, "PRODUCT_ADDED_TO_WISHLIST_SUCCESSFULLY", "Product added to wishlist successfully!");
    }

    public List<Wishlist> getAllFromWishlist() {

//        List<Wishlist> wishlistList = wishlistRepository.findAllByUserIdAndStatus(userService.getCurrentUser().getId(),true);
//        if (wishlistList.isEmpty()) {
//            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "NO_PRODUCT_FOUND_ON_WISHLIST", "No product found on wishlist");
//        }
//        return wishlistList;

        return wishlistRepository.findAllByUserIdAndStatus(userService.getCurrentUser().getId(),true);

    }

    public Wishlist getWishlistById(Long id) {

        return wishlistRepository.findByIdAndUserIdAndStatus(id, userService.getCurrentUser().getId(),true).
                orElseThrow(() -> new ApplicationCustomException(HttpStatus.NOT_FOUND, "WISHLIST_ID_NOT_FOUND", "Wishlist id not found"));
    }

    public List<Wishlist> getWishlistByProductId(Long productId) {

//        List<Wishlist> wishlistList = wishlistRepository.findByUserIdAndStatusAndProductId(userService.getCurrentUser().getId(),true,productId);
//        if (wishlistList.isEmpty()) {
//            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "NO_PRODUCT_FOUND_ON_WISHLIST", "No product found on wishlist");
//        }
//        return wishlistList;

        return wishlistRepository.findByUserIdAndStatusAndProductId(userService.getCurrentUser().getId(),true,productId);
    }

    public List<Wishlist> getWishlistByProductName(String productName) {

        List<Wishlist> wishlistList = wishlistRepository.findByUserIdAndStatusAndProduct_NameContainsIgnoreCase(userService.getCurrentUser().getId(),true,productName);
        if (wishlistList.isEmpty()) {
            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "NO_PRODUCT_FOUND_ON_WISHLIST", "No product found on wishlist");
        }
        return wishlistList;
    }

    public ApplicationResponseDTO deleteProductFromWishlist(Long id) {

        Wishlist wishlist = wishlistRepository.findByIdAndUserIdAndStatus(id, userService.getCurrentUser().getId(),true).orElseThrow(() -> new ApplicationCustomException(HttpStatus.NOT_FOUND, "WISHLIST_ID_NOT_FOUND", "Wishlist id not found"));
        wishlistRepository.delete(wishlist);
        return new ApplicationResponseDTO(HttpStatus.OK, "PRODUCT_DELETED_FROM_WISHLIST_SUCCESSFULLY", "Product deleted from wishlist successfully!");
    }

    public ApplicationResponseDTO deleteProductFromWishlistByProductId(Long id) {

        Wishlist wishlist = wishlistRepository.findByProductIdAndUserIdAndStatus(id, userService.getCurrentUser().getId(),true).orElseThrow(() -> new ApplicationCustomException(HttpStatus.NOT_FOUND, "WISHLIST_ID_NOT_FOUND", "Wishlist id not found"));
        wishlistRepository.delete(wishlist);
        return new ApplicationResponseDTO(HttpStatus.OK, "PRODUCT_DELETED_FROM_WISHLIST_SUCCESSFULLY", "Product deleted from wishlist successfully!");
    }

}
