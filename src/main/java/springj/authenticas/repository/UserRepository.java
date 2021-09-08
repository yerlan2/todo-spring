package springj.authenticas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springj.authenticas.model.SimpleUser;

@Repository
public interface UserRepository extends CrudRepository<SimpleUser, Integer> {
	SimpleUser findByUsername(String username);
}