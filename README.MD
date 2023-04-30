## HAL9k to 68k compiler

This projects targets to pupils of the UIB who have to create using `68K assembler` a new machine code named `HAL9k`.
For using this project you only hace to download the `compiler.zip` and edit the java environment variable targeting to
your java 17 JDK.

## Instructions:
- **LOA M,Ti**: `0000xxxmmmmmmmmi`
- **STO Ti,M**: `0001xxxmmmmmmmmi`
- **LOIP (Xb),Ti**: `0010xxxxxbbbxxxi`
- **STIP Ti,(Xb)**: `0011xxxxxbbbxxxi`
- **GOI M**: `0100xxxmmmmmmmmx`
- **GOZ M**: `0101xxxmmmmmmmmx`
- **GON M**: `0110xxxmmmmmmmmx`
- **EXIT**: `0110xxxmmmmmmmmx`
- **COPY Rb,Rc**: `11000xxxxbbbxccc`
- **ADD Ra,Rb,Rc**: `11001aaaxbbbxccc`
- **SUB Ra,Rb,Rc**: `11010aaaxbbbxccc`
- **AND Ra,Rb,Rc**: `11011aaaxbbbxccc`
- **SET #k,Rc**: `11100kkkkkkkkccc`
- **ADQ #k,Rc**: `11101kkkkkkkkccc`
- **LSH #p,Rb,#n**: `11110pppxbbbxxxn`