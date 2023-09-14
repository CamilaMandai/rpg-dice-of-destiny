package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.model.Character;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.characterDTO.CharacterCreateDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.characterDTO.CharacterResponseDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.dto.characterDTO.CharacterUpdateDTO;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.exception.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/character/character-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/character/character-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CharacterIT {
    @Autowired
    WebTestClient testClient;

    @Test
    public void createCharacter_withValidData_returnStatus201() {
        CharacterCreateDTO characterDTO =
                new CharacterCreateDTO(
                        "Sereia",
                        20L,
                        10L,
                        5L,
                        7L,
                        2,
                        6,Character.Personality.HERO);
        CharacterResponseDTO responseBody = testClient
                .post()
                .uri("/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(characterDTO)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(CharacterResponseDTO.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isNotNull();
        Assertions.assertThat(responseBody.getName()).isEqualTo(characterDTO.getName());
        Assertions.assertThat(responseBody.getLifePoints()).isEqualTo(characterDTO.getLifePoints());
        Assertions.assertThat(responseBody.getStrength()).isEqualTo(characterDTO.getStrength());
        Assertions.assertThat(responseBody.getDefense()).isEqualTo(characterDTO.getDefense());
        Assertions.assertThat(responseBody.getAgility()).isEqualTo(characterDTO.getAgility());
        Assertions.assertThat(responseBody.getDice()).isEqualTo("2d6");
        Assertions.assertThat(responseBody.getPersonality()).isEqualTo(characterDTO.getPersonality());
    }
    @Test
    public void createCharacter_withInvalidLifePoints_returnErrorStatus422() {
        CharacterCreateDTO characterDTO =
                new CharacterCreateDTO(
                        "Sereia",
                        0L,
                        10L,
                        5L,
                        7L,
                        2,
                        6,Character.Personality.HERO);
        ErrorMessage responseBody = testClient
                .post()
                .uri("/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(characterDTO)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }

    @Test
    public void createCharacter_withInvalidStrength_returnErrorStatus422() {
        CharacterCreateDTO characterDTO =
                new CharacterCreateDTO(
                        "Sereia",
                        20L,
                        0L,
                        5L,
                        7L,
                        2,
                        6,Character.Personality.HERO);
        ErrorMessage responseBody = testClient
                .post()
                .uri("/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(characterDTO)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }

    @Test
    public void createCharacter_withInvalidDefense_returnErrorStatus422() {
        CharacterCreateDTO characterDTO =
                new CharacterCreateDTO(
                        "Sereia",
                        50L,
                        10L,
                        0L,
                        7L,
                        2,
                        6,Character.Personality.HERO);
        ErrorMessage responseBody = testClient
                .post()
                .uri("/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(characterDTO)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }

    @Test
    public void createCharacter_withInvalidAgility_returnErrorStatus422() {
        CharacterCreateDTO characterDTO =
                new CharacterCreateDTO(
                        "Sereia",
                        50L,
                        10L,
                        10L,
                        0L,
                        2,
                        6,Character.Personality.HERO);
        ErrorMessage responseBody = testClient
                .post()
                .uri("/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(characterDTO)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }

    @Test
    public void createCharacter_withInvalidDiceQty_returnErrorStatus422() {
        CharacterCreateDTO characterDTO =
                new CharacterCreateDTO(
                        "Sereia",
                        20L,
                        10L,
                        5L,
                        7L,
                        0,
                        6,Character.Personality.HERO);
        ErrorMessage responseBody = testClient
                .post()
                .uri("/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(characterDTO)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }

    @Test
    public void createCharacter_withInvalidDiceSides_returnErrorStatus422() {
        CharacterCreateDTO characterDTO =
                new CharacterCreateDTO(
                        "Sereia",
                        50L,
                        10L,
                        10L,
                        7L,
                        2,
                        3,Character.Personality.HERO);
        ErrorMessage responseBody = testClient
                .post()
                .uri("characters")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(characterDTO)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }
    @Test
    public void createCharacter_withInvalidName_returnErrorStatus422() {
        CharacterCreateDTO characterDTO =
                new CharacterCreateDTO(
                        "",
                        20L,
                        7L,
                        5L,
                        6L,
                        1,
                        12,
                        Character.Personality.HERO);
        ErrorMessage responseBody = testClient
                .post()
                .uri("/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(characterDTO)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }
    @Test
    public void getCharacter_withValidId_returnStatus200() {
        CharacterResponseDTO responseBody = testClient
                .get()
                .uri("/characters/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CharacterResponseDTO.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isEqualTo(1);
        Assertions.assertThat(responseBody.getName()).isEqualTo("Guerreiro");
        Assertions.assertThat(responseBody.getLifePoints()).isEqualTo(20L);
        Assertions.assertThat(responseBody.getStrength()).isEqualTo(7L);
        Assertions.assertThat(responseBody.getDefense()).isEqualTo(5);
        Assertions.assertThat(responseBody.getAgility()).isEqualTo(6);
        Assertions.assertThat(responseBody.getDice()).isEqualTo("1d12");
        Assertions.assertThat(responseBody.getPersonality()).isEqualTo(Character.Personality.HERO);
    }
    @Test
    public void getCharacter_withNonExistingId_returnStatus404() {
        ErrorMessage responseBody = testClient
                .get()
                .uri("/characters/100")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }
    @Test
    public void getAllCharacter_returnStatus200() {
        List<CharacterResponseDTO> responseBody = testClient
                .get()
                .uri("/characters")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<CharacterResponseDTO>>() {})
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.get(0)).isNotNull();
        Assertions.assertThat(responseBody.get(0).getName()).isEqualTo("Guerreiro");
        Assertions.assertThat(responseBody.get(0).getLifePoints()).isEqualTo(20L);
        Assertions.assertThat(responseBody.get(0).getStrength()).isEqualTo(7L);
        Assertions.assertThat(responseBody.get(0).getDefense()).isEqualTo(5);
        Assertions.assertThat(responseBody.get(0).getAgility()).isEqualTo(6);
        Assertions.assertThat(responseBody.get(0).getDice()).isEqualTo("1d12");
        Assertions.assertThat(responseBody.get(0).getPersonality()).isEqualTo(Character.Personality.HERO);
    }
    @Test
    public void editCharacter_withValidData_returnStatus200() {
        CharacterUpdateDTO characterDTO =
                new CharacterUpdateDTO(
                        20L,
                        5L,
                        10L,
                        7L,
                        2,
                        12,Character.Personality.HERO);
        CharacterResponseDTO responseBody = testClient
                .put()
                .uri("/characters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(characterDTO)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CharacterResponseDTO.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isNotNull();
        Assertions.assertThat(responseBody.getName()).isEqualTo("Guerreiro");
        Assertions.assertThat(responseBody.getLifePoints()).isEqualTo(characterDTO.getLifePoints());
        Assertions.assertThat(responseBody.getStrength()).isEqualTo(characterDTO.getStrength());
        Assertions.assertThat(responseBody.getDefense()).isEqualTo(characterDTO.getDefense());
        Assertions.assertThat(responseBody.getAgility()).isEqualTo(characterDTO.getAgility());
        Assertions.assertThat(responseBody.getDice()).isEqualTo("2d12");
        Assertions.assertThat(responseBody.getPersonality()).isEqualTo(characterDTO.getPersonality());
    }
    @Test
    public void editCharacter_withInValidData_returnStatus422() {
        CharacterUpdateDTO characterDTO =
                new CharacterUpdateDTO(
                        0L,
                        5L,
                        10L,
                        7L,
                        2,
                        12,Character.Personality.HERO);
        ErrorMessage responseBody = testClient
                .put()
                .uri("/characters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(characterDTO)
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
    public void deleteCharacter_withValidId_returnStatus204() {
        testClient
                .delete()
                .uri("/characters/1")
                .exchange()
                .expectStatus().isEqualTo(204);
    }
    @Test
    public void deleteCharacter_withInValidId_returnStatus404() {
        ErrorMessage responseBody = testClient
                .delete()
                .uri("/characters/100")
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }
}