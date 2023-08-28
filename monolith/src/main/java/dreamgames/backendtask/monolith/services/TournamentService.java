package dreamgames.backendtask.monolith.services;

import dreamgames.backendtask.monolith.exceptions.leaderboard.UserNotCompetingException;
import dreamgames.backendtask.monolith.model.dto.CountryLeaderBoardDto;
import dreamgames.backendtask.monolith.model.dto.ParticipantDto;
import dreamgames.backendtask.monolith.model.dto.PlayerDto;
import dreamgames.backendtask.monolith.model.entity.Participant;
import dreamgames.backendtask.monolith.model.entity.Player;
import dreamgames.backendtask.monolith.model.entity.Tournament;
import dreamgames.backendtask.monolith.model.enums.Award;
import dreamgames.backendtask.monolith.model.enums.Country;
import dreamgames.backendtask.monolith.repositories.ParticipantRepository;
import dreamgames.backendtask.monolith.repositories.TournamentRepository;
import dreamgames.backendtask.monolith.utils.VerificationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class TournamentService {
    private final PlayerService playerService;
    private final TournamentRepository tournamentRepository;
    private final ParticipantRepository participantRepository;

    public Tournament enterTournamentQueue(String username) {
        VerificationUtil.verifyTournamentTime();
        Player player = playerService.findByUsername(username);
        PlayerDto playerDto = new PlayerDto(player);
        VerificationUtil.verifyTournamentConditions(playerDto);
        playerService.enterTournamentQueue(username);
        return startTournament(player);
    }

    public Tournament getTournament(String username) {
        Participant participant = participantRepository.findParticipantByPlayer_Username(username);
        return tournamentRepository.findTournamentByParticipantsContaining(participant);
    }

    private Tournament createTournament(Player player) {
        Tournament tournament = new Tournament();
        tournament.setStarted(false);
        Participant participant = new Participant();
        participant.setPlayer(player);
        participant.setScore(0);
        tournament.setParticipants(new ArrayList<>(List.of(participant)));
        return tournamentRepository.save(tournament);
    }

    private Tournament startTournament(Player player) {
        List<Tournament> tournaments = tournamentRepository.findAllByStartedIsFalse();
        for (Tournament tournament : tournaments) {
            List<Participant> participants = tournament.getParticipants();
            // get players from participants
            List<Player> players = participants.stream().map(Participant::getPlayer).toList();
            // get player countries
            List<Country> countries = players.stream().map(Player::getCountry).toList();
            if(!countries.contains(player.getCountry())) {
                // start tournament
                Participant participant = new Participant();
                participant.setPlayer(player);
                participant.setScore(0);
                participants.add(participant);
                tournament.setParticipants(participants);
                if(participants.size() == 5) {
                    tournament.setStarted(true);
                    // Set players to competing
                    for (Player p : players) {
                        p.setIsCompeting(true);
                    }
                    playerService.saveAll(players);
                }
                return tournamentRepository.save(tournament);

            }
        }
        return createTournament(player);
    }

    @Scheduled(cron = "0 20 * * *")
    private void endTournament() {
        List<Tournament> tournaments = tournamentRepository.findAllByStartedIsTrue();
        for (Tournament tournament : tournaments) {
            List<Participant> participants = tournament.getParticipants();
            participants.sort((p1, p2) -> p2.getScore() - p1.getScore());
            participants.get(0).getPlayer().setAward(Award.FIRST_PLACE);
            participants.get(1).getPlayer().setAward(Award.SECOND_PLACE);
            for (Participant participant : participants) {
                Player player = participant.getPlayer();
                player.setIsCompeting(false);
                player.setWantsToCompete(false);
                playerService.save(player);
                participant.setPlayer(null);
            }
            tournamentRepository.delete(tournament);
        }
        playerService.resetCountryPoints();
    }

    public Integer getUserRankingWithinGroup(String username) {
        Participant participant = participantRepository.findParticipantByPlayer_Username(username);
        if(participant == null) {
            log.error("Player with username {} is not competing.", username);
            throw new UserNotCompetingException(username);
        }
        Tournament tournament = tournamentRepository.findTournamentByParticipantsContaining(participant);
        List<Participant> participants = tournament.getParticipants();
        participants.sort((p1, p2) -> p2.getScore() - p1.getScore());
        return participants.indexOf(participant) + 1;
    }

    public List<ParticipantDto> getUserRankingWithinLeaderboard(String username) {
        Participant participant = participantRepository.findParticipantByPlayer_Username(username);
        if(participant == null) {
            log.error("Player with username {} is not competing.", username);
            throw new UserNotCompetingException(username);
        }
        Tournament tournament = tournamentRepository.findTournamentByParticipantsContaining(participant);
        List<Participant> participants = tournament.getParticipants();
        participants.sort((p1, p2) -> p2.getScore() - p1.getScore());
        List<ParticipantDto> participantDtos = new ArrayList<>();
        for (Participant p : participants) {
            participantDtos.add(new ParticipantDto(p));
        }
        return participantDtos;
    }
    public List<CountryLeaderBoardDto> getCountryLeaderBoardDto() {
        return playerService.getCountryLeaderBoard();
    }
}
