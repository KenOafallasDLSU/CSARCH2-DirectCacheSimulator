# CSARCH2-DirectCacheSimulator

This manual is best viewed through a markdown previewer such as that in VSCode or GitHub

#### [Git Repository](https://github.com/KenOafallasDLSU/CSARCH2-DirectCacheSimulator)

## Authors

CSARCH2 - S12

- BUHION, Deborah

- DIZON, Michaela

- LIN, James

- OAFALLAS, Kenneth

## Features and Options

### ***Program Flow***

Input block or address as Decimal or Hexadecimal, with initial location as 0. There can only be one address or command per line. Leading spaces and line separators can be used to organize program flow input. 

As Decimal input, enter as normal integer number i.e. `15` for location 15. For Hexadecimal input, write as x followed by Hex integer with capital letters with no space in between i.e. `xF` for location 15.

`RANGE <a1> <a2>` puts all addresses from a1 to a2 inclusive into the program flow once. <a1> and <a2> are integers where <a1> is less than <a2>. For example, `RANGE 0 4` puts 0, 1, 2, 3 and 4 into the program flow.

`LOOP <name> <count>` marks the start of a loop block. <name> is a case sensitive identifier for the loop, <count> is a Decimal integer which determines how mamy times the loop is executed.

`J <name>` marks the end of a loop block. <name> is a case sensitive identifier to associate with a LOOP command. All commands and addresses between the LOOP command and the J command will be looped. Loops can be nested, and can involve RANGE commands. LOOP commands are closed with J commands in a last in-first out method similar to typical loops in Java. 
    
### ***Simulation Parameters***

Cache Size, Main Memory Size and Block Size are ***positive Decimal integers***

Cache Access Time and Memory Access Time are ***positive Decimal floats***

Inputs in the program flow can be treated as either Blocks or Addresses, picked using a radio button.

***Contiguous Addresses*** when checked means addresses are in a sequence and can be grouped into blocks, such as for Cache Problem Set #5 and #6. Input must be treated as Address for this option to be valid.

***Load Through*** when checked simulates load through in calculations, where the first address location is the desired address as is standardized in CSARCH2 class.

### ***Result Export***

Allows user to export the result on the output screen into a text file at a selected directory. Name of file is Result_Log_<timestamp>.txt, where <timestamp> is standard Java timestamp.

## Sample Input Cases

## **Cache Problem Set #4**

#### ***Input***

Cache Size: **32**, as Words

Main Memory Size: **1024**, as Words

Block Size in Words: **4**

Cache Access Time: **1**

Memory Access Time: **10**

Load Through: **unchecked**

Contiguous Addresses: **unchecked**

Treat Input as: **Address**

Program Flow:
```
x200
x204
x208
x20C
x2F4
x2F0
x200
x204
x218
x21C
x24C
x2F4
```

#### ***Expected Output***

Miss Count: **9**

Hit Count: **3**

Miss Penalty: **42.0 ns**

Average Memory Access Time: **31.75 ns**

Total Memory Access Time: **417.0 ns**

## **Cache Problem Set #5**

#### ***Input***

Cache Size: **4096**, as Words

Main Memory Size: **1048576**, as Words

Block Size in Words: **64**

Cache Access Time: **1**

Memory Access Time: **10**

Load Through: **unchecked**

Contiguous Addresses: **checked**

Treat Input as: **Address**

Program Flow:
```
LOOP L1 10
    RANGE 0 4351
J L1
```

#### ***Expected Output***

Miss Count: **140**

Hit Count: **540**

Miss Penalty: **642.0 ns**

Average Memory Access Time: **132.9706 ns**

Total Memory Access Time: **133260.0 ns**

## **Cache Problem Set #6**

Cache Size: **1024**, as Words

Main Memory Size: **65536**, as Words

Block Size in Words: **128**

Cache Access Time: **1**

Memory Access Time: **10**

Load Through: **unchecked**

Contiguous Addresses: **checked**

Treat Input as: **Address**

Program Flow:
```
RANGE 0 127
LOOP L1 10
    RANGE 128 255
    LOOP L2 20
        RANGE 256 511
    J L2
    RANGE 512 1279
J L1
RANGE 1280 1535
```

#### ***Expected Output***

Miss Count: **30**

Hit Count: **443**

Miss Penalty: **1282.0 ns**

Average Memory Access Time: **82.24735 ns**

Total Memory Access Time: **98974.0 ns**
