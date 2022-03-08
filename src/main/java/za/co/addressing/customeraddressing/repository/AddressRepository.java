package za.co.addressing.customeraddressing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.addressing.customeraddressing.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>
{
}
