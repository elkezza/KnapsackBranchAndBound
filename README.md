# Branch and Bound Algorithm for Knapsack Problem

This project implements the Branch and Bound algorithm to solve the 0/1 Knapsack problem. It is designed to handle various datasets with different types of data correlation, such as uncorrelated, weakly correlated, and strongly correlated instances.

## Description

The Branch and Bound algorithm is a well-known method used in combinatorial optimization. This project applies the algorithm to the classic 0/1 Knapsack problem, aiming to maximize the profit based on the items' values and weights under a given capacity constraint.

## Project Structure

The project is composed of several Java classes that work together to implement the Branch and Bound algorithm for the Knapsack problem:

- `Item`: Represents an item that can be put into the knapsack, characterized by its weight and value.
- `Knapsack`: Contains the main logic for solving the knapsack problem using the Branch and Bound technique. It handles the decision tree creation, node bounding, and tracks the maximum profit.
- `Node`: Represents a node in the state space tree, encapsulating the level of the node in the decision tree, the cumulative profit, weight, and the upper bound of the maximum profit for nodes on the path from the root to the node.
- `Main`: The entry point of the program. It tests the knapsack solver with sample data from different datasets.

The `data` directory within the project contains multiple subdirectories (e.g., `p01`, `p02`, ...), each corresponding to a different instance of the Knapsack problem. The files within these subdirectories include:

- `capacity.txt`: The maximum capacity of the knapsack.
- `description.txt`: A brief description of the dataset, usually indicating the correlation type.
- `optimal_solution.txt`: The known optimal solution for the given instance.
- `profits.txt`: The values or profits associated with each item.
- `weights.txt`: The weights of each item.
  
## Correlation Types and Data

The datasets included in this project are categorized based on the correlation between the weights and profits of the items:

- **Uncorrelated**: No specific relationship between weights and profits.
- **Weakly Correlated**: Some relationship exists but is not strong.
- **Strongly Correlated**: A strong direct relationship between weights and profits.

## Getting Started

To get a local copy up and running follow these simple steps:

1. Clone the repository
2. Navigate to the repository directory
3. Compile the Java classes
4. Run the `Main` class

## Usage

To run the solution, cd to where the Main class is at and execute the `Main` class which will process all datasets under the `data` folder and produce the maximum profit for each dataset along with the execution time.

## Results

The program outputs the maximum profit found for each dataset and compares it with the known optimal solution. The results are printed in the console with a success message if the calculated profit matches the known optimal profit.

## Visual Representation

The program includes a feature to graphically represent the branching process of the algorithm. This aids in understanding the decision tree created by the Branch and Bound method.

## Requirements

- Java SDK (version 18 or higher)
- Maven for dependency management


