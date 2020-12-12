package ua.kiev.sinenko.otpservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"otp_records\"")
public class OtpEntity 	implements Serializable {
	private static final long serialVersionUID = 7712500562384424795L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	@Type(type = "pg-uuid")
	private UUID id; //chellange id

	@Column(name = "recipient")
	private String recipient;

    @Column(name = "otp_value", nullable = false)
	private String otpValue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
	private Calendar createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expire_date", nullable = false)
    private Calendar expireDate;

    @Column(name = "confirmation_code")
	private String confirmationCode;

    @Column(name = "is_success")
	private Integer isSuccess ;

}