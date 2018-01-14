using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TOOL
{

    public class IOHelper
    {
        #region 创建文件夹
        /// <summary>
        /// 创建文件夹
        /// </summary>
        /// <param name="dirName"></param>
        /// <returns></returns>
        public static bool CreateDir(string dirName)
        {
            if (!Directory.Exists(dirName))
            {
                Directory.CreateDirectory(dirName);
            }
            return true;
        }
        #endregion

        #region 复制文件夹
        /// <summary>
        /// 复制文件夹
        /// </summary>
        /// <param name="fromDir">被复制的目录</param>
        /// <param name="toDir">复制到的目录</param>
        /// <returns></returns>
        public static bool CopyDir(string fromDir, string toDir)
        {
            if (fromDir == null || toDir == null)
            {
                throw new NullReferenceException("参数为空");
            }

            if (fromDir == toDir)
            {
                throw new Exception("两个目录都是" + fromDir);
            }

            if (!Directory.Exists(fromDir))
            {
                throw new IOException("目录fromDir=" + fromDir + "不存在");
            }

            DirectoryInfo dir = new DirectoryInfo(fromDir);
            return CopyDir(dir, toDir, dir.FullName);
        }


        /// <summary>
        /// 复制目录
        /// </summary>
        /// <param name="fromDir">被复制的目录</param>
        /// <param name="toDir">复制到的目录</param>
        /// <param name="rootDir">被复制的根目录</param>
        /// <returns></returns>
        private static bool CopyDir(DirectoryInfo fromDir, string toDir, string rootDir)
        {
            string filePath = string.Empty;
            foreach (FileInfo f in fromDir.GetFiles())
            {
                filePath = toDir + f.FullName.Substring(rootDir.Length);
                string newDir = filePath.Substring(0, filePath.LastIndexOf("//"));
                CreateDir(newDir);
                File.Copy(f.FullName, filePath, true);
            }

            foreach (DirectoryInfo dir in fromDir.GetDirectories())
            {
                CopyDir(dir, toDir, rootDir);
            }

            return true;
        }
        #endregion

        #region 文件是否存在
        /// <summary>
        /// 判断文件是否存在
        /// </summary>
        /// <param name="fileName"></param>
        /// <returns></returns>
        public static bool Exists(string fileName)
        {
            if (fileName == null || fileName.Trim() == "")
            {
                return false;
            }

            if (File.Exists(fileName))
            {
                return true;
            }

            return false;
        }
        #endregion

        #region 删除文件夹
        /// <summary>
        /// 删除文件夹
        /// </summary>
        /// <param name="dir">制定目录</param>
        /// <param name="onlyDir">是否只删除目录</param>
        /// <returns></returns>
        public static bool DeleteDir(string dir, bool onlyDir)
        {
            if (dir == null || dir.Trim() == "")
            {
                throw new NullReferenceException("目录dir=" + dir + "不存在");
            }

            if (!Directory.Exists(dir))
            {
                return false;
            }

            DirectoryInfo dirInfo = new DirectoryInfo(dir);
            if (dirInfo.GetFiles().Length == 0 && dirInfo.GetDirectories().Length == 0)
            {
                Directory.Delete(dir);
                return true;
            }


            if (!onlyDir)
            {
                return false;
            }
            else
            {
                DeleteDir(dirInfo);
                return true;
            }

        }

        public static void DeleteDir(DirectoryInfo dir)
        {
            if (dir == null)
            {
                throw new NullReferenceException("目录不存在");
            }

            foreach (DirectoryInfo d in dir.GetDirectories())
            {
                DeleteDir(d);
            }

            foreach (FileInfo f in dir.GetFiles())
            {
                DeleteFile(f.FullName);
            }

            dir.Delete();

        }
        #endregion

        #region 创建文件
        /// <summary>
        /// 创建文件
        /// </summary>
        /// <param name="fileName">文件名称</param>
        /// <returns></returns>
        public static bool CreateFile(string fileName)
        {
            if (!File.Exists(fileName))
            {
                FileStream fs = File.Create(fileName);
                fs.Close();
                fs.Dispose();
            }
            return File.Exists(fileName);
        }
        #endregion

        #region 写文件
        /****************************************
          * 函数名称：WriteFile
          * 功能说明：写文件,会覆盖掉以前的内容
          * 参     数：Path:文件路径,Strings:文本内容
          * 调用示列：
          *            string Path = Server.MapPath("Default2.aspx");       
          *            string Strings = "这是我写的内容啊";
          *            EC.FileObj.WriteFile(Path,Strings);
         *****************************************/
        /// <summary>
        /// 写文件
        /// </summary>
        /// <param name="Path">文件路径</param>
        /// <param name="Strings">文件内容</param>
        public static bool WriteFile(string Path, string Strings)
        {
            if (!System.IO.File.Exists(Path))
            {
                System.IO.FileStream f = System.IO.File.Create(Path);
                f.Close();
            }
            System.IO.StreamWriter f2 = new System.IO.StreamWriter(Path, false, System.Text.Encoding.UTF8);
            f2.Write(Strings);
            f2.Close();
            f2.Dispose();
            return File.Exists(Path);
        }

        /// <summary>
        /// 写入一行
        /// </summary>
        /// <param name="fileName">文件名</param>
        /// <param name="content">内容</param>
        /// <returns></returns>
        public static bool WriteLine(string fileName, string content)
        {
            using (FileStream fs = new FileStream(fileName, FileMode.OpenOrCreate | FileMode.Append))
            {
                lock (fs)
                {
                    if (!fs.CanWrite)
                    {
                        throw new System.Security.SecurityException("文件fileName=" + fileName + "是只读文件不能写入!");
                    }

                    StreamWriter sw = new StreamWriter(fs);
                    sw.WriteLine(content);
                    sw.Dispose();
                    sw.Close();
                    return true;
                }
            }
        }


        public static bool CopyDir(DirectoryInfo fromDir, string toDir)
        {
            return CopyDir(fromDir, toDir, fromDir.FullName);
        }
        #endregion

        #region 追加文件
        /****************************************
          * 函数名称：FileAdd
          * 功能说明：追加文件内容
          * 参     数：Path:文件路径,strings:内容
          * 调用示列：
          *            string Path = Server.MapPath("Default2.aspx");     
          *            string Strings = "新追加内容";
          *            EC.FileObj.FileAdd(Path, Strings);
         *****************************************/
        /// <summary>
        /// 追加文件
        /// </summary>
        /// <param name="Path">文件路径</param>
        /// <param name="strings">内容</param>
        public static void FileAdd(string Path, string strings)
        {
            StreamWriter sw = File.AppendText(Path);
            sw.Write(strings);
            sw.Flush();
            sw.Close();
        }
        #endregion

        #region 读文件
        /****************************************
          * 函数名称：ReadFile
          * 功能说明：读取文本内容
          * 参     数：Path:文件路径
          * 调用示列：
          *            string Path = Server.MapPath("Default2.aspx");       
          *            string s = EC.FileObj.ReadFile(Path);
         *****************************************/
        /// <summary>
        /// 读文件
        /// </summary>
        /// <param name="Path">文件路径</param>
        /// <returns></returns>
        public static string ReadFile(string Path)
        {
            string s = "";
            if (!System.IO.File.Exists(Path))
                s = "不存在相应的目录";
            else
            {
                StreamReader f2 = new StreamReader(Path, System.Text.Encoding.GetEncoding("gb2312"));
                s = f2.ReadToEnd();
                f2.Close();
                f2.Dispose();
            }

            return s;
        }

        public static string ReadLine(string fileName)
        {
            if (!Exists(fileName))
            {
                return null;
            }
            using (FileStream fs = new FileStream(fileName, FileMode.Open))
            {
                return new StreamReader(fs).ReadLine();
            }
        }
        #endregion

        #region 删除文件
        /// <summary>
        /// 删除文件
        /// </summary>
        /// <param name="fileName">文件的完整路径</param>
        /// <returns></returns>
        public static void DeleteFile(string fileName)
        {
            try
            {
                if (Exists(fileName))
                {
                    File.Delete(fileName);
                }
            }
            catch (Exception e)
            {
                throw new Exception(e.Message);
            }
        }
        #endregion

        #region 在指定的目录中查找文件
        /// <summary>
        /// 在指定的目录中查找文件
        /// </summary>
        /// <param name="dir">目录</param>
        /// <param name="fileName">文件名</param>
        /// <returns></returns>
        public static bool FindFile(string dir, string fileName)
        {
            if (dir == null || dir.Trim() == "" || fileName == null || fileName.Trim() == "" || !Directory.Exists(dir))
            {
                return false;
            }

            DirectoryInfo dirInfo = new DirectoryInfo(dir);
            return FindFile(dirInfo, fileName);

        }

        public static bool FindFile(DirectoryInfo dir, string fileName)
        {
            foreach (DirectoryInfo d in dir.GetDirectories())
            {
                if (File.Exists(d.FullName + "//" + fileName))
                {
                    return true;
                }
                FindFile(d, fileName);
            }

            return false;
        }
        #endregion

        #region 移动文件
        /// <summary>
        /// 移动文件
        /// </summary>
        /// <param name="OrignFile">原始路径</param>
        /// <param name="NewFile">新路径</param>
        public static void FileMove(string orignFile, string NewFile)
        {
            if (File.Exists(NewFile))
            {
                File.Delete(NewFile);
            }
            File.Move(orignFile, NewFile);
        }
        #endregion

        #region 拷贝文件
        /// <summary>
        /// 拷贝文件
        /// </summary>
        /// <param name="SourceFile">原始文件</param>
        /// <param name="TargetDirectory">新文件路径</param>
        public static void CopyFileToDirectory(string SourceFile, string TargetDirectory)
        {
            DirectoryInfo DeseFileInfo = new DirectoryInfo(TargetDirectory);
            if (!DeseFileInfo.Exists)
            {
                Directory.CreateDirectory(DeseFileInfo.FullName);
            }

            FileInfo sourceFileInfo = new FileInfo(SourceFile);

            string targetFile = sourceFileInfo.FullName.Replace(sourceFileInfo.DirectoryName, TargetDirectory);
            CreateFile(targetFile);

            File.Copy(SourceFile, targetFile, true);
        }

        /// <summary>
        /// 拷贝文件
        /// </summary>
        /// <param name="SourceFile">原始文件</param>
        /// <param name="TargetFile">目标文件</param>
        public static void CopyFile(string SourceFile, string TargetFile)
        {
            if (!File.Exists(TargetFile))
            {
                File.Create(TargetFile);
            }
            File.Copy(SourceFile, TargetFile, true);
        }

        #endregion

        #region 在当前目录下创建目录
        /****************************************
          * 函数名称：FolderCreate
          * 功能说明：在当前目录下创建目录
          * 参     数：OrignFolder:当前目录,NewFloder:新目录
          * 调用示列：
          *            string orignFolder = Server.MapPath("test/");    
          *            string NewFloder = "new";
          *            EC.FileObj.FolderCreate(OrignFolder, NewFloder); 
         *****************************************/
        /// <summary>
        /// 在当前目录下创建目录
        /// </summary>
        /// <param name="OrignFolder">当前目录</param>
        /// <param name="NewFloder">新目录</param>
        public static string FolderCreate(string orignFolder, string NewFloder)
        {
            Directory.SetCurrentDirectory(orignFolder);
            return Directory.CreateDirectory(NewFloder).FullName;
        }
        #endregion

        #region 递归删除文件夹目录及文件
        /****************************************
          * 函数名称：DeleteFolder
          * 功能说明：递归删除文件夹目录及文件
          * 参     数：dir:文件夹路径
          * 调用示列：
          *            string dir = Server.MapPath("test/");  
          *            EC.FileObj.DeleteFolder(dir);       
         *****************************************/
        /// <summary>
        /// 递归删除文件夹目录及文件
        /// </summary>
        /// <param name="dir"></param>
        /// <returns></returns>
        public static void DeleteFolder(string dir)
        {
            if (Directory.Exists(dir)) //如果存在这个文件夹删除之 
            {
                foreach (string d in Directory.GetFileSystemEntries(dir))
                {
                    if (File.Exists(d))
                        File.Delete(d); //直接删除其中的文件 
                    else
                        DeleteFolder(d); //递归删除子文件夹 
                }
                Directory.Delete(dir); //删除已空文件夹 
            }

        }

        #endregion

        #region 将指定文件夹下面的所有内容copy到目标文件夹下面 果目标文件夹为只读属性就会报错。
        /****************************************
          * 函数名称：CopyDir
          * 功能说明：将指定文件夹下面的所有内容copy到目标文件夹下面 果目标文件夹为只读属性就会报错。
          * 参     数：srcPath:原始路径,aimPath:目标文件夹
          * 调用示列：
          *            string srcPath = Server.MapPath("test/");  
          *            string aimPath = Server.MapPath("test1/");
          *            EC.FileObj.CopyDir(srcPath,aimPath);   
         *****************************************/
        /// <summary>
        /// 指定文件夹下面的所有内容copy到目标文件夹下面
        /// </summary>
        /// <param name="srcPath">原始路径</param>
        /// <param name="aimPath">目标文件夹</param>
        public static void CopyDirByFile(string srcPath, string aimPath)
        {
            try
            {
                // 检查目标目录是否以目录分割字符结束如果不是则添加之
                if (aimPath[aimPath.Length - 1] != Path.DirectorySeparatorChar)
                    aimPath += Path.DirectorySeparatorChar;
                // 判断目标目录是否存在如果不存在则新建之
                if (!Directory.Exists(aimPath))
                    Directory.CreateDirectory(aimPath);
                // 得到源目录的文件列表，该里面是包含文件以及目录路径的一个数组
                //如果你指向copy目标文件下面的文件而不包含目录请使用下面的方法
                //string[] fileList = Directory.GetFiles(srcPath);
                string[] fileList = Directory.GetFileSystemEntries(srcPath);
                //遍历所有的文件和目录
                foreach (string file in fileList)
                {
                    //先当作目录处理如果存在这个目录就递归Copy该目录下面的文件

                    if (Directory.Exists(file))
                        CopyDir(file, aimPath + Path.GetFileName(file));
                    //否则直接Copy文件
                    else
                        File.Copy(file, aimPath + Path.GetFileName(file), true);
                }

            }
            catch (Exception ee)
            {
                throw new Exception(ee.ToString());
            }
        }


        #endregion

        #region 取得文件后缀名
        /****************************************
          * 函数名称：GetPostfixStr
          * 功能说明：取得文件后缀名
          * 参     数：filename:文件名称
          * 调用示列：
          *            string filename = "aaa.aspx";        
          *            string s = EC.FileObj.GetPostfixStr(filename);         
         *****************************************/
        /// <summary>
        /// 取后缀名
        /// </summary>
        /// <param name="filename">文件名</param>
        /// <returns>.gif|.html格式</returns>
        public static string GetPostfixStr(string filename)
        {
            int start = filename.LastIndexOf(".");
            int length = filename.Length;
            string postfix = filename.Substring(start, length - start);
            return postfix;
        }
        #endregion

        #region 获取某目录下的所有文件
        /// <summary>
        /// 获取文件夹下的所有文件
        /// </summary>
        /// <param name="folder">folder</param>
        /// <returns>file path list</returns>
        public static List<string> GetAllFileUnderDir(string folder)
        {
            List<string> fileList = new List<string>();
            GetFilesInFolder(folder, fileList);
            return fileList;
        }

        private static void GetFilesInFolder(string Path, List<string> filePathList)
        {
            string[] fileList = System.IO.Directory.GetFileSystemEntries(Path);
            // 遍历所有的文件和目录
            foreach (string file in fileList)
            {
                if (System.IO.Directory.Exists(file))
                    GetFilesInFolder(file, filePathList);
                else
                    filePathList.Add(file);
            }
        }
        #endregion

        #region 获取文件名称
        public static string GetFileName(string file)
        {
            FileInfo info = new FileInfo(file);
            if (info != null)
            {
                return info.Name;
            }
            else
            {
                return string.Empty;
            }
        }

        public static string GetFileNameWithoutPostfix(string file)
        {
            string result = string.Empty;
            string filename = GetFileName(file);
            if (!string.IsNullOrEmpty(filename))
            {
                if (filename.IndexOf(".") > 0)
                {
                    result = filename.Substring(0, filename.IndexOf("."));
                }
            }
            return result;
        }
        #endregion
    }

}
