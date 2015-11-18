package com.example.locationexample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class Utility {

	public static void saveToAFile(Context context ,final String fileContents,String fileName) {
		//Context context = App.instance.getApplicationContext();
		try {
			FileWriter out = new FileWriter(new File(context.getFilesDir(),
					fileName));
			out.write(fileContents);
			out.close();
		} catch (IOException e) {
			Log.e("DATA",e.getMessage());
		}
	}

	public static String readFromFile(Context context, String fileName) {
		//Context context = App.instance.getApplicationContext();
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		BufferedReader in = null;

		try {
			in = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
			while ((line = in.readLine()) != null)
				stringBuilder.append(line);

		} catch (FileNotFoundException e) {
			Log.e("DATA",e.getMessage());
		} catch (IOException e) {
			Log.e("DATA",e.getMessage());
		}

		return stringBuilder.toString();
	}

}
