import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class DiffTool {

	public static int[][] jaccardSim(String[][] file1, String[][] file2) {
		ArrayList<String> first = new ArrayList<String>();
		ArrayList<String> second = new ArrayList<String>();
		int[][] jaccInd = new int[file2.length][file1.length];
		int total = 0;
		double same = 0;
		double diff = 0;
		//
		// for (int x = 0; x < jaccInd.length; x++) {
		// System.out.println("x = " +x);
		// for (int y = 0; y < jaccInd[x].length; y++) {
		// System.out.println("y = " +y);
		for (int a = 0; a < file1.length; a++) {

			System.out.println("a = " + a);
			for (int b = 0; b < file2.length; b++) {

				System.out.println("b = " + b);
				for (int i = 0; i < file1[0].length; i++) {

					System.out.println("i = " + i);

					for (int j = 0; j < file2[0].length; j++) {
						System.out.println("j = " + j);
						if (file1[a][i].equals(file2[b][j])) {
							same++;
						} else {
							first.add(file1[a][i]);
							second.add(file2[b][j]);
						}

						for (int m = 0; m < first.size(); m++) {
							// System.out.println("m = " +m);
							for (int n = 0; n < second.size(); n++) {
								// System.out.println("n = " +n);
								if (first.get(m).equals(second.get(n))) {
									same++;

									// System.out.println("diff = " +diff);
									System.out.println("firstans = "
											+ first.get(m));
									System.out.println("secondans = "
											+ second.get(n));
									System.out.println("first: " + first.size()
											+ " second: " + second.size());
									first.remove(m);
									second.remove(n);
									n--;
									m--;

								}

							}
							int size1 = first.size();
							int size2 = second.size();
							diff = size1 + size2;
							System.out.println("diff = " + diff);
							total = 0;
							same = 0;
							diff = 0;
						}
						// int size1 = first.size();
						// int size2 = second.size();
						// System.out.println("size1 = " +size1 + " size2 = "
						// +size2);
						// diff = size1 + size2;
					}
				}
				// total = (int)((100)*(same/(same+diff)));
				// System.out.println("total = " +total);
				// jaccInd[x][y] = total;
			}
		}
		// }
		// }
		return jaccInd;
	}

	// public static ArrayList<String> findTransformations(String[][] text1,
	// String[][] text2, int[][] margins) {
	// ArrayList<String> transformations = new ArrayList<String>();
	//
	// for (int i = 0; i < text1.length; i++) {
	// for (int j = 0; j < text1[i].length - 1; j++) {
	// String t1A = text1[i][j];
	// String t1B = text1[i][j+1];
	// String t2A = text2[i][j];
	// String t2B = text2[i][j+1];
	// if (margins[i][j] == 0)
	// continue;
	// else if (t1B.equals(t2A)) { // deletion
	// transformations.add("Replace word \"" + t1A + "\" with word \"" + t2A +
	// "\""); //deletion
	// else if (t1A.equals(t2B))
	// transformations.add("Add word \"" + t2A + "\" before word \"" + t1B +
	// "\""); //addition
	// }
	// else { //the two lines are completely different - needs to be transposed
	// if ()
	// }
	//
	// }
	//
	// }
	// return transformations;
	// }

	public static void main(String[] args) {
		String[][] doc1Lines = null;
		String[][] doc2Lines = null;
		String doc1 = "";
		String doc2 = "";
		int max1 = 0;
		int max2 = 0;
		int max = 0;

		try { // reads in .txt file
			File d1 = new File("File1.txt");
			File d2 = new File("File2.txt");
			Scanner sc1 = new Scanner(d1);
			Scanner sc2 = new Scanner(d2);

			// copy file1 to doc1 string
			while (sc1.hasNextLine()) {
				doc1 += sc1.nextLine() + "\n";
				// doc1 += "\n";
			}

			// copy file2 to doc2 string
			while (sc2.hasNextLine()) {
				doc2 += sc2.nextLine() + "\n";
				// doc2 += "\n";
			}

			sc1.close();
			sc2.close();

			Scanner scn1 = new Scanner(d1);
			Scanner scn2 = new Scanner(d2);

			// find max number of columns
			while (scn1.hasNextLine()) {
				String[] line = scn1.nextLine().split(" ");
				if (line.length > max1)
					max1 = line.length;
				System.out.println(max1);
			}

			while (scn2.hasNextLine()) {
				String[] line = scn2.nextLine().split(" ");
				if (line.length > max2)
					max2 = line.length;
				System.out.println(max2);
			}

			if (max1 > max2) {
				max = max1;
			} else
				max = max2;

			System.out.println(max);

			scn1.close();
			scn2.close();

		} catch (FileNotFoundException ex) { // throws exception if file is not
												// found
			System.out.println("File not found");
		}

		// add to 2d array
		String[] lines1 = doc1.split("\n");
		String[] lines2 = doc2.split("\n");
		doc1Lines = new String[lines1.length][max];
		doc2Lines = new String[lines2.length][max];

		for (int i = 0; i < lines1.length; i++) {
			String[] words = lines1[i].split(" "); // makes array of the words
													// in each line
			for (int j = 0; j < words.length; j++) {
				doc1Lines[i][j] = words[j]; // adds the word to corresponding
											// position in 2d array, meaning
											// there will still be blank spaces
			}
		}

		for (int i = 0; i < lines2.length; i++) {
			String[] words = lines2[i].split(" ");
			for (int j = 0; j < words.length; j++) {
				doc2Lines[i][j] = words[j];
			}
		}

		for (int i = 0; i < doc1Lines.length; i++) {
			for (int j = 0; j < doc1Lines[i].length; j++) {
				if (doc1Lines[i][j] == null) {
					doc1Lines[i][j] = " ";
				}
			}
		}

		for (int i = 0; i < doc2Lines.length; i++) {
			for (int j = 0; j < doc2Lines[i].length; j++) {
				if (doc2Lines[i][j] == null) {
					doc2Lines[i][j] = " ";
				}
			}
		}

		System.out.println("file1 len " + doc1Lines.length + " file1 width "
				+ doc1Lines[0].length);
		System.out.println("file2 len " + doc2Lines.length + " file2 width "
				+ doc2Lines[0].length);

		int[][] margError = jaccardSim(doc1Lines, doc2Lines);

		// for (int i = 0; i < doc2Lines.length; i++) {
		// for (int j = 0; j < doc2Lines[i].length; j++) {
		// System.out.print(doc2Lines[i][j] + " ");
		// }
		// System.out.println();
		// }

		// System.out.println(doc1Lines[1][0]);
		//
		for (int i = 0; i < margError.length; i++) {
			for (int j = 0; j < margError[0].length; j++) {
				System.out.print(margError[i][j] + " ");
			}
			System.out.println();
		}
	}
}
