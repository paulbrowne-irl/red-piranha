---
layout: home
---	

# Red Piranha Examples

Jump to 
* [Example]()
* [Setup]()

 Red-Piranha is a set of Power tools for Word, Excel and Office Power users.
 
![Red Piranha Splash Screen](https://paulbrowne-irl.github.io/red-piranha/images/splash.png)

It packages the Drools Business Rules Management System (BRMS) solution from RedHat (IBM) to be more accessible to Windows Desktop users. In particular, it makes it easier to feed information from Excel and Word (Office) Documents, apply business rules to these documents, and get useful results.

Most importantly, because your business rules are in an 'English like' language, it makes it very easy for you (and colleagues) to see what checks are being made


  * Word Document + Business Rules = Result

Why would we want to do this?

  * **Excel Formulas** can get too complicated. Rules are more simple.
  * Same for **Excel Macros** - and they have security concerns. You can tightly control the rules.
  * Google Spreadsheets are like Excel online, but don't have all the power. Rules add them back.
  * You control the rules. Who changes the Excel file when you're not looking?

## Find Home For ##
Explain for example

-   Prerequest
-   Files in directory
-   Console / logs

-   Where
-   Contents

-   More details

-   Input file
-   Rules applied
-   Output file

What objects are
 
## Business Problem ##
Statement: 


## What this sample does ##
High level 

 1. Walk through input file
 2. Walk through running RP - 1 screenshot
 3. Walk through output

## More detail on how it does it ##


### Setup ###
Text
Where get from
Pre-reqs

-   How to check you have Java

-   Mininum Version
-   Picture of command window
- Java Admin rights

-   Test:
- First time run
- 

(take from notes)

### Where to get the sample from ###
* Link to project
	

### Files in Directory ###
* Walk through

### Configuration ###
Walk through

> Example of config file

### Rules ###

### Running the Example ###
-   Running

-   Does not overwrite
-   Batch
-   Config
-   Logs
-   Screens
-   Step through what it is doing

-   Reads from Word ..
-   Applies rules
-   Appends to CSV

-   Useful Tools

-   Drools / Eclipse
-   GitHub source and pages
-   Visual studio drl

-   GUI Notes

-   Drill down , view cell, can copy paste into rules (and remove filters)

-   DRL Notes

-   Case senstive

-   CSV Notes

-   Case Sensitive headings
-   Case senstive file name in config
-   Doesn't like it when file is left open
-   Notion of templating

### Screens - details ###

### Logs - More info on what is going on ###


Log then modified - simple sample of where modified

-   Launch4j.log
-   Red-piranha.log

Only once

Where to find output when done

Make sure to close output

How to log to console


## Extending the example ##

### Investigating Data ##
* from the Screens
* Using the JSON Output

### Other config ###
GUI Mode

### Dev Setup ###
* Eclipse
* VSCode
* Java install

Eclipse download

Jboss / Drools tools

For tasks

[http://download.eclipse.org/egit/github/updates-nightly/](http://download.eclipse.org/egit/github/updates-nightly/)

[https://wiki.eclipse.org/EGit/GitHub/User_Guide](https://wiki.eclipse.org/EGit/GitHub/User_Guide)

Github

### More on Document Conversion ###

-   How to set as input file
-   Word (docx) and not doc - not yet impmeneted / how to covnert
-   Why we map everything to a cell
-   Viewing the data in the view
-   And how to use this to write data gainst it
- Link to Javadoc

INPUT

-   XLS/XLSX
-   WORD/WORDX

BASIC RP MODEL

RangeList

0 more Ranges

0 or more Cells

CONVERTOR MODEL - word

Document

-   Para

-   Text

RangeList 1 per Doc

-   Range 1 per Para

-   Cell 1 per Para

Cell Value Gives Text

Sample Rule to Access

???

Document

-   Tables

-   Rows

-   Cells in Table

RangeList 1 per Doc

[all rows in tables added] - but using

Table 1 Row 1 Convention

-   Range = one row on table

-   Cell = 1 cell per cell on table

Name is the fist row

Names - based on …

-   First row
-   thisCell.setOriginalTableRefernece("TABLE_"+tableCounter);
-   thisCell.setOriginalCellReference("R:"+rowCounter+" C:"+cellCounter);

Update: Paragraph / Cells - new doc?

Other ntoes

-   Filters to Ascii sets
-   Only table cells with a value get in (so can't guarantee all cells from table are present in working memory

Sample Doc: Before (picture of word)

Sample Doc after (from viewr)

Only document in and out …

### More on Document Output ### 
Dev Note on how to extend


OUTPUT
Available File formats
	- XLS / XLSX in and out
	- Word, WordX In
		○ Wriite rules to detect if format of incomfing doc changes
		○ "fingerprint" a couple of key points
		○ If values are repeated multiple times in doc, try to t
		○ Double check names nearby to 
			§ e.g. on our doc
			§ // cellName=Department Manager Name:_0 , originalTableReference ="TABLE_9"
		○ Prefer Table values to document, less likely to hcange
		○ Refer to Table name in rules
		○ 
	- ?? PDF - very simple
		○ How to create a template which we can then fill
	- ?? CSV Out - very simple
		○ Tries to append ??
		○ Ignores original document 
		○ @see net.firstpartners.core.file.CSVOutputStrategy
		○ Note on how to create a CSV file
	- .json
		○ Use for debugging
		○ Use alongside GUI
		○ Go through Cycle of mappign document
	- Note about data independent of layout
	




NTOE IN WRITING RULES
PUT IN STRAGN VLAUES 

### Explaining the DRL ###
Note \\ needed in java when specifiying directories

In translator.dsl

-   Adding #/result will debug the generated rules to the log

### Writing your first rule ###

Writing your first rule

-   Refer to install notes
-   Start with existing Batch
-   Update config file

-   Input and output ; close to what you want as possible
-   Data Model is same, but there may be small differneces

-   First run

-   What you should see (work through tabs)
-   Possible exceptions

-   Esp around
#### More rules ####
-   Refer to showcase (of every possible rule)
-   Using drl
-   Without using drl

Checks for values

Using offsets

Cell Object (and javadoc on it)

Iterative development

Link to sample rules catalog

### Key Technical Documents and Classes ###
* Main Javadoc https://paulbrowne-irl.github.io/red-piranha/javadocs/
* Cell - https://paulbrowne-irl.github.io/red-piranha/javadocs/net/firstpartners/data/Cell.html
* Range  - https://paulbrowne-irl.github.io/red-piranha/javadocs/net/firstpartners/data/Range.html
* RangeList - https://paulbrowne-irl.github.io/red-piranha/javadocs/net/firstpartners/data/RangeList.html
* Convertors
** Excel Input Strategy https://paulbrowne-irl.github.io/red-piranha/javadocs/net/firstpartners/core/excel/ExcelInputStrategy.html
** Excel Output Strategy https://paulbrowne-irl.github.io/red-piranha/javadocs/net/firstpartners/core/excel/ExcelOutputStrategy.html
** CSV Output Strategy https://paulbrowne-irl.github.io/red-piranha/javadocs/net/firstpartners/core/file/CSVOutputStrategy.html
** JSON Output Strategy https://paulbrowne-irl.github.io/red-piranha/javadocs/net/firstpartners/core/file/JsonOutputStrategy.html
** WordInput Strategy https://paulbrowne-irl.github.io/red-piranha/javadocs/net/firstpartners/core/word/WordInputStrategy.html
** Word (newer version) Input Strategy
https://paulbrowne-irl.github.io/red-piranha/javadocs/net/firstpartners/core/word/WordXInputStrategy.html

## What Next ##
<!--stackedit_data:
eyJoaXN0b3J5IjpbNTk5MTM3NDk3LDE2MjY3NjY5NF19
-->