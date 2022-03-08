package za.co.addressing.customeraddressing.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="customer_address", uniqueConstraints = {
		@UniqueConstraint(name="duplicateCheck", columnNames =
				{ "line1", "suburb","city" }) })
public class Address
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private Long id;
	private String line1;
	private String line2;
	private String suburb;
	private String city;
	private Integer postalCode;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(
					name = "country_code"),
			@JoinColumn(
					name = "province_code")
	})
	private Province province;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_code")
	@Transient
	private Country country;

}
