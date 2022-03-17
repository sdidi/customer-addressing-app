package za.co.addressing.customeraddressing.model;

import io.swagger.annotations.*;
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
@ApiModel(description = "Address model with all details")
public class Address
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	@ApiModelProperty(notes ="The unique id of the address")
	private Long id;
	@ApiModelProperty(notes =" apartment/house/complex number of the address")
	private String line1;
	@ApiModelProperty(notes =" street / road number of the address")
	private String line2;
	@ApiModelProperty(notes =" surburb name of the address")
	private String suburb;
	@ApiModelProperty(notes =" city in which the address is in")
	private String city;
	@ApiModelProperty(notes =" postal code of the address")
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
