# Selenium UI Automation Framework

A UI test automation framework built with **Selenium WebDriver**, **Java**, and **TestNG**, following the **Page Object Model (POM)** design pattern. Tests run against [saucedemo.com](https://www.saucedemo.com), a public e-commerce demo site.

## Tech Stack
- Java 17
- Selenium WebDriver 4.25.0
- TestNG 7.10.2
- WebDriverManager (automatic browser driver management)
- Maven (build & dependency management)

## Project Structure
```src/test/java
├── base/ # Shared test lifecycle (browser setup/teardown)
│ └── BaseTest.java
├── pages/ # Page Object classes — locators + actions per screen
│ ├── LoginPage.java
│ ├── InventoryPage.java
│ ├── CartPage.java
│ ├── CheckoutInfoPage.java
│ └── CheckoutOverviewPage.java
└── tests/ # Test classes — scenarios and assertions
├── LoginTest.java
├── CartTest.java
├── CheckoutTest.java
├── SortTest.java
├── LogoutTest.java
└── DataDrivenLoginTest.java
```


## What's Covered
- **Login**: valid login, invalid password, locked-out user, and data-driven negative login scenarios
- **Cart**: adding and removing items, cart badge state verification
- **Checkout**: full end-to-end flow — login → add item → cart → checkout info → order confirmation
- **Sorting**: verifying product price sort order
- **Logout**: session termination and redirect

## Design Notes
- **Page Object Model** — each page's locators and actions are encapsulated in its own class; test classes never interact with Selenium locators directly.
- **Explicit waits** (`WebDriverWait`) are used throughout instead of hardcoded sleeps, to keep tests reliable and avoid flaky timing issues.
- **Data-driven testing** — `DataDrivenLoginTest` uses a TestNG `@DataProvider` to run the same test logic across multiple credential scenarios without duplicating code.

## How to Run

**Run all tests via the TestNG suite:**
```
mvn test

```

**Or run the suite file directly in an IDE:**
Right-click `testng.xml` > Run As > TestNG Suite

**Run a single test class:**
Right-click any class in `src/test/java/tests` > Run As > TestNG Test

## Prerequisites
- JDK 17
- Maven
- Google Chrome installed (ChromeDriver is managed automatically via WebDriverManager — no manual driver setup needed)