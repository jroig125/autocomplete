---
Autocomplete
---

### Build
To build the program, enter the following into the command line in the directory you wish to work out of.

```bash
git clone https://github.com/jroig125/autocomplete.git
cd autocomplete
javac com/asymmetrik/*
jar cmf MANIFEST.MF Autocomplete.jar com/asymmetrik/*
java -jar Autocomplete.jar  
```

### Run
To run the program, enter the following into the command line from the same directory.
```bash
java -jar Autocomplete.jar
```
Note: the .java files can be found for review by accessing src/com/asymmetrik from the root folder of the project Autocomplete.
### Usage

When the program is initialized, the main menu will be displayed with initial directions. The menu will display options to pursue one of the two available modes: `Training Mode` and `Autocomplete Mode`. Training Mode is used to input training texts to the algorithm. Autocomplete Mode is used to input word fragments to receive Autocomplete suggestions. You can select Training Mode by typing 't' and pressing enter on the main menu. You can select Autocomplete Mode by typing 'a' and pressing enter on the main menu. You may also quit the program from the main menu by typing '0' and pressing enter (the use of 0 avoids confusion with the text-based functions of the program). Quitting the program will delete all training data. You may freely switch between modes while retaining all input training text within the same execution of the program. Switching out of a mode does not delete the training data.

#### Training Mode

In training mode, enter training text one line at a time, pressing enter between each line. The line may consist of several complete sentences with punctuation, or can be a simple sequence of words. The program is not case-sensitive except for the standalone word "I". All words except "I" will be made lowercase to avoid duplicates. The program retains apostrophes and dashes within words, while removing all other punctuation to keep a proper count of entered words. Standalone punctuation (e.g. " : ") is removed from the training text. You may enter as many lines of training text as you wish. When you are done entering training text, type '0' and press enter to return to the main menu.

#### Autocomplete Mode

In autocomplete mode, type a partially completed word and press enter. In response, you will get a list of suggested completed words that start with your sequence of characters. If there are no suggestions based on the entered word fragment, the program will notify you as such. You may enter as many word fragments as you wish, one per line. The program will not provide suggestions if multiple words are entered on one line. The algorithm does not check entries against a dictionary. While this allows for flexibility, any and all words entered during the training will be possible suggestions, even if nonsensical.

The word list will be organized primarily by confidence that it is the next word needed based on the expectations set by the training text. If candidate words share the same confidence, shorter words are displayed first, since these are more likely to be function words or other common content words. If the candidate words share the same confidence and word length, they are organized alphabetically, which causes no conflicts since there are no repeated words in the list. Plural versions of words are considered separate words. When you are done receiving suggestions, type '0' and press enter to return to the main menu.

### Test Input
Test input is available in the main directory under TestInput.txt. Note that the program is not designed to read a text file, so the contents must be copied and pasted into Training Mode in order to be used.

### Sample Output
After entering the test code above in Training Mode, the following is expected from Autocomplete Mode. The output line contains each suggested completion ordered by confidence. In this case, "word" is the most likely next word.

```$xslt
wo
word (29), words (5), won't (1)
```