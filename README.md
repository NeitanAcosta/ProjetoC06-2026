# 🎬 SmartCine — Sistema de Gerenciamento de Cinema

> Projeto acadêmico desenvolvido para a disciplina de **Programação Orientada a Objetos** — Curso de Engenharia de Software Management (GES) · **Inatel · 2026**

---

## 🎯 Sobre o Projeto

O **SmartCine** é um sistema de gerenciamento de rede de cinemas desenvolvido em Java, que simula de forma realista o fluxo de venda de ingressos em múltiplos terminais simultâneos. O projeto foi construído como ecossistema integrado, unindo os três grandes pilares técnicos da disciplina em um único software coeso:

- **Polimorfismo profundo** na modelagem dos tipos de ingressos
- **Persistência relacional** via banco de dados MySQL com padrão DAO
- **Concorrência avançada** com threads simulando terminais de venda em paralelo

---

## ✨ Funcionalidades

- Venda de ingressos com múltiplos tipos (inteira, meia, VIP, assinante, etc.)
- Reserva e bloqueio de assentos em tempo real com controle de concorrência
- Persistência do histórico de ingressos emitidos e status de ocupação das salas
- Consultas analíticas de faturamento por tipo de ingresso
- Validação de entrada na portaria via interface `Validavel`
- Tratamento robusto de conflitos com a exceção customizada `AssentoIndisponivelException`

---

## 🏗️ Arquitetura e POO

### Hierarquia de Classes — Herança e Polimorfismo

O núcleo do sistema é a classe abstrata `Ingresso`, que define o contrato base de todos os tipos de bilhetes comercializados. A partir dela, ramificações concretas implementam regras de negócio específicas (cálculo de desconto, validação de elegibilidade, etc.), demonstrando polimorfismo em sua forma mais expressiva.

```
Ingresso (abstract)
├── IngressoInteiro
├── IngressoMeia
├── IngressoVIP
└── IngressoAssinante
```

### Interface `Validavel`

Todos os ingressos implementam a interface `Validavel`, que padroniza o processo de verificação na portaria do estabelecimento, garantindo um contrato unificado independentemente do tipo de bilhete.

### Encapsulamento e Boas Práticas

- Atributos encapsulados com visibilidade controlada
- Construtores sobrecarregados para flexibilidade na criação de objetos
- Nomenclatura em padrão **CamelCase** consistente em todo o projeto
- Separação clara de responsabilidades entre camadas (modelo, DAO, apresentação)

---

## ⚡ Engenharia de Concorrência e Persistência

### 🧵 Mecanismo de Threads & Sincronização

Para simular a realidade de uma rede de cinemas, a classe `TerminalVendasThread` estende o ciclo de vida concorrente do Java. Quando múltiplos terminais tentam comprar ingressos para o mesmo assento na classe `Sessao`, o sistema utiliza o modificador `synchronized` ou travas atômicas na reserva de assentos.

Caso um terminal tente confirmar um assento que acabou de ser comprado por outra thread milissegundos antes, o sistema impede a gravação e lança imediatamente a exceção de negócio `AssentoIndisponivelException`, abortando a transação daquela thread sem afetar os demais compradores em paralelo.

### 🗄️ Camada de Persistência (MySQL + DAO)

A persistência utiliza o padrão **DAO (Data Access Object)** acoplado a uma classe de utilidade de infraestrutura (`ConexaoBanco`). Através do driver JDBC nativo (`mysql-connector-j-9.7.0.jar`), o sistema realiza:

- Salvamento do histórico consolidado de ingressos emitidos
- Atualização em tempo real do status de ocupação dos assentos no banco
- Consultas analíticas de faturamento por tipo de ingresso

---

## 👥 Equipe de Desenvolvimento

Desenvolvimento técnico, modelagem de dados e arquitetura foram distribuídos colaborativamente entre os integrantes do grupo — GES / Inatel:

| Integrante | Papel |
|---|---|
| Nathan Arruola da Costa | Arquitetura POO — Herança, Polimorfismo, Interface e Classes de Ingressos |
| Gustavo Henrique dos Santos Giron| Banco de Dados — Configuração MySQL, JDBC e Padrão DAO |
| Gabriel Westin | Engenharia de Concorrência — Threads, sincronização e travas de estado |
| Felipe Fróes | Testes e UX — Validação de logs, stress de threads e interface do terminal |
| Marcelo Paschoal | Análise e Documentação — Diagramação UML e validação das regras da NP2 |

---

## 🤖 Declaração de Uso de Inteligência Artificial

Em cumprimento às diretrizes éticas e acadêmicas da disciplina de Programação Orientada a Objetos, a equipe declara total transparência na utilização de ferramentas de IA Generativa durante o desenvolvimento do SmartCine.

### 🛠️ Ferramentas Utilizadas

- **Google Gemini** — Motor de arquitetura consultiva e ideação de soluções
- **GitHub Copilot** (via VS Code) — Assistente de programação pareada em tempo real, habilitado via GitHub Student Developer Pack
- **Claude (Anthropic)** — Assistente de auxílio à codificação, diagramação UML e elaboração da documentação do projeto

### 📊 Escopo de Atuação da IA

**Brainstorming e Planejamento:** O Google Gemini foi consultado nas fases conceituais para estruturar um problema real que englobasse, de forma orgânica, o uso simultâneo de Threads, Banco de Dados e Polimorfismo profundo em um único sistema.

**Engenharia de Prompts:** A equipe utilizou o Gemini para projetar e refinar prompts técnicos de arquitetura de software contendo as regras de negócio exatas do ecossistema do cinema — descontos, hierarquias de herança, assinaturas de métodos.

**Implementação Assistida (Copilot):** Os prompts estruturados foram utilizados no GitHub Copilot Chat, auxiliando na escrita de lógica repetitiva, injeção de construtores, encapsulamento e blocos de cálculo tarifário.

**Auxílio à Codificação (Claude):** O Claude foi utilizado como assistente técnico durante o desenvolvimento, apoiando a equipe na resolução de dúvidas de implementação, sugestão de abordagens para os mecanismos de concorrência e persistência, e refinamento de trechos de código Java.

**Diagramação UML (Claude):** O Claude auxiliou na estruturação e revisão dos diagramas UML do projeto, contribuindo para a representação visual da hierarquia de classes, relacionamentos entre entidades e fluxos de comportamento do sistema.

**Documentação (Claude):** A elaboração deste README foi realizada com apoio do Claude, que auxiliou na organização das seções, padronização da linguagem técnica e formatação do documento final.

### 🧑‍💻 Autoria e Validação Humana

A inteligência artificial operou **exclusivamente como assistente de produtividade**. Toda a lógica conceitual, resolução de bugs de concorrência, acoplamento transacional do banco de dados, depuração de erros e amarração dos pilares de POO foram revisados, alterados, testados e validados de forma autoral pelos integrantes da equipe. A IA não substituiu a tomada de decisão técnica nem a compreensão teórica dos pilares de software por parte dos alunos.

## 📈 Diagrama UML

<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/ef13e04c-c4df-49dd-bd64-6775ff95dd09" />

---

> 📍 **Inatel — Instituto Nacional de Telecomunicações** · Santa Rita do Sapucaí, MG · 2026
