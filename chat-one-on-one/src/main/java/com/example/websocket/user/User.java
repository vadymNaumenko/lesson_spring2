package com.example.websocket.user;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    public enum Role {
        STUDENT, TEACHER, ADMIN;

        public static boolean contains(String value) {
            for (Role role : values()) {
                if (role.name().equalsIgnoreCase(value)) {
                    return true;
                }
            }
            return false;
        }
    }
    public enum Status {
        ONLINE, OF_LINE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private String firstName;

    private String lastName;
}
