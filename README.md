# Hotel Reservation API

Este projeto é uma API RESTful para gerenciamento de reservas de hotéis. Ele permite que usuários façam reservas, visualizem quartos disponíveis e realizem pagamentos. A API utiliza Spring Boot e PostgreSQL como banco de dados, e foi desenvolvida com o intuito de praticar a criação de APIs com relacionamentos entre entidades.

## Diagrama de Classes

```mermaid
classDiagram
    class User {
        +Long id
        +String name
        +String email
        +String password
        +List~Reservation~ reservations
    }

    class Hotel {
        +Long id
        +String name
        +String location
        +List~Room~ rooms
    }

    class Room {
        +Long id
        +String type
        +Double price
        +Boolean isAvailable
        +Hotel hotel
        +List~Reservation~ reservations
    }

    class Reservation {
        +Long id
        +Date checkInDate
        +Date checkOutDate
        +User user
        +Room room
        +Payment payment
    }

    class Payment {
        +Long id
        +Double amount
        +String status
        +Reservation reservation
    }

    %% Relationships
    User "1" --o "many" Reservation : has
    Hotel "1" --o "many" Room : has
    Room "1" --o "many" Reservation : booked
    Reservation "1" --> "1" Payment : has
