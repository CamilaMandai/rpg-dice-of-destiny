package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.AttackReturnDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.CreatedBattleDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.DamageReturnDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.service.dto.DefenseReturnDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.gameDTO.MoveRequestDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.gameDTO.PlayRequestDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.exception.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {
        "/sql/character/character-insert.sql",
        "/sql/character/battle-insert.sql",
        "/sql/character/turn-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/sql/character/turn-delete.sql",
        "/sql/character/battle-delete.sql",
        "/sql/character/character-delete.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GameIT {
    @Autowired
    WebTestClient testClient;

    @Test
    public void playGame_withValidData_returnStatus201() {
        PlayRequestDTO playRequestDTO =
                new PlayRequestDTO(
                        "Eduardo",
                        2L, 6L);
        CreatedBattleDTO responseBody = testClient
                .post()
                .uri("/game/play")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(playRequestDTO)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(CreatedBattleDTO.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.battle().getId()).isNotNull();
        Assertions.assertThat(responseBody.battle().getPlayerName()).isEqualTo("Eduardo");
        Assertions.assertThat(responseBody.diceBot()).isNotNull();
        Assertions.assertThat(responseBody.dicePlayer()).isNotNull();
    }
    @Test
    public void playGame_withNonExistingBotCharacterId_returnStatus404() {
        PlayRequestDTO playRequestDTO =
                new PlayRequestDTO(
                        "Eduardo",
                        2L, 60L);
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/play")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(playRequestDTO)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }
    @Test
    public void playGame_withNonExistingPlayerCharacterId_returnStatus404() {
        PlayRequestDTO playRequestDTO =
                new PlayRequestDTO(
                        "Eduardo",
                        25L, 6L);
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/play")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(playRequestDTO)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }
    @Test
    public void attackMove_withValidData_returnStatus200() {
        AttackReturnDTO responseBody = testClient
                .post()
                .uri("/game/attack")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(1L, 1))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AttackReturnDTO.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.attack()).isEqualTo(24);
        Assertions.assertThat(responseBody.diceResult()).isNotNull();
        Assertions.assertThat(responseBody.attacker()).isEqualTo("Guerreiro");
    }
    @Test
    public void attackMove_withTooHighRound_returnStatus404GameOver() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/attack")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(1L, 6))
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getMessage()).isEqualTo("Game over at round 5");
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }
    @Test
    public void attackMove_withInvalidBattleId_returnStatus404() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/attack")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(2L, 5))
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }
    @Test
    public void attackMove_InvalidBattleData_returnStatus422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/attack")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(0L, 5))
                .exchange()
                .expectStatus()
                .isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }
    @Test
    public void attackMove_InvalidRoundData_returnStatus422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/attack")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(1L, 0))
                .exchange()
                .expectStatus()
                .isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }
    @Test
    public void defenseMove_withValidData_returnStatus200() {
        DefenseReturnDTO responseBody = testClient
                .post()
                .uri("/game/defense")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(1L, 1))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(DefenseReturnDTO.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.diceResult()).isNotNull();
        Assertions.assertThat(responseBody.defense()).isEqualTo(12);
        Assertions.assertThat(responseBody.defender()).isEqualTo("Orc");
    }
    @Test
    public void defenseMove_withTooHighRound_returnStatus404GameOver() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/defense")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(1L, 6))
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getMessage()).isEqualTo("Game over at round 5");
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }
    @Test
    public void defenseMove_withInvalidBattleId_returnStatus404() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/defense")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(2L, 5))
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }
    @Test
    public void defenseMove_InvalidBattleData_returnStatus422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/defense")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(0L, 5))
                .exchange()
                .expectStatus()
                .isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }
    @Test
    public void defenseMove_InvalidRoundData_returnStatus422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/defense")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(1L, 0))
                .exchange()
                .expectStatus()
                .isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }
    @Test
    public void damageMove_withValidData_returnStatus200() {
        DamageReturnDTO responseBody = testClient
                .post()
                .uri("/game/calculate-damage")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(1L, 1))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(DamageReturnDTO.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.diceResult()).isNotNull();
        Assertions.assertThat(responseBody.damage()).isEqualTo(16);
        Assertions.assertThat(responseBody.attacker()).isEqualTo("Guerreiro");
    }
    @Test
    public void damageMove_withTooHighRound_returnStatus404GameOver() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/calculate-damage")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(1L, 6))
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getMessage()).isEqualTo("Game over at round 5");
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }
    @Test
    public void damageMove_withInvalidBattleId_returnStatus404() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/calculate-damage")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(2L, 5))
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }
    @Test
    public void damageMove_InvalidBattleData_returnStatus422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/calculate-damage")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(0L, 5))
                .exchange()
                .expectStatus()
                .isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }
    @Test
    public void damageMove_InvalidRoundData_returnStatus422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/game/calculate-damage")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MoveRequestDTO(1L, 0))
                .exchange()
                .expectStatus()
                .isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }
}
