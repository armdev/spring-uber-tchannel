package io.project.app.server.tchannel.repositories;

import io.project.app.server.tchannel.domain.Payments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends CrudRepository<Payments, String> {

}
