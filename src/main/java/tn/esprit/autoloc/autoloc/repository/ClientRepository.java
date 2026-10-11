package tn.esprit.autoloc.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.autoloc.domain.Client;

public interface ClientRepository extends JpaRepository<Client , Long> {
}
