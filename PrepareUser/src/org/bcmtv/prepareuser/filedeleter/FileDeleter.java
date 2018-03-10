/*******************************************************************************
 * Source code copyright Berkeley Community Media. All rights reserved.
 ******************************************************************************/
package org.bcmtv.prepareuser.filedeleter;

import java.io.File;

public class FileDeleter 
{
	//Recursively delete files in a directory
	boolean deleteDirectory(File dir) 
	{
		if (dir.isDirectory())
		{
			File[] children = dir.listFiles();
			for (File child:children)
			{
				boolean success = deleteDirectory(child);
				if (!success)
				{
					return false;
				}
			}
		}
		
		return dir.delete();
	}
	
	//delete the file given
	boolean deleteFile(File file)
	{
		boolean success = false;
		if (file.isDirectory())
		{
			success = deleteDirectory(file);
		}
		else
		{
			success = file.delete();
		}
		
		if(success)
		{
			System.out.println("Deleted "+file.getName());
			return true;
		}
		else
		{
			if(!file.exists())
			{
				System.out.println("Failed to delete "+file.getName()+" because it doesn't exist.");
			}
			else
			{
				System.out.println("Failed to delete "+file.getName());
			}
			return false;
		}
	}
	
	boolean deleteFile(String filename)
	{
		File file = new File(filename);
		return deleteFile(file);
	}
	
	//delete all the files in array
	public void deleteFiles(File[] files)
	{
		for (File file:files)
		{
			deleteFile(file);
		}
	}
}
