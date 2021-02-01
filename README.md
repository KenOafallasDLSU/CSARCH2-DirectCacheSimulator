# CSARCH2-DirectCacheSimulator

#### [Git Repository](https://github.com/KenOafallasDLSU/CSARCH2-DirectCacheSimulator)

## Authors

CSARCH2 - S12

- BUHION, Deborah

- DIZON, Michaela

- LIN, James

- OAFALLAS, Kenneth

## Sample Input Cases

### Cache Problem Set #4

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

### Cache Problem Set #5

#### ***Input***

Cache Size: **4096**, as Words

Main Memory Size: **1024**, as Words

Block Size in Words: **4**

Cache Access Time: **1**

Memory Access Time: **10**

Load Through: **unchecked**

Contiguous Addresses: **unchecked**

Treat Input as: **Address**

Program Flow:
```
LOOP L1 10
    RANGE 0 4351
J L1
```

#### ***Expected Output***

Miss Count: **9**

Hit Count: **3**

Miss Penalty: **42.0 ns**

Average Memory Access Time: **31.75 ns**

Total Memory Access Time: **417.0 ns**

### Cache Problem Set #6

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

Miss Count: **9**

Hit Count: **3**

Miss Penalty: **42.0 ns**

Average Memory Access Time: **31.75 ns**

Total Memory Access Time: **417.0 ns**
