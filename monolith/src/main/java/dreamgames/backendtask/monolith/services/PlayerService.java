package dreamgames.backendtask.monolith.services;

import dreamgames.backendtask.monolith.exceptions.NoAwardAvailableException;
import dreamgames.backendtask.monolith.exceptions.PlayerAlreadyExistsException;
import dreamgames.backendtask.monolith.model.dto.CountryLeaderBoardDto;
import dreamgames.backendtask.monolith.model.entity.Participant;
import dreamgames.backendtask.monolith.model.entity.Player;
import dreamgames.backendtask.monolith.model.enums.Award;
import dreamgames.backendtask.monolith.model.enums.Country;
import dreamgames.backendtask.monolith.repositories.ParticipantRepository;
import dreamgames.backendtask.monolith.repositories.PlayerRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class PlayerService {

    @Resource
    private final PlayerRepository playerRepository;
    @Resource
    private final ParticipantRepository participantRepository;
    private static int germanyPoints = 0;
    private static int unitedStatesPoints = 0;
    private static int turkeyPoints = 0;
    private static int unitedKingdomPoints = 0;
    private static int francePoints = 0;

    public boolean existsByUsername(String username) {
        return playerRepository.existsByUsername(username);
    }

    public Player findByUsername(String username) {
        return playerRepository.findByUsername(username);
    }


    public Player createPlayer(String username) {

        if (existsByUsername(username)) {
            log.error("Player with username {} already exists", username);
            throw new PlayerAlreadyExistsException(username);
        }

        Player player = new Player();
        player.setUsername(username);
        player.setCoin(5000);
        player.setLevel(1);
        player.setIsCompeting(false);
        player.setAward(Award.NO_AWARD);
        player.setCountry(Country.getRandomCountry());
        return playerRepository.save(player);
    }

    public Player levelUp(String username) {
        Player player = playerRepository.findByUsername(username);
        player.setLevel(player.getLevel() + 1);
        player.setCoin(player.getCoin() + 25);
        if(player.getIsCompeting()) {
            Participant participant = participantRepository.findParticipantByPlayer_Username(username);
            participant.setScore(participant.getScore() + 1);
            participantRepository.save(participant);
            setCountryPoint(player);
        }
        return playerRepository.save(player);

    }

    public void enterTournamentQueue(String username) {
        Player player = playerRepository.findByUsername(username);
        player.setWantsToCompete(true);
        player.setCoin(player.getCoin() - 1000);
        playerRepository.save(player);
    }

    public Player claimReward(String username) {
        Player player = playerRepository.findByUsername(username);
        if(player.getAward().equals(Award.NO_AWARD)) {
            log.error("Player with username {} has no award to claim", username);
            throw new NoAwardAvailableException(username);
        }
        else if(player.getAward().equals(Award.FIRST_PLACE)) {
            player.setCoin(player.getCoin() + 10000);
        }
        else {
            player.setCoin(player.getCoin() + 5000);
        }
        player.setAward(Award.NO_AWARD);
        return playerRepository.save(player);
    }

    public void save(Player player) {
        playerRepository.save(player);
    }
    public void saveAll(Iterable<Player> players) {
        playerRepository.saveAll(players);
    }

    private static void setCountryPoint(Player player) {
        if (player.getCountry().equals(Country.GERMANY)) {
            germanyPoints += 1;
        } else if (player.getCountry().equals(Country.UNITED_STATES)) {
            unitedStatesPoints += 1;
        } else if (player.getCountry().equals(Country.TURKEY)) {
            turkeyPoints += 1;
        } else if (player.getCountry().equals(Country.UNITED_KINGDOM)) {
            unitedKingdomPoints += 1;
        } else {
            francePoints += 1;
        }
    }

    public void resetCountryPoints() {
        germanyPoints = 0;
        unitedStatesPoints = 0;
        turkeyPoints = 0;
        unitedKingdomPoints = 0;
        francePoints = 0;
    }

    public List<CountryLeaderBoardDto> getCountryLeaderBoard() {
        List<CountryLeaderBoardDto> countryList = new java.util.ArrayList<>(List.of(
                new CountryLeaderBoardDto(Country.GERMANY, germanyPoints),
                new CountryLeaderBoardDto(Country.UNITED_STATES, unitedStatesPoints),
                new CountryLeaderBoardDto(Country.TURKEY, turkeyPoints),
                new CountryLeaderBoardDto(Country.UNITED_KINGDOM, unitedKingdomPoints),
                new CountryLeaderBoardDto(Country.FRANCE, francePoints)
        ));
        countryList.sort((c1, c2) -> c2.getScore() - c1.getScore());
        return countryList;
    }
}
