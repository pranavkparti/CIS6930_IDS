PROJECT 1

There are three main .java files representing each type of hash table:

1)Multi-hashing Table (MultiHashTable.java)
2)Cuckoo Hash Table (CuckooHashTable.java)
3)D-Left Hash Table (DLeftHashTable.java)

Each hash table extends the abstract class HashTable.java, 
which contains common variables (such as table size, number of hashes, etc.),
and common methods (query() and insert()).
 

USAGE INSTRUCTIONS:

> Compile and Run Test.java
	-> In the directory project1/, run the following command:
		
		javac -d bin/ *.java
	  
	  This compiles all the .java files in project1/ into the empty directory bin/ 
	
	-> Now, in the same directory project1/, run the following command:
		
		java -cp bin/ project1.Test
	  
	  This runs the Test class which executes a function demo() for all three hash tables.
	  It outputs three files into the empty directory output/ :
	  1) mht.txt, for Multi-hashing Table
	  2) cht.txt, for Cuckoo Hash Table
	  3) dlht.txt, for D-Left Hash Table
	  
OUTPUT FORMAT:

The first line of each output file is a number representing the number of flows in the table.
The next line is empty, followed by the title line "TABLE ENTRIES".
Following that are all the table entries.
	   	