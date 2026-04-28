ABacusDE
A Java-based emulator for a traditional German abacus.

FEATURES
You will see a typical German abacus with all its 100 beads and 10 rods.
This abacus works similarly to a real physical German abacus including its mathematical rules and logic.
However, unlike a physical abacus, you will see two types of results in the top of this abacus.
On the right side, you will see the positive results (sum of bead values on the right side of this abacus).
On the left side, you will see the negative results (sum of bead values on the left side of this abacus).
The negative sum is calculated in the same way as computers do (for example, -1 is represented by 9 active
beads on the right side, and 1 inactive bead on the left side). 
Everytime you move a bead, you will hear the sound of clicking beads.

REQUIREMENTS
Java 25+
Maven (./mwvnw)

INSTALLATION
If you want to install it immediately without bothering compilation and building, just use the installer abacus-1.0.dmg. Double-click the installer, agree to the terms and use, and install it.

BUILDING
To compile the project and generate the JAR:
```bash
./mvnw clean package
```

USAGE
You can move each beade from left to right by clicking a button called "plus 10^p". The upper digit p above the 10 is the power of ten.
So for instance, when you click 10^0, you add a bead of the value 1 because 10^0=1. When you click 10^9, you add a bead of the value 1,000,000,000 because 10^9=1,000,000,000 (one billion).
When you click minus 10^0, you remove a bead of value 1. Click minus 10^9 to remove a bead of value 1,000,000,000.
By default, you will always see the result of your maths operations, and the numbering of each rod.
If you don't want to see them, you can easily hide them by selecting "Hide Results" or "Hide Numbering" in the menu "Options".
When you're done with the abacaus, just "Close" (in the menu "File") to end this program.

LICENSE
Distributed under the MIT License. See LICENSE for more information.

CREDITS/COPYRIGHT
© Ruan Yue Xin (xin81) 
