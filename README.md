---

# DNA Compressor / Decompressor

## Description
This is a console-based Java application for compressing and decompressing DNA sequences (`A`, `C`, `G`, `T`) into byte format and back.  
It supports simple commands to **compress**, **decompress**, show **about** info, and **exit**.

---

## Features
- **comp** `<DNA_sequence>` — Compress a DNA sequence into bytes and print the byte representation.
- **decomp** `<byte_count> <byte1> <byte2> ...>` — Decompress bytes into the original DNA sequence.
- **about** — Display author and project information.
- **exit** — Exit the program.

---

## Command Formats

### Compress (`comp`)
- **Input:** A sequence of characters `A`, `C`, `G`, and `T` only (case insensitive).
- **Output:** 
  - Number of letters.
  - Hexadecimal representation of the compressed data.

**Example:**
```
comp ACGT
```
**Output:**
```
4 1B 00 
```
(Here, `1B 00` is the hex form of compressed data.)

---

### Decompress (`decomp`)
- **Input:** 
  - The number of bytes.
  - A list of bytes (as integers from `-127` to `127`).

**Example:**
```
decomp 2 27 0
```
**Output:**
```
1B 00 
ACGT
```

---

### Exit (`exit`)
Immediately stops the program.

---

## Installation and Running
1. Make sure you have JDK 8 or higher installed.
2. Download or copy the `proj.java` file.
3. Open a terminal and navigate to the folder where `proj.java` is saved.
4. Compile the project:
   ```bash
   javac proj.java
   ```
5. Run the program:
   ```bash
   java proj
   ```

---

## Error Handling
- If the input format is incorrect, the program prints:
  ```
  wrong command format
  ```
- After every command (successful or not), the program waits for the next user command.

---

## Notes
- **Compression**: Each DNA letter is encoded into **2 bits** (`A=00`, `C=01`, `G=10`, `T=11`).
- **Decompression**: Bytes are unpacked to recover the original DNA sequence.
- Only valid characters `A`, `C`, `G`, `T` are allowed during compression.
- Bytes must be within the range **-127 to 127** during decompression.

---

## Author
- Developer: **German Veideman**

---
