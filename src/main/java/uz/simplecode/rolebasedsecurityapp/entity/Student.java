package uz.simplecode.rolebasedsecurityapp.entity;

import lombok.*;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "STUDENT")
public class Student {

    @Getter
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )}
    )
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;
    @Column(name = "FIRST_NAME",nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME",nullable = false)
    private String lastName;
    @Column(name = "PHONE_NUMBER",unique = true)
    private String phoneNumber;
    @ManyToOne(optional = false)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    public Student(String firstName, String lastName, String phoneNumber,Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.group=group;
    }
}
