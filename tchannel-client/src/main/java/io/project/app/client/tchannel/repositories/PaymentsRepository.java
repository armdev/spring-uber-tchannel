package io.project.app.client.tchannel.repositories;

import io.project.app.client.tchannel.domain.Payments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends CrudRepository<Payments, String> {

}
