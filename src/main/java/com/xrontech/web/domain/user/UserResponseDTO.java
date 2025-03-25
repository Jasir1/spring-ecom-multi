package com.xrontech.web.domain.user;

import com.xrontech.web.domain.security.entity.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String primaryAddress;
    private String secondaryAddress;
    private String imageURL;
    private UserRole userRole;
    private Boolean status;
    private Boolean delete;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
