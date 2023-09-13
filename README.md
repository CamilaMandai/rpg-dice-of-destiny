# DICE OF DESTINY - Jogo de RPG com API Rest

Este aplicativo é um jogo de interpretação de papéis (RPG) de batalhas épicas estilo Advanced Dungeons & Dragons (AD&D). Nele, a pessoa jogadora pode escolher entre se tornar um herói ou um monstro e se envolver em duelos estratégicos em turnos contra um monstro escolhido ou aleatório. O objetivo é derrotar o oponente, reduzindo seus pontos de vida (PV) a zero.

## Fluxo do jogo:

- **Seleção de Personagem:** Escolha seu personagem favorito entre heróis e monstros, cada um com atributos únicos que afetam o desempenho em batalha.

- **Seleção de Personagem Rival:** Escolha qualquer personagem para ser seu rival, caso não queira escolher, um monstro aleatório será escolhido por você.

- **Pontos de Vida (PV):** Cada personagem possui pontos de vida (PV) que representam sua resistência na batalha. O jogo termina quando um personagem fica com zero ou menos PV.

- **Iniciativa:** Descubra quem terá a iniciativa no início do jogo lançando um dado de 20 faces. O jogador com o maior resultado começará atacando e posteriormente os papéis serão alternados.

- **Turno - Ataque e Defesa:** Cada turno é dividido em duas partes: Ataque e Defesa. Durante o Ataque, você lançará um dado de 12 faces e somará sua Força e Agilidade para determinar o poder do ataque. Na Defesa, você lançará novamente um dado de 12 faces e somará Defesa e Agilidade. Se o ataque for maior que a defesa, o dano será calculado.

- **Cálculo de Dano:** O dano causado depende primeiramente da diferença entre o ataque e a defesa calculados no item anteior. Caso a defesa seja maior que o ataque, o dano final a ser subtraído dos pontos de vida do atacado é calculado pelo valor sorteado pelos dados específicos do atacante somado à sua Força.

- **Histórico de Batalha:** Todas as batalhas são registradas em um log, incluindo informações sobre os personagens envolvidos, informações de ataque, defesa e dano de cada turno e outros detalhes importantes.

- **Endpoints da API:** A API oferece endpoints para realizar ações no jogo, incluindo inicar batalha, ataques, defesas, cálculos de dano e gerenciamento de personagens (Create, Read, Update e Delete).


## Requisitos
- Java 17 ou acima
- Docker (docker compose)
- Git
- Maven

## Como Usar

1. Clone este repositório para o seu ambiente de desenvolvimento.

2. A aplicação utiliza banco de dados PostGreSQL, que você pode subir em ambiente 'dockerizado':
   - Rode o comando `docker-compose up -d` para subir o containers de banco

3.   Execute o projeto SpringBoot
4. O projeto estará disponivel em: http://localhost:8080/docs-rpg/swagger-ui/index.html

## Alguns endpoints que você pode chamar:

### Requisite informações de todos os Personagens

```
http://localhost:8080/api/characters
```

### Busque personagens por nome ou parte do nome

```
http://localhost:8080/characters/search?name=eiro

Response: HTTP 200
Content: Character List
``` 

### Crie um novo personagem

```
POST /api/characters
Accept: application/json
Content-Type: application/json

{
  "name": "Meu Nome",
  "lifePoints": 20,
  "strength": 5,
  "defense": 7,
  "agility": 5,
  "diceQuantity": 1,
  "diceSides": 12,
  "personality": "HERO"
}

RESPONSE: HTTP 201 (Created)
Location header:  location: http://localhost:8080/characters/1
```

### Recupere uma lista paginada de batalhas

```
http://localhost:8080/battles?page=0&size=10

Response: HTTP 200
Content: paginated list 
``` 


