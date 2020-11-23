# EveTrader
"EVE Online Trader"

Desktop application that allows EVE Online player to search for profitable buy/sell orders in certain regions.
Not intended for mass use, as it's getting less useful with each user due to it's nature.

## Features
- Searching orders by available cargo space and wallet funds
- Finding routes based on given system security level
- Calculating income based on available cargo space and given tax
- Creating in-game routes
- EVE SSO login
- Developer login

## Supported languages
- English
- Russian

English is used by default.

Чтобы включить русский язык добавьте *\[-Duser.language=ru -Duser.country=RU]* к параметрам запуска.<br />
Например: **java -Duser.language=ru -Duser.country=RU -jar ./JET-1.8.jar**

## Tools used
- JavaFX
- Spring (Core, AOP, REST)
- Guava EventBus
- Apache HttpComponents
- Ebean ORM
- H2 DB

## Build requirements
- JDK 8.
- Maven version 3.2.5 or higher.
- [tools](https://github.com/tddts/tools-core) library version 2.0.
- [sprix](https://github.com/tddts/sprix) library version 8.0.

## Download
[Java 8 version](https://github.com/tddts/EveTrader/raw/master/bin/eve-trader-8.0.jar). Multiplatform. Requires JRE 8 or above.
