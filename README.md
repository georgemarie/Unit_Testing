# 📊 Unit Testing for JFreeChart and Custom Discount Modules

## 📝 Overview
This project focuses on writing and executing unit tests for various components of a Java application using **JFreeChart**, an open-source Java framework for chart creation and rendering. The primary objective is to validate the functionality of selected classes by testing their constructors, methods, and expected behavior.

---

## 📦 Project Structure

The IntelliJ Java project includes the following:

- ✅ **JFreeChart Required Libraries**  
  A set of essential JAR files to allow JFreeChart components to compile and run correctly.
  
- 🧪 **Sample Test Files**  
  - `WeekClassTest.java`: A sample test case for the `org.jfree.data.time.Week` class. This test case is functional and serves as a reference.
  - `DiscountCalculatorTest.java`: Includes test cases for all methods in the `DiscountCalculator` class.
  - `DiscountManagerTest.java`: Contains isolated unit tests for `DiscountManager` without dependencies on other classes.

- 📚 **Modified JavaDocs**  
  A zipped file containing:
  - Official JavaDocs for the JFreeChart framework.
  - Detailed documentation for `DiscountCalculator` and `DiscountManager`.

---

## 🧪 Classes to Be Tested

1. **`org.jfree.data.time.Week`**
   - Test all **constructors** and **public methods**.
   - Validate correct week representation, comparison logic, and date boundary handling.

2. **`DiscountCalculator`**
   - Test all core business logic methods (e.g., applying discounts, validating input).
   - Handle edge cases (e.g., negative prices, null input).

3. **`DiscountManager`**
   - Test without relying on external dependencies.
   - Focus on managing and applying multiple discounts in various scenarios.

---

## 🚀 Deliverables

 ✅ Three Unit Test Files:
   - `WeekClassTest.java`
   - `DiscountCalculatorTest.java`
   - `DiscountManagerTest.java`

---

## 🛠 Testing Framework

- Language: **Java**
- Testing Tool: **JUnit (v4 or v5)** depending on configuration
- IDE: **IntelliJ IDEA**

---

## 📌 Notes

- All test cases should be **independent** and **repeatable**.
- Use meaningful **assertions** to check both normal and edge case scenarios.
- Ensure all code is covered with **maximum test coverage**.

---

## 📁 Example Output in Bugs Report

| Method              | Test Case Description                     | Status  | Notes                         |
|---------------------|-------------------------------------------|---------|-------------------------------|
| `applyDiscount()`   | Apply 20% discount to $100 item           | Passed  |                               |
| `getWeek(int, int)` | Week from year 2024 and week 35           | Failed  | Returned null for valid input|

