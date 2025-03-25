package com.xrontech.web;

import com.xrontech.web.domain.category.Category;
import com.xrontech.web.domain.category.CategoryRepository;
import com.xrontech.web.domain.product.Condition;
import com.xrontech.web.domain.product.Product;
import com.xrontech.web.domain.product.ProductRepository;
import com.xrontech.web.domain.security.entity.User;
import com.xrontech.web.domain.security.entity.UserRole;
import com.xrontech.web.domain.security.repos.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ECom API", version = "2.0", description = "ECom"))
@SecurityScheme(name = "ecom", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class SpringEcomApplication {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringEcomApplication.class, args);
    }


    @PostConstruct
    public void init(){

        if (userRepository.findByEmail("abc@gmail.com").isEmpty()){
            userRepository.save(
                    User.builder()
                            .name("abc")
                            .email("abc@gmail.com")
                            .mobile("0762652448")
                            .password(passwordEncoder.encode("1234"))
                            .status(true)
                            .delete(false)
                            .userRole(UserRole.ADMIN)
                            .build()
            );
        }

        if (userRepository.findByEmail("xyz@gmail.com").isEmpty()){
            userRepository.save(
                    User.builder()
                            .name("xyz")
                            .email("xyz@gmail.com")
                            .mobile("0761273648")
                            .password(passwordEncoder.encode("1234"))
                            .status(true)
                            .delete(false)
                            .userRole(UserRole.USER)
                            .build()
            );
        }

        categoryRepository.findByStatusAndNameIgnoreCase(true,"Laptop").orElseGet(()-> categoryRepository.save(
                Category.builder()
                        .name("Laptop").status(true).build()
        ));

        categoryRepository.findByStatusAndNameIgnoreCase(true,"Mobile phone").orElseGet(()-> categoryRepository.save(
                Category.builder()
                        .name("Mobile phone").status(true).build()
        ));

        categoryRepository.findByStatusAndNameIgnoreCase(true,"Comp Accessories").orElseGet(()-> categoryRepository.save(
                Category.builder()
                        .name("Comp Accessories").status(true).build()
        ));

        categoryRepository.findByStatusAndNameIgnoreCase(true,"Computer").orElseGet(()-> categoryRepository.save(
                Category.builder()
                        .name("Computer").status(true).build()
        ));

        Long adminId = userRepository.findByEmail("abc@gmail.com").get().getId();
        Long userId = userRepository.findByEmail("xyz@gmail.com").get().getId();

        productRepository.findByNameIgnoreCase("Asus Tuf A15 2021").orElseGet(()-> productRepository.save(
                Product.builder()
                        .name("Asus Tuf A15 2021")
                        .description("Asus Tuf A15 2021 | 16GB | 256GB SSD | 1TB HDD | Black ")
                        .imagePath("uwqbyqwtufqwufnqifuhqyudgqhbfqoihoeqw")
                        .qty(3)
                        .price(210000.00)
                        .status(true)
                        .delete(false)
                        .condition(Condition.BRANDNEW)
                        .categoryId(10002L)
                        .userId(adminId)
                        .build()
        ));

        productRepository.findByNameIgnoreCase("iPhone 12 Pro").orElseGet(()-> productRepository.save(
                Product.builder()
                        .name("iPhone 12 Pro")
                        .description("iPhone 12 Pro | 6GB | 256GB | 12MP | Black ")
                        .imagePath("tervfuiasnfasifuaoisfnasfaisufhasnfamf")
                        .qty(3)
                        .price(210000.00)
                        .status(true)
                        .delete(false)
                        .condition(Condition.USED)
                        .categoryId(10003L)
                        .userId(userId)
                        .build()
        ));

    }
}
