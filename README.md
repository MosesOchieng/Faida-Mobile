
# Faida Mobile Application

Faida is a mobile application that allows users to create and manage their own shops. It provides a user-friendly interface for creating and updating shop details, managing inventory, and tracking sales.

## Features

- User Authentication: Users can create accounts, log in, and manage their profiles.
- Shop Creation: Users can create their own shops by providing shop details such as name, description, location, and contact information.
- Inventory Management: Users can add, update, and remove products in their shops' inventory.
- Sales Tracking: Users can track sales and view sales reports for their shops.

## Technologies Used

- Flutter: A cross-platform framework for building mobile applications.
- Dart: The programming language used for developing Flutter applications.
- Spring Boot: A Java-based framework for building backend applications.
- MySQL: A relational database management system for storing shop and inventory data.

## Getting Started

### Prerequisites

- Flutter SDK: Install Flutter by following the official Flutter installation guide (https://flutter.dev/docs/get-started/install).
- Android Studio / Xcode: Set up Android Studio or Xcode for running the Flutter application on an emulator or physical device.
- Java 11 or higher
- Maven or Gradle
- MySQL database server

### Backend Setup

1. Clone the backend repository:


2. Configure the database:
- Set up a MySQL database server and create a new database for the application.
- Update the database configuration in the backend application properties file (`src/main/resources/application.properties`):
  - Set the database URL, username, and password.
  - Update any other required configurations.

3. Build and run the backend application:
- Using Maven: `mvn spring-boot:run`
- Using Gradle: `./gradlew bootRun`

### Frontend Setup

1. Clone the frontend repository:


2. Update the backend URL:
- Open the Flutter project in your preferred IDE or text editor.
- Update the API base URL in the Flutter application code (`lib/utils/api.dart`) to match your backend server URL.

3. Install dependencies:
- Run `flutter pub get` to download and install the required dependencies for the Flutter application.

4. Run the application:
- Using command line: `flutter run`
- Using IDE: Run the application from your preferred IDE.

## Contributing

Contributions to the Faida mobile application are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push the branch to your fork.
4. Submit a pull request, explaining your changes and any additional information.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

If you have any questions or suggestions, please feel free to reach out to us at [your-email@example.com](mailto:your-email@example.com).

