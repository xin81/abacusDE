# ABacusDE  
[English] | [Deutsch](LIESMICH.md)  
A Java-based emulator for a traditional German abacus.


![Abacus Interface](Screenshot.png)


## Features  
* **Authentic Experience**: A typical German abacus with 100 beads across 10 rods, following traditional mathematical rules and logic.
* **Dual Results Display**: Unlike a physical abacus, this emulator shows two types of results at the top:
    * **Positive Results**: The sum of active beads on the right side.
    * **Negative Results**: Calculated using computer-like logic (e.g., -1 is represented by 9 active beads on the right and 1 inactive bead on the left).
* **Audio Feedback**: Realistic clicking sounds trigger every time you move a bead.

## Requirements  
* **Java**: 21+
* **Maven Wrapper**: Included in the project (`./mvnw`)

## Installation  
To install immediately without compiling from source:
1. Download the latest release for your operating system (for example, abacus-1.0.dmg on a MacOS X machine)
2. Double-click the installer and follow the on-screen instructions.

## Building from Source  
To compile the project and generate the JAR manually:
```bash
./mvnw clean package
```

## Usage  
* **Movement**: Use the buttons labeled **Plus 10ᵖ** or **Minus 10ᵖ** to move beads; the small p denotes the power of 10 and also equals the numbering of each rod.
    * Example: Clicking `10⁰` adds/removes a bead with a value of 1.
    * Example: Clicking `10⁹` adds/removes a bead with a value of 1,000,000,000.
* **Customization**: Use the **Options** menu to toggle "Hide Results" or "Hide Numbering."
* **Exiting**: Select **File > Close** to end the program.

## License  
Distributed under the MIT License. See [LICENSE](LICENSE) for more information.

## Credits & Copyright  
Copyright (c) 2026 Ruan Yue Xin (xin81)
