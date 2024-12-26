# Minimal Cover Implicants

This project implements an application for determining the minimal covering implicants, developed as a team project by **Nemanja**, **Srdjan**, and **Me**. It was created as part of the *Specification and Software Modeling* course using **Java** and **JavaFX**.

## Objective

The primary goal of this application is to simplify logical expressions by determining the minimal set of implicants required to cover all the outputs in a given truth table. This process is crucial in digital logic design and optimization of circuits.

## Features

- **Input Handling**: Supports input of truth tables and logical expressions.
- **Visualization**: Displays Karnaugh maps (K-maps) to represent the logic visually.
- **Algorithm Implementation**: Implements the Quine-McCluskey algorithm for simplification.
- **Result Display**: Shows the simplified expressions and highlights essential implicants.
- **Interactive UI**: Built with JavaFX to provide a user-friendly interface for input and visualization.
- **Export Option**: Allows exporting results to a file for documentation.
- **Two Modes**:
  - **Educational Mode**: Step-by-step explanation of the simplification process for learning purposes.
  - **Design Mode**: Fast simplification focused on practical applications.

## Project Structure

The project is organized into the following modules:

- **Model**: Encapsulates data structures like truth tables, implicants, and simplified expressions.
- **View**: Handles the JavaFX-based graphical user interface.
- **Controller**: Acts as the intermediary between the Model and View, processing user actions and triggering calculations.
- **Algorithm**: Contains the logic for determining minimal covering implicants, including the Quine-McCluskey implementation.

## Technologies Used

- **Java**: The primary programming language.
- **JavaFX**: For creating the graphical user interface.
- **Maven**: For project dependency management and build automation.

## How to Run

### Prerequisites

- JDK 11 or higher
- Maven installed on your system

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/mrsicsasa/OdredjivanjeMinimalnogPrekrivanjaImplikanti.git
   cd OdredjivanjeMinimalnogPrekrivanjaImplikanti
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn javafx:run
   ```

## Usage

1. Launch the application.
2. Select the desired mode:
   - **Educational Mode** for a guided simplification process.
   - **Design Mode** for direct results.
3. Input the truth table or logical expression.
4. Visualize the Karnaugh map and review the essential implicants.
5. View the simplified expression in the results section.
6. Export results if needed.


## Lessons Learned

- Collaborative software development and project management.
- Implementing advanced algorithms like Quine-McCluskey in Java.
- Building dynamic and interactive UIs with JavaFX.
- Applying software modeling techniques in a real-world scenario.

## Resources

- Course Material: *Specification and Software Modeling*
- [JavaFX Documentation](https://openjfx.io/)
- [Quine-McCluskey Algorithm Reference](https://en.wikipedia.org/wiki/Quine%E2%80%93McCluskey_algorithm)

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
