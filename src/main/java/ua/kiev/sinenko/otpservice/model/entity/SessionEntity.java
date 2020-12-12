package ua.kiev.sinenko.otpservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"session\"")
public class SessionEntity implements Serializable {
    private static final long serialVersionUID = -4662534500806236829L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    @Type(type = "pg-uuid")
    private UUID id; //session id

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"start_time\"")
    private Calendar startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"expire_time\"")
    private Calendar expireTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "\"update_time\"")
    private Calendar updateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AccessUserEntity accessUserEntity;
}
