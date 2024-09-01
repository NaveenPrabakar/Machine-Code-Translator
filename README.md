# Machine-Code-Translator

A Java-based tool for decoding 32-bit binary machine instructions, converting them into human-readable assembly language representations.

## Table of Contents
- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Data Structures](#data-structures)
- [Algorithms](#algorithms)
- [Usage](#usage)
- [Build and Run](#build-and-run)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This project decodes binary instructions encoded in the LEGv8 assembly language. It reads binary data from a file, processes it to extract various fields (such as opcodes and register identifiers), and then outputs a human-readable representation of each instruction.

## Project Structure

- **`PA2.java`**: The main class that drives the program. It handles reading binary data from a file and coordinates the decoding process by interacting with the `Register` and `Output` classes.
- **`Register.java`**: Responsible for bit-level manipulation. It extracts specific parts of a 32-bit instruction, such as registers and addresses.
- **`Output.java`**: Converts binary instructions into their corresponding assembly language representation. It uses a `Hashtable` to map opcodes to assembly instructions.

## Data Structures

- **`Hashtable<Integer, String>`**:
  - Used in `Output.java` to store opcode mappings. This structure allows for quick lookup of assembly instructions based on binary opcodes.
 
  [Screenshot 2024-09-01 105829](https://github.com/user-attachments/assets/dcdff201-7de6-4a71-bf00-7313b90b87ed)


- **`ArrayList<String>`**:
  - Used in `PA2.java` to store the decoded output strings. This allows for easy storage and retrieval of the decoded instructions.

- **`ArrayList<Integer>`**:
  - Also used in `PA2.java` to store the locations of labels within the instruction set.
 
  [Screenshot 2024-09-01 105722](https://github.com/user-attachments/assets/6cec2f34-8e32-40e7-9ac7-6205a62e9ba0)

## Algorithms

- **Bitwise Operations**:
  - The project makes extensive use of bitwise operators (`>>`, `&`, etc.) to extract fields from 32-bit binary instructions. For example, extracting the opcode might involve shifting the bits of the instruction to the right and masking with a bitwise AND operation.

    [Screenshot 2024-09-01 105758](https://github.com/user-attachments/assets/8308b151-c854-40da-bc3a-7b31fb6db786)


- **File I/O**:
  - `PA2.java` reads binary data from a file in chunks of 4 bytes (32 bits). This data is then processed to form the complete 32-bit instructions.
 
    [Screenshot 2024-09-01 105713](https://github.com/user-attachments/assets/726a255c-341f-488a-ac53-0f316d22c52e)


- **Decoding**:
  - The `Output.java` class maps binary opcodes to assembly language instructions using a hashtable, allowing for efficient decoding.
 
    [image](https://github.com/user-attachments/assets/ebc0fcc9-9a67-448f-baea-559bb99e15b9)


## Usage

To use this tool, you need a binary file containing 32-bit LEGv8 instructions. The program reads this file, decodes the instructions, and outputs the corresponding assembly code.

## Build and Run

### Prerequisites
- Java Development Kit (JDK) installed on your system.

### Build
To compile the project, use the provided `build.sh` script:
  ```bash
  ./build.sh


  



