# 💊 Drug Database Web App

A full-stack pharmaceutical drug database built with Java and Spring Boot.

The application allows users to manage their own persistent drug database while also searching real-world medication data through the RxNorm and openFDA APIs. External medication results can be imported directly into the local database for later searching, viewing and management.

## Features

- Add, edit and delete drug records
- Store drug information persistently using H2
- Search stored medications by name, manufacturer or usage
- Search real-world medication data using the RxNorm API
- Retrieve manufacturer, indications/usage and adverse-reaction information from openFDA
- Combine RxNorm and openFDA data using RxCUI identifiers
- Save externally retrieved medication data directly to the local database
- Prevent duplicate API imports using RxCUI-based duplicate detection
- View detailed medication usage and adverse-reaction information on dedicated detail views
- Handle missing or unavailable external API data
- Responsive web interface built with Thymeleaf and Bootstrap

## API Integration

The application combines data from two external pharmaceutical APIs.

### RxNorm

RxNorm is used to search for medications and retrieve standardized medication information, including the medication's RxCUI identifier.

### openFDA

The RxCUI returned by RxNorm is used to query openFDA for additional information such as:

- Manufacturer
- Indications and usage
- Adverse reactions

The results from both APIs are mapped into DTOs and combined by the service layer into a unified medication result.

Users can then save the retrieved medication to the application's persistent database.

### Data Flow

User Search
    ↓
RxNorm API
    ↓
RxCUI
    ↓
openFDA API
    ↓
MedicationDataService
    ↓
DTO Mapping
    ↓
Medication Search Result
    ↓
Save to Database
    ↓
Persistent Drug Record

## Database

Drug records are managed using Spring Data JPA.

The application supports standard CRUD operations and includes a native SQL query for searching across multiple fields, including:
- Drug name
- Manufacturer
- Usage
Externally imported medications also store their RxCUI identifier, which is used to prevent duplicate imports.

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- REST APIs

### Frontend
- Thymeleaf
- HTML
- Bootstrap

### Database
- H2
- SQL

### External APIs
- RxNorm
- openFDA

### Build Tools
- Maven

## Project Structure

The application follows a layered architecture:

- **Controller Layer** – Handles HTTP requests, navigation, CRUD operations and medication imports
- **Service Layer** – Handles RxNorm and openFDA API communication and combines external data
- **Repository Layer** – Handles database access and SQL queries using Spring Data JPA
- **DTO Layer** – Maps and transforms external API JSON responses
- **Model Layer** – Represents persistent drug entities
- **View Layer** – Thymeleaf templates used to display and manage medication data

## How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Ensure Java and Maven are installed/configured.
4. Run the main Spring Boot application class.
5. Open:

   http://localhost:8080

## Future Improvements

- Migrate from H2 to MySQL
- Add pagination for larger medication collections
- Add advanced filtering and sorting
- Further improve UI/UX and styling
- Add automated unit and integration tests
- Expand medication metadata and search capabilities
