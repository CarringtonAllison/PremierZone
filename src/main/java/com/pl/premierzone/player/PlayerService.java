package com.pl.premierzone.player;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    };

    public Optional<Player> getPlayerByName(String player_name) {
        return playerRepository.findByPlayer_name(player_name);
    };

    public List<Player> getPlayersByTeam(String team) {
        return playerRepository.findAll().stream()
                .filter(player -> Objects.equals(player.getTeam_name(), team))
                .collect(Collectors.toList());
    };

    public List<Player> getPlayersByPosition(String position) {
        return playerRepository.findAll().stream()
                .filter(player -> player.getPos().contains(position))
                .collect(Collectors.toList());
    };

    public List<Player> getPlayersByNation(String nation) {
      return playerRepository.findAll().stream()
              .filter(player -> player.getNation().equals(nation))
              .collect(Collectors.toList());
    };

    public List<Player> getPlayersByTeamAndPosition(String team, String position) {
        return playerRepository.findAll().stream()
                .filter(player -> team.equals(player.getTeam_name()) && position.equals(player.getPos()))
                .collect(Collectors.toList());
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Player player) {
        Optional<Player> existingPlayer = playerRepository.findByPlayer_name(player.getPlayer_name());

        if (existingPlayer.isPresent()) {
            existingPlayer.get().setPlayer_name(player.getPlayer_name());
            existingPlayer.get().setTeam_name(player.getTeam_name());
            existingPlayer.get().setNation(player.getNation());
            existingPlayer.get().setPos(player.getPos());

            playerRepository.save(existingPlayer.get());
            return existingPlayer.get();
        }
        return null;
    }

    @Transactional
    public void deletePlayer(String player) {
        playerRepository.deleteByPlayer_name(player);
    }
}
