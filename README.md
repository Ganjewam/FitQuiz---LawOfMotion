# FitQuiz---LawOfMotion
# FitQuiz Test Suite

## Overview
This test suite validates the FitQuiz functionality on Laws of Motion website.

##Tech Stack
- **Selenium WebDriver** + **Java 17**
- ** TestNG** (Data Providers, Groups)
- **Page Object Model (POM)**
-** Maven** (Dependencies + Execution)

## Implemented Test Cases
1. **Verify FitQuiz Page Test1**: Navigate to FitQuiz page, enter name, and verify height selection is visible.
2. **Name Field Validation**: Validate name field behavior (empty name disables Next button).
3. **Verify FitQuiz Page Test3**: Test name, height, and weight selection flow.
4. **Page Source and Title Validation**: Verify page title and source contains expected text.
5. **Data-Driven Height Selection**: Test multiple height combinations using DataProvider.

## Groups
- **smoke**: Basic flow tests (Test1, Test3, Data-driven height)
- **validation**: Validations (Name field, Page title/source)

## Dependencies
- fitQuizPage object
- BaseClass (driver setup)

## Notes
- Klaviyo popup handling is implemented.
- Uses WebDriverWait for element visibility.
- DataProvider used for height combinations.
