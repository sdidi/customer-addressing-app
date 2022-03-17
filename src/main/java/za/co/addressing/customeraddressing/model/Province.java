package za.co.addressing.customeraddressing.model;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass( ProvinceId.class )
@ApiModel(description = "Province model with all details")
public class Province implements Serializable
{
	@Id
	@Column(name="province_code")
	private String provinceCode;
	@Id
	@Column(name="country_code")
	private String countryCode;
	@Column(name="province_name")
	private String name;

	@Override
	public String toString()
	{
		return "Province{" +
				"provinceCode='" + provinceCode + '\'' +
				", countryCode='" + countryCode + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
