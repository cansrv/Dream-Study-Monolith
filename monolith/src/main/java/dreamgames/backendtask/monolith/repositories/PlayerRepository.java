package dreamgames.backendtask.monolith.repositories;

import dreamgames.backendtask.monolith.model.entity.Player;
import dreamgames.backendtask.monolith.model.enums.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

        public boolean existsByUsername(String username);
        public void deleteByUsername(String username);
        public Player findByUsername(String username);

        public Player findByCountryAndAndIsCompetingIsFalse(Country country);
}
