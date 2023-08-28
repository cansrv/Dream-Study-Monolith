package dreamgames.backendtask.monolith.controller;

import dreamgames.backendtask.monolith.model.dto.CountryLeaderBoardDto;
import dreamgames.backendtask.monolith.model.dto.ParticipantDto;
import dreamgames.backendtask.monolith.model.dto.TournamentDto;
import dreamgames.backendtask.monolith.services.TournamentService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tournament")
@RequiredArgsConstructor
public class TournamentController {

    @Resource
    private final TournamentService tournamentService;


    @GetMapping("/user-ranking")
    public Integer getUserRankingWithinGroup(@RequestParam String username) {
        return tournamentService.getUserRankingWithinGroup(username);
    }
    @GetMapping("/user-leaderboard")
    public List<ParticipantDto> getUserRankingWithinLeaderboard(@RequestParam String username) {
        return tournamentService.getUserRankingWithinLeaderboard(username);
    }
    @PatchMapping("/enter-queue")
    public TournamentDto enterTournamentQueue(@RequestParam String username) {
        return new TournamentDto(tournamentService.enterTournamentQueue(username));
    }
    @GetMapping("/country-leaderboard")
    public List<CountryLeaderBoardDto> getCountryLeaderboard() {
        return tournamentService.getCountryLeaderBoardDto();
    }
}
