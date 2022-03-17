package za.co.addressing.customeraddressing.model;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Province ID model to allow a referential integrity")
public class ProvinceId implements Serializable
{
	public String countryCode;
	public String provinceCode;

	public boolean equals(Object obj){
		ProvinceId pId = (ProvinceId) obj;
		if(this == obj) return true;
		if(pId == null || getClass() != obj.getClass())
			return false;
		return provinceCode.equals( pId.provinceCode ) &&
				this.countryCode.equals( pId.countryCode );
	}

	public int hashCode(){
		return Objects.hash(countryCode, provinceCode);
	}
}
