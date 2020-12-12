package ua.kiev.sinenko.otpservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="\"access_user\"")
public class AccessUserEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "\"id\"")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "\"login\"")
    private String login;

    @Column(name = "\"password\"")
    private String password;

    @Column(name = "\"user_name\"")
    private String userName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"group_id\"")
    private AccessGroupEntity accessGroupEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"session_id\"")
    private SessionEntity sessionEntity;
}
