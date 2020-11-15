package linecounter;

import java.io.*;
import java.util.*;

public class Main {

	private static int total = 0;

	public static void main(String[] args) throws IOException {
		Scanner getInput = new Scanner(System.in);
		System.out.print("Please provide the full directory path here >>> ");
		String dir = getInput.nextLine();
		File maindir = new File(dir);
		if (maindir.exists() && maindir.isDirectory()) {
			// array for files and sub-directories
			// of directory pointed by maindir
			File arr[] = maindir.listFiles();

			System.out.println("**********************************************");
			System.out.println("Files from main directory : " + maindir);
			System.out.println("**********************************************");

			// Calling recursive method
			RecursivePrint(arr, 0, 0);
		}
		System.out.println("The total number of lines is " + total);

	}

	public static void createTestCountFiles(File dir) throws IOException {

		if (!dir.exists()) {
			System.out.println("Dir does not exist");
			System.exit(1);
		}
		int count = lineCount(dir.getPath());
		System.out.println("The file " + dir.getName() + " has " + count + " lines");
		total += count;
	}

	private static int lineCount(String file) throws IOException {
		int lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while (reader.readLine() != null)
				lines++;
		}
		return lines;
	}

	static void RecursivePrint(File[] arr, int index, int level) {

		// terminate condition
		if (index == arr.length)
			return;

		// tabs for internal levels
		for (int i = 0; i < level; i++)
			System.out.print("\t");

		// for files
		if (arr[index].isFile())
			try {
				createTestCountFiles(new File(arr[index].getAbsolutePath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (arr[index].isDirectory()) {
			System.out.println("[" + arr[index].getName() + "]");

			// recursion for sub-directories
			RecursivePrint(arr[index].listFiles(), 0, level + 1);
		}

		// recursion for main directory
		RecursivePrint(arr, ++index, level);
	}
}
