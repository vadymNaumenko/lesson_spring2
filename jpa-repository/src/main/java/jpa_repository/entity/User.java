package jpa_repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "User.company",
        attributeNodes = @NamedAttributeNode("company"))
@Data
@EqualsAndHashCode(of = "username")
@ToString(exclude = "company")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class User extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private LocalDate birthDate;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

//    @NotAudited
//    private List<Company> companies = new ArrayList<>();
}
