package dreamgames.backendtask.monolith.controller;

import dreamgames.backendtask.monolith.model.dto.PlayerDto;
import dreamgames.backendtask.monolith.services.PlayerService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {
    @Resource
    private final PlayerService playerService;
    @GetMapping
    public PlayerDto getPlayer(@RequestParam String username) {
        return new PlayerDto(playerService.findByUsername(username));
    }
    @PostMapping
    public PlayerDto createPlayer(@RequestParam String username) {
        return new PlayerDto(playerService.createPlayer(username));
    }
    @PatchMapping("/levelUp")
    public PlayerDto levelUp(@RequestParam String username) {
        return new PlayerDto(playerService.levelUp(username));
    }

    @PatchMapping("/claimReward")
    public PlayerDto claimReward(@RequestParam String username) {
        return new PlayerDto(playerService.claimReward(username));
    }
}
