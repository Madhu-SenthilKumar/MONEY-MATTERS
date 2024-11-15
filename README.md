EXPENSE TRACKER APP ANDROID

This is an Android application designed to help users track their daily expenses efficiently. The project is developed using Kotlin and leverages SQLite databases to store user data and transaction records.

📂 PROJECT STRUCTURE

AndroidManifest.xml: Defines the app's components, permissions, and activities.

Kotlin Source Files:

Core activities include:
MainActivity.kt: Main entry point of the application.
LoginActivity.kt & RegisterActivity.kt: User authentication.
AddExpensesActivity.kt: Interface for adding new expenses.
SetLimitActivity.kt: Allows users to set a budget limit.
ViewRecordsActivity.kt: Displays expense records.
Database and data management:
ExpenseDatabase.kt, UserDatabase.kt, ItemsDatabase.kt: Database handling using Room.
DAOs (ExpenseDao.kt, UserDao.kt, ItemsDao.kt) for data operations.
Helper classes for managing database connections.
Data models:
Expense.kt, User.kt, Items.kt: Data classes representing different entities in the app.
UI Components:

The project includes a structured theme using:
Theme.kt, Color.kt, Shape.kt, and Type.kt files for consistent UI styling.

📋 Features
User Authentication: Secure registration and login.
Expense Tracking: Add, view, and manage expenses.
Budget Management: Set spending limits to manage finances better.
Data Persistence: Uses Room database for offline data storage.

🛠️ Technologies Used
Kotlin: Programming language for Android development.
SQLite: Embedded database for storing user data.
Android SDK: Core tools and libraries for Android development.
Material Design: For a polished and user-friendly interface.

🗃️ Future Enhancements
Implement data visualization for expense analytics.
Add cloud storage for data backup.
Integrate notifications for budget limits.
