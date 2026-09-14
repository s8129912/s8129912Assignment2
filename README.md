# NIT3213 Android Application Assignment 2

* **Student Name:** Vaishnavi Bhoga
* **Student ID:** 8129912 (App Username: 8129912)
* **Project Name:** s8129912Assignment2

---

## Project Overview
An Android application built using **Kotlin**, **XML layouts**, and **Fragments** following the **MVVM (Model-View-ViewModel)** architectural pattern. The application integrates with the remote `nit3213apinew` server to authenticate students and dynamically retrieve and display a live dataset of world languages.

## Architecture & Technical Requirements
* **Architecture:** MVVM with Fragments and View Binding for clean separation of concerns.
* **Dependency Injection:** **Dagger Hilt** handles the lifecycle and injection of network modules and repositories.
* **Networking Layer:** **Retrofit 2** combined with **Gson** for handling asynchronous API requests and JSON deserialization.
* **UI Components:** Traditional XML templates utilizing a `RecyclerView` to efficiently handle summary list data, navigating to a detailed view on item click.

---

## API Configuration
* **Base URL:** `https://onrender.com`
* **Auth Endpoint Used:** `/footscray/auth`, `/sydney/auth`, or `/br/auth` (Depending on class location)
* **Dashboard Endpoint:** `/dashboard/{keypass}`

---

## How to Build and Run the Application
1. **Clone the Repository:** Clone or download this repository folder link to your local machine.
2. **Open in Android Studio:** Open Android Studio (Ladybug or newer recommended), select `File > Open`, and choose the `s8129912Assignment2` project root directory.
3. **Gradle Sync:** Allow Gradle to sync completely and download all necessary project dependencies.
4. **Select Device:** Select a virtual emulator or physical device configuration (e.g., Medium Phone API 34 or newer).
5. **Run Project:** Press the green **Run** button to compile, install, and execute the application.

## Testing
* Includes **Unit Tests** for critical architecture components (such as the ViewModels) to validate authentication states and data stream mapping.