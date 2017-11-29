package com.revature.ers.io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
/**
 * @deprecated
 * 
 * thought I needed this but I actually just needed to let
 * pass the file extension and let the web browser figure it out
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class MimeTypeDetector {

	public static String detectType(File file) {
		String extension = getFileExtension( file.getName() );
		System.out.println( extension );
		return getTypes().get( extension );
	}
	
	private static Map<String, String> getTypes() {
		Map<String, String> types = new HashMap<>();
		
		types.put("docm", "application/vnd.ms-word.document.macroEnabled.12");
		types.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		types.put("dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template");
		
		return types;

/*		application/vnd.ms-word.document.macroEnabled.12                        docm
		application/vnd.openxmlformats-officedocument.wordprocessingml.document docx
		application/vnd.openxmlformats-officedocument.wordprocessingml.template dotx
		application/vnd.ms-powerpoint.template.macroEnabled.12                  potm
		application/vnd.openxmlformats-officedocument.presentationml.template   potx
		application/vnd.ms-powerpoint.addin.macroEnabled.12                     ppam
		application/vnd.ms-powerpoint.slideshow.macroEnabled.12                 ppsm    
		application/vnd.openxmlformats-officedocument.presentationml.slideshow  ppsx
		application/vnd.ms-powerpoint.presentation.macroEnabled.12              pptm
		application/vnd.openxmlformats-officedocument.presentationml.presentation       pptx
		application/vnd.ms-excel.addin.macroEnabled.12                          xlam
		application/vnd.ms-excel.sheet.binary.macroEnabled.12                   xlsb
		application/vnd.ms-excel.sheet.macroEnabled.12                          xlsm
		application/vnd.openxmlformats-officedocument.spreadsheetml.sheet       xlsx    
		application/vnd.ms-excel.template.macroEnabled.12                       xltm    
		application/vnd.openxmlformats-officedocument.spreadsheetml.template    xltx
		*/
	}
	
	/**
	 * returns the extension of a file
	 * 
	 * @param String fileName
	 * 
	 * @return String
	 */
	private static String getFileExtension(String fileName ) {
		String[] parts = fileName.split("\\.");
		int len = parts.length;
		String ext = "";
		

		try {
			ext = parts[ len - 1].toLowerCase();
		} catch(Exception e) {
			// stub
		}
		
		return ext;
	}
}
