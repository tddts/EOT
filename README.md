# EveTrader
"EVE Online Trader"

Desktop application that allows EVE Online player to search for profitable buy/sell orders in certain regions.
Not intended for mass use, as it's getting less useful with each user due to it's nature.

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
- [tools library version 2.0](https://github.com/tddts/tools-core).
- [sprix library version 8.0](https://github.com/tddts/sprix).
