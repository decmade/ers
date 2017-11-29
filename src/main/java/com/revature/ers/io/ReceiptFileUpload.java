package com.revature.ers.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.revature.ers.beans.interfaces.ReceiptInterface;
import com.revature.ers.logger.IOLogger;
import com.revature.ers.logger.LoggerInterface;
import com.revature.ers.props.ApplicationProperties;

/**
 * a utility class that processes a file upload to be saved
 * to local storage
 * 
 * @author john.w.brown.jr@gmail.com
 *
 */
public class ReceiptFileUpload {
	private static final String KEY_CONTENT_PART = "content-disposition";
	private static final String KEY_FILENAME = "filename";
	private static final String STORAGE_PREFIX = "/uploads";
	private static final String dataPath = ApplicationProperties.getInstance().getProperties().getProperty("DataPath");
	
	private static LoggerInterface log = IOLogger.getInstance();
		
	/**
	 * stores a file uploaded with an Http request at the
	 * destPath specified as fileName with the original extension
	 * of the uploaded file and returns the full path of the file
	 * 
	 * @param HttpServletRequest request
	 * @param String destPath
	 * 
	 * @return string
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	public static String store (HttpServletRequest request, String fileName) {
		Part filePart = null;
		String uploadedFileName = "";
		String uploadedFileExtension = "";
		BufferedInputStream in = null;
		File destination = null;
		BufferedOutputStream out = null;
		byte[] buffer = new byte[ 10240 ];
		int numBytes = 0;
		
		try {
			createRootDirectory();
			filePart = request.getPart("file");
			uploadedFileName = getFileName( filePart );
			uploadedFileExtension = getFileExtension( uploadedFileName );
			in = getInputStream( filePart );
			destination = getFile(fileName, uploadedFileExtension );
			out = getOutputStream( destination );
			
			log.debug( String.format("attempting to store uploaded file as [%s]", destination.getAbsolutePath() ) );
			
			while( ( numBytes = in.read(buffer) ) != -1) {
				out.write(buffer, 0, numBytes );
				out.flush();
			}
			
			in.close();
			out.close();
			
			log.debug(String.format("upload successfully stored as [%s]", destination.getAbsolutePath() ) );
			
		} catch(Exception e) {
			log.error( e.getMessage() );
			return "";
		}
		
		
		
		return destination.getName();
	}
	
	/**
	 * return the the File represented by the receipt object
	 * 
	 * @param ReceiptInterface receipt
	 * 
	 * @return File
	 */
	public static File retrieve(ReceiptInterface receipt) {
		return new File( getRootPath(), receipt.getFileName() );
	}
	
	/**
	 * returns a BufferendInputStream from the part of 
	 * the HttpServletRequest passed
	 * 
	 * @param Part in
	 * 
	 * @return BufferedInputStream
	 * 
	 * @throws IOException
	 */
	private static BufferedInputStream getInputStream(Part in)
	throws IOException {
		
		InputStream inputStream = null;
		BufferedInputStream bufferedStream = null;
		
		log.debug("preparing an input stream from uploaded file");
		
		try {
			inputStream = in.getInputStream();
			bufferedStream = new BufferedInputStream( inputStream );
			
			log.debug("input stream prepared successfully");
		} catch(Exception e) {
			log.error( e.getMessage() );
		}
		
		return bufferedStream;
	}
	
	/**
	 * returns the root storage path for all files
	 * 
	 * @return String
	 */
	public static String getRootPath() {
		
		String[] parts = {
			System.getProperty("user.dir"),
			dataPath,
			STORAGE_PREFIX,
		};
		
		return String.join(File.separator, parts);
	}
	
	/**
	 * returns a BufferedOutputStream object that points to
	 * the file path specified
	 * 
	 * @param String destination
	 * 
	 * @return BufferendOutputStream
	 * 
	 * @throws IOException
	 */
	private static BufferedOutputStream getOutputStream(File file) {
		OutputStream outputStream = null;
		BufferedOutputStream out = null;
		
		log.debug( String.format("preparing an output stream to save uploaded file to: [%s]", file.getAbsolutePath() ) );
		
		try {
			outputStream = new FileOutputStream( file );
			out = new BufferedOutputStream( outputStream );
			log.debug("output stream created successfully");
		} catch( IOException e) {
			log.error( e.getMessage() );
		}
		
		return out;
	}


	
	/**
	 * returns a file pointer to the destination path
	 * after making certain that the file is there and
	 * not a directory
	 * 
	 * @param String path
	 * 
	 * @return File
	 * 
	 * @throws IOException
	 */
	private static File getFile(String fileName, String extension) {
		File out = null;
		String path = getRootPath();
		
		try {
			out = new File(path, String.format("%s.%s", fileName, extension) );
			log.debug( String.format("opening destination file resource at [%s]", out.getAbsolutePath() ) );
			
			/*
			 * throw exception if this points to a directory
			 */
			if ( out.isDirectory() ) {
				throw new IOException();
			}
			
			/*
			 * make certain that the file exists
			 */
			out.createNewFile();
			
			log.debug("destination file created successfully");
		} catch(Exception e) {
			log.error( e.getMessage() );
		}		
		
		return out;
	}
	
	/**
	 * is this ever necessary? why do I care what the name of the upload was?
	 * 
	 * part
	 * extracts the file name of a file inluded in a request
	 * 
	 * @param Part part
	 * 
	 * @return String
	 */
	private static String getFileName(Part part) 
	throws ServletException {
	    String partHeader = "";
	    String output = "";
	    int index = 0;
	    
	    log.debug("attempting to extract file name from upload");
	    
	    partHeader = part.getHeader( KEY_CONTENT_PART );
	    
	    for (String content : partHeader.split(";")) {
	    	
	        if ( content.trim().startsWith( KEY_FILENAME ) ) {
	        	index = content.indexOf('=') + 1;	        	
	            output = content.substring( index );
	            log.debug( String.format("detected uploaded file: [%s]", output ) );
	        }
	    }
	    
	    return output.trim().replace("\"", "");
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
		
		log.debug( String.format( "extracting file extension from [%s]", fileName) );
		
		try {
			ext = parts[ len - 1].toLowerCase();
			log.debug( String.format("successfully extracted extension [%s]", ext) );
		} catch(Exception e) {
			log.error( e.getMessage() );
		}
		
		return ext;
	}
	
	/**
	 * establishes the directory structure for the
	 * root path
	 */
	private static void createRootDirectory() {
		String rootPath = getRootPath();
		File target = null;
	
		target = new File( rootPath );
		
		if ( target.exists() == false ) {
			target.mkdirs();
		}
	}

}