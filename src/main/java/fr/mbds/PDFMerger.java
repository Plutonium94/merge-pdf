package fr.mbds;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import java.io.*;
import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Function;

public class PDFMerger {

	public static final String DEFAULT_MERGED_FILENAME = "./target/resultat.pdf";

	public static PDDocument merge(PDDocument[] documents) {
		PDDocument res = new PDDocument();
		for(PDDocument doc : documents) {
			for(PDPage page : doc.getPages()) {
				res.addPage(page);
			}
		}
		return res;
	}

	public static boolean mergeInto(String resultFilePath, String[] inputFilePaths) throws IOException {
		try  {
			PDDocument[] docs = Arrays.stream(inputFilePaths).map(new Function<String, PDDocument>() {

				@Override
				public PDDocument apply(String ifp) {
					File f = new File(ifp);
					try {
						return PDDocument.load(f);
					} catch(IOException ioe) {
						ioe.printStackTrace();
						return null;
					}
				}
			}).toArray(PDDocument[]::new);
			PDDocument docRes = merge(docs);
			docRes.save(resultFilePath);
			docRes.close();
			for(PDDocument doc : docs) {
				doc.close();
			}
			return true;
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return false;
	}

	public static boolean mergeInto(String[] inputFilePaths) throws IOException {
		return mergeInto(DEFAULT_MERGED_FILENAME, inputFilePaths);
	}
 

}