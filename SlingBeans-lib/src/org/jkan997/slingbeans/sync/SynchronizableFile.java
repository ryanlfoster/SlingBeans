/**
 * SlingBeans - NetBeans Sling plugin
 * https://github.com/jkan997/SlingBeans
 * Licensed under Apache 2.0 license
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jkan997.slingbeans.sync;

import org.jkan997.slingbeans.helper.StringHelper;

/**
 *
 * @author jkan997
 */
public abstract class SynchronizableFile {

    protected String rootDir;
    protected String path;
    
    public String getPath(){
        return path;
    }

    public abstract long lastModified();

    public abstract SynchronizableFile[] getChildren();

    public abstract boolean isFolder();
    public abstract boolean isFile();

    public abstract byte[] getContent();
    public abstract void createFile(String name,byte[] content);
    public abstract void createFolder(String name,byte[] content);

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir =  StringHelper.normalizePath(rootDir);
    }
    
     public void setFullPath(String fullPath) {
        fullPath = StringHelper.normalizePath(fullPath);
        fullPath = fullPath.substring(rootDir.length());
        path = StringHelper.normalizePath(fullPath);
        
    }

    
}
