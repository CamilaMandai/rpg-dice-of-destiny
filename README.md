# Jogo de RPG com API Rest

Este aplicativo é um jogo de interpretação de papéis (RPG) de batalhas épicas estilo Advanced Dungeons & Dragons (AD&D). Nele, a pessoa jogadora pode escolher entre se tornar um herói ou um monstro e se envolver em duelos estratégicos em turnos contra um monstro escolhido ou aleatório. O objetivo é derrotar o oponente, reduzindo seus pontos de vida (PV) a zero.

## Fluxo do jogo:

- **Seleção de Personagem:** Escolha seu personagem favorito entre heróis e monstros, cada um com atributos únicos que afetam o desempenho em batalha.

- **Pontos de Vida (PV):** Cada personagem possui pontos de vida (PV) que representam sua resistência na batalha. O jogo termina quando um personagem fica com zero ou menos PV.

- **Iniciativa:** Descubra quem terá a iniciativa no início do jogo lançando um dado de 20 faces. O jogador com o maior resultado começará atacando.

- **Turno - Ataque e Defesa:** Cada turno é dividido em duas partes: Ataque e Defesa. Durante o Ataque, você lançará um dado de 12 faces e somará sua Força e Agilidade para determinar o poder do ataque. Na Defesa, você lançará novamente um dado de 12 faces e somará Defesa e Agilidade. Se o ataque for maior que a defesa, o dano será calculado.

- **Cálculo de Dano:** O dano causado depende primeiramente da diferença entre o ataque e a defesa calculados no item anteior. Caso a defesa seja maior que o ataque, o dano final a ser subtraído dos pontos de vida do atacado é calculado pelo valor sorteado pelos dados específicos do atacante somado à sua Força.

- **Histórico de Batalha:** Todas as batalhas são registradas em um log, incluindo informações sobre os personagens envolvidos, os dados de cada turno e outros detalhes importantes.

- **Endpoints da API:** A API oferece endpoints para realizar ações no jogo, incluindo ataques, defesas, cálculos de dano e gerenciamento de personagens (Create, Read, Update e Delete).

## Como Usar

Para começar a jogar, siga estas etapas:

1. Clone este repositório para o seu ambiente de desenvolvimento.

2. Rode o comando `docker-compose up -d` para subir os containers da aplicação

...