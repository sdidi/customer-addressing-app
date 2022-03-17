package za.co.addressing.customeraddressing.model;

import io.swagger.annotations.ApiModel;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Country model with all details")
public class Country
{
	@Id
	@Column(name="country_code")
	private String code;
	@Column(name="country_name")
	private String name;

	@Override
	public String toString()
	{
		return name ;
	}

}
